const { EventEmitter } = require('events');
const { Mutex } = require('async-mutex'); // Mutex for thread safety

const ticketLock = new Mutex();

class TicketPool extends EventEmitter {
  constructor(totalTickets, maxCapacity) {
    super();
    this.tickets = totalTickets || 0;
    this.maxCapacity = maxCapacity || 0;
  }

  async addTickets(vendorId, count) {
    const release = await ticketLock.acquire();
    try {
      if (this.tickets + count <= this.maxCapacity) {
        this.tickets += count;
        this.emit('ticketUpdate', this.tickets);
        this.emit(
          'logUpdate',
          `Vendor ${vendorId} added ${count} tickets. Current count: ${this.tickets}`
        );
      } else {
        this.emit(
          'logUpdate',
          `Vendor ${vendorId} could not add tickets. Capacity exceeded.`
        );
      }
    } finally {
      release();
    }
  }

  async removeTicket(customerId) {
    const release = await ticketLock.acquire();
    try {
      if (this.tickets > 0) {
        this.tickets -= 1;
        this.emit('ticketUpdate', this.tickets);
        this.emit(
          'logUpdate',
          `Customer ${customerId} purchased a ticket. Current count: ${this.tickets}`
        );
      } else {
        this.emit(
          'logUpdate',
          `Customer ${customerId} could not purchase a ticket. Tickets sold out.`
        );
      }
    } finally {
      release();
    }
  }
}

module.exports = TicketPool;
