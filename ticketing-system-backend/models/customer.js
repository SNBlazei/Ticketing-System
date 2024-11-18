const mongoose=require('mongoose');

const customerSchema=new mongoose.Schema({
    customerId:String,
    name:String,
    email:String,
    telePhone:String,
    tickets:[{
        type:mongoose.Schema.Types.ObjectId,
        ref:"Ticket"
    }],






})

module.exports=mongoose.model('Customer',customerSchema)