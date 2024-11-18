const mongoose=require('mongoose');


const bookingSchema=new mongoose.Schema({
    vendor:{
        type:mongoose.Schema.Types.ObjectId,
        ref:'Vendor'

    },

    customer:{
        type:mongoose.Schema.Types.ObjectId,
        ref:'Customer'

    },

    ticket:{
        type:mongoose.Schema.Types.ObjectId,
        ref:'Ticket'
    },

    bookingType:String,
    dateGiven:{
        type:Date,
        default:Date.now
    }


});
module.exports=mongoose.model('Booking',bookingSchema);