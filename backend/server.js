const express = require('express');
const swaggerUi = require('swagger-ui-express');
const swaggerJsdoc = require('swagger-jsdoc');
const http = require('http');
const socketIo = require('socket.io');
const cors = require('cors');
const {
  configureSystem,
  startSystem,
  stopSystem,
  resetSystem,
} = require('./controllers/ticketcontroller');

const app = express();
const server = http.createServer(app);

// Swagger setup
const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Ticketing System API',
      version: '1.0.0',
      description: 'API documentation for the ticketing system.',
    },
  },
  apis: ['./controllers/ticketcontroller.js'], // Path to the annotated controller file
};

const swaggerSpec = swaggerJsdoc(swaggerOptions);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));

// Middleware
app.use(cors());
app.use(express.json()); // Parse incoming JSON requests

// Logging middleware to track API requests
app.use((req, res, next) => {
  console.log(`${req.method} ${req.url} - ${new Date().toISOString()}`);
  next();
});

// Socket.IO setup
const io = socketIo(server, {
  cors: {
    origin: '*', // Allow any origin or specify your frontend URL
    methods: ['GET', 'POST'],
  },
});

let systemConfig = null;

// Socket.IO event handling
io.on('connection', (socket) => {
  console.log('A user connected');

  socket.on('configureSystem', (config) => {
    console.log('Received configuration:', config);
    systemConfig = config; // Save configuration for other operations
    configureSystem(config, io);
  });

  socket.on('startSystem', () => {
    if (systemConfig) {
      startSystem(io, systemConfig);
    } else {
      io.emit('logUpdate', 'System configuration missing!');
    }
  });

  socket.on('stopSystem', () => {
    stopSystem(io);
  });

  socket.on('resetSystem', () => {
    resetSystem(io);
  });

  socket.on('disconnect', () => {
    console.log('User disconnected');
  });
});

// Express route handlers (for Swagger documentation)
/**
 * @swagger
 * /configureSystem:
 *   post:
 *     summary: Configure the ticketing system
 *     description: Set system parameters such as total tickets, max capacity, and other configuration options.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               totalTickets:
 *                 type: integer
 *                 description: Total number of tickets to be managed.
 *                 example: 500
 *               maxTicketCapacity:
 *                 type: integer
 *                 description: Maximum capacity of tickets.
 *                 example: 1000
 *     responses:
 *       200:
 *         description: System configured successfully
 *       400:
 *         description: Configuration failed due to invalid parameters
 */
app.post('/configureSystem', (req, res) => {
  const config = req.body;
  if (config.totalTickets < 1 || config.maxTicketCapacity < config.totalTickets) {
    return res.status(400).json({
      message: 'Invalid configuration: Check total tickets and max capacity.'
    });
  }
  systemConfig = config; // Store configuration for socket use
  configureSystem(config, io);
  res.status(200).send('System configured successfully');
});

/**
 * @swagger
 * /startSystem:
 *   post:
 *     summary: Start the ticketing system
 *     description: Starts the system, vendors begin adding tickets and customers start purchasing them.
 *     responses:
 *       200:
 *         description: System started successfully
 *       400:
 *         description: Cannot start the system due to incorrect configuration
 */
app.post('/startSystem', (req, res) => {
  if (systemConfig) {
    startSystem(io, systemConfig);
    res.status(200).send('System started successfully');
  } else {
    res.status(400).send('System configuration missing!');
  }
});

/**
 * @swagger
 * /stopSystem:
 *   post:
 *     summary: Stop the ticketing system
 *     description: Stops the ticketing system, halting ticket addition and purchases.
 *     responses:
 *       200:
 *         description: System stopped successfully
 */
app.post('/stopSystem', (req, res) => {
  stopSystem(io);
  res.status(200).send('System stopped successfully');
});

/**
 * @swagger
 * /resetSystem:
 *   post:
 *     summary: Reset the ticketing system
 *     description: Resets the system, clearing the ticket pool and stopping all operations.
 *     responses:
 *       200:
 *         description: System reset successfully
 */
app.post('/resetSystem', (req, res) => {
  resetSystem(io);
  res.status(200).send('System reset successfully');
});

/**
 * @swagger
 * /status:
 *   get:
 *     summary: Get the status of the system
 *     description: Check if the system is running or stopped.
 *     responses:
 *       200:
 *         description: System status retrieved successfully
 */
app.get('/status', (req, res) => {
  const status = systemConfig ? 'Running' : 'Stopped';
  res.status(200).json({ status });
});

// Start the server
server.listen(5000, () => {
  console.log('Server is running on http://localhost:5000');
});
