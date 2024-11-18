const express=require('express');
const bodyParser=require('body-parser');
const connectDB=require('./config/db');

const app=express();
connectDB();


app.use('/api/vendors',require('./routes/vendorRoutes'));
app.use('/api/customers',require('./routes/customerRoutes'));
app.use('/api/bookings',require('./routes/bookingRoutes'));


const PORT=process.env.PORT||8080;
app.listen(PORT,()=>console.log(`Server started on port ${PORT}`))
