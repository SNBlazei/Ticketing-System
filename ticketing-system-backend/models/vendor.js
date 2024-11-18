const mongoose=require('mongoose');

const vendorSchema=new mongoose.Schema({
    vendorId:String,
    name:String,
    email:String,
    soldTickets:[{
        type:mongoose.Schema.Types.ObjectId,
        ref:"SoldTicket"
    }],






})

module.exports=mongoose.model('Vendor',vendorSchema)

