
const mongoose=require('mongoose');

const connectDB=async()=>{
    try{
        await mongoose.connect('mongodb://localhost:27017/ticketing-system',{
            useNewUrlParser:true,
            useUnifiedTopology:true,



        });
        console.log("MonogDB Connected");

    }catch(err){
        console.error(err.massage);
        process.exit(1);

    }
};

module.exports=connectDB;
