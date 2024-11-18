const mongoose=require('mongoose');

const ticketSchema=new mongoose.Schema({
    ticketId:String,
    price:String,
    Description:String,
    currentTickets:[{
        type:mongoose.Schema.Types.ObjectId,
        ref:"CurrentTickets"
    }],






})

module.exports=mongoose.model('ticket',ticketSchema)

