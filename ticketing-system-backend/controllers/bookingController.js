
const Booking=require('../models/booking');

exports.createBooking=async(req,res)=>{
    const booking=new Booking(req,body);
    try{
        await Booking.save();
        res.status(201).json(booking)


    }catch(err){
        res.status(400).json({message:err.message});


    }
};