
const TicketPool=require('../Models/TicketPool');
let vendorInterval,customerInterval;

//system configuration
const systemConfig=(config,io) =>{
    if(config.totalTickets <1 || config.totalTickets >2000){
        io.emit('logUpdate', 'Error: Total tickets should be between 1 and 2000.');
        return;
    }

    TicketPool =new TicketPool(config.totalTickets,config.maximumTicketCapacity);

    io.emit('logUpdate','System configuration successful');
    io.emit('ticketUpdate',TicketPool.tickets);


    TicketPool.on('ticketUpdate',(data)=>{
        io.emit('ticketUpdate',data);
    });

    TicketPool.on('logUpdate',(logMassage)=>{
        io.emit('logUpdate',logMassage);
    });

};  
    //Start the system
    const activateSystem=(io,config)=>{
        if(!TicketPool || TicketPool.tickets<1 ||TicketPool.tickets>2000){
            io.emit('logUpdate', 'Cannot activate: Ticket count out of range.');
            return;
        }
        io.emit('logUpdate','System Started');
        // Vendor logic using a different release rate approach
        vendorInterval=setInterval(async() => {
            const added=await TicketPool.addTickets(config.ticketsReleaseRate);
            if(!added)clearInterval(vendorInterval);// Stop if no tickets can be removed
            
        }, 1000);
         // Customer logic to remove tickets if available
        customerInterval=setInterval(async() => {
            const removed=await TicketPool.removeTicket();
            if(!removed) clearInterval(customerInterval);// Stop if no tickets can be removed
            
        }, 1500);
        

    };
    //stop the system
    const deActivateSystem=(io)=>{
        clearInterval(vendorInterval);
        clearInterval(customerInterval);
        io.emit('logUpdate','System Stopped');
    };
    //reset the system
    const resetSystem=(io)=>{
        clearInterval(vendorInterval);
        clearInterval(customerInterval);
        TicketPool=new TicketPool(0,0);//reset ticketpool
        io.emit('ticketUpdate',0);
        io.emit('logUpdate', 'System reset complete.');

    };





module.exports={systemConfig,activateSystem,deActivateSystem,resetSystem};