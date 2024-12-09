const TicketPool = require('../models/ticketpool');
let ticketPool, vendorInterval, customerInterval;

/**
 * @swagger
 * components:
 *   schemas:
 *     ConfigureSystemRequest:
 *       type: object
 *       required:
 *         - totalTickets
 *         - maxTicketCapacity
 *       properties:
 *         totalTickets:
 *           type: integer
 *           description: Initial ticket count.
 *           example: 100
 *         maxTicketCapacity:
 *           type: integer
 *           description: Maximum ticket count.
 *           example: 1000
 *     StartSystemResponse:
 *       type: object
 *       properties:
 *         message:
 *           type: string
 *           description: Status message.
 *           example: "System started"
 */

/**
 * @swagger
 * /configureSystem:
 *   post:
 *     summary: Configure the ticketing system
 *     description: |
 *       Configures the ticketing system, setting total tickets, maximum capacity, and more.
 *       **Concurrency Logic**: 
 *       - Mutex locks ensure thread safety during add/remove operations.
 *       - Real-time updates are provided using Socket.IO events.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/ConfigureSystemRequest'
 *     responses:
 *       200:
 *         description: System configured successfully.
 *       400:
 *         description: Configuration failed.
 */
const configureSystem = (config, io) => {
  if (config.totalTickets < 10 || config.totalTickets > 1000) {
    io.emit('logUpdate', 'Configuration failed: Total tickets must be between 5 and 1000.');
    return;
  }
  ticketPool = new TicketPool(config.totalTickets, config.maxTicketCapacity);

  io.emit('logUpdate', 'System configured successfully');
  io.emit('ticketUpdate', ticketPool.tickets);

  ticketPool.on('ticketUpdate', (status) => {
    io.emit('ticketUpdate', status);
  });

  ticketPool.on('logUpdate', (message) => {
    io.emit('logUpdate', message);
  });
};

/**
 * @swagger
 * /startSystem:
 *   post:
 *     summary: Start the system
 *     description: |
 *       Starts ticketing operations. Vendors add tickets and customers purchase them.
 *       **Concurrency Logic**:
 *       - Vendors and customers run in separate threads using intervals.
 *       - Mutex ensures thread safety.
 *     responses:
 *       200:
 *         description: System started successfully.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/StartSystemResponse'
 *       400:
 *         description: Failed to start.
 */
const startSystem = (io, config) => {
  if (!ticketPool || ticketPool.tickets < 10 || ticketPool.tickets > 1000) {
    io.emit('logUpdate', 'Cannot start system: Tickets must be between 5 and 1000.');
    return;
  }

  io.emit('logUpdate', 'System started');

  vendorInterval = setInterval(() => {
    ticketPool.addTickets(1, config.ticketReleaseRate).catch((error) =>
      io.emit('logUpdate', `Error in vendor operation: ${error.message}`)
    );
  }, 1000);
  customerInterval = setInterval(() => {
    ticketPool.removeTicket(1).catch((error) =>
      io.emit('logUpdate', `Error in customer operation: ${error.message}`)
    );
  }, 1200);
};

/**
 * @swagger
 * /stopSystem:
 *   post:
 *     summary: Stop the system
 *     description: Stops the ongoing ticketing operations.
 *     responses:
 *       200:
 *         description: System stopped successfully.
 */
const stopSystem = (io) => {
  clearInterval(vendorInterval);
  clearInterval(customerInterval);
  io.emit('logUpdate', 'System stopped');
};

/**
 * @swagger
 * /resetSystem:
 *   post:
 *     summary: Reset the system
 *     description: Resets the ticketing system, clearing all tickets.
 *     responses:
 *       200:
 *         description: System reset successfully.
 */
const resetSystem = (io) => {
  clearInterval(vendorInterval);
  clearInterval(customerInterval);
  ticketPool = new TicketPool(0, 0); // Reset ticket pool
  io.emit('ticketUpdate', 0);
  io.emit('logUpdate', 'System reset');
};

module.exports = { configureSystem, startSystem, stopSystem, resetSystem };

