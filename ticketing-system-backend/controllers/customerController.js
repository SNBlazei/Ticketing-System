
const Customer=require('../models/customer');

exports.getAllCustomers=async(req,res)=>{
    try{
        const customers=await Customer.find();
        res.json(customers);

    }catch(err){
        res.status(500).json({message:err.message});

    }
};

exports.createCustomer=async(req,res)=>{
    const customer=new Customer(req,body);
    
    try{
        await customer.save();
        res.status(201).json(customer)

    }catch(err){
        res.status(400).json({message:err.message});


    }
};