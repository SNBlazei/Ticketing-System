const {EventEmitter}=require('events');
const {Mutex}=require('async-mutex'); //Import Mutex

const systemLock=new Mutex(); //for synchronization

class TicketPool extends EventEmitter{
    constructor(totalTickets,maximumTicketCapacity){
        super();
        this.tickets=totalTickets ||0;
        this.maximumTicketCapacity=maximumTicketCapacity ||0;
    }
    // Vendor logic: Add tickets
    async addTickets(count) {
        const release = await ticketLock.acquire(); // Acquire the lock
        try {
          if (this.tickets + count <= this.maximumTicketCapacity && this.tickets + count >= 5) {
            this.tickets += count;
            this.emit('ticketUpdate', this.tickets);
          } else {
            this.emit(
              'logUpdate',
              'Vendor unable to add tickers'
            );
          }
        } finally {
          release(); // Release the lock
        }
      };
      //customer removing tickets with mutex for thread safety
      async removeTicket() {
        const release = await ticketLock.acquire(); // Acquire the lock
        try {
          if (this.tickets > 5) {
            this.tickets -= 1;
            this.emit('ticketUpdate', this.tickets);
          } else {
            this.emit('logUpdate', 'Customer tried to purchase a ticket, but  limit reached!');
          }
        } finally {
          release(); // Release the lock
        }
      };


}
module.exports=TicketPool;


