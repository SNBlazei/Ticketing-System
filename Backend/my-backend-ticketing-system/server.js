const express = require('express');
const http = require('http');

const socketIo = require('socket.io');
const cors = require('cors');


const {
    systemConfig,
    activateSystem,
    deactivateSystem,
    resetSystem,


}=require('../my-backend-ticketing-system/Controllers/TicketController');

const app=express();
const server=http.createServer(app);

app.use(cors());


const io=socketIo(server,{
    cors:{
        origin:'*',// Frontend or client origin allowed
        methods: ['GET', 'POST'],


    },
})

let configure=null;

io.on('connection',(socket)=>{
    console.log('User connected');


    socket.on('systemConfig',(settings)=>{
        console.log('Received Configuration:',settings);
        configure=settings;
        systemConfig(settings,io);
    });

    socket.on('startSystem', () => {
        if (configuration) {
          activateSystem(io, configuration);
        } else {
          io.emit('logUpdate', 'Cannot start: Missing system configuration.');
        }
      });

    socket.on('stopSystem', () => {
        deactivateSystem(io);
      });


      socket.on('resetSystem', () => {
        resetSystem(io);
      });

      socket.on('disconnect', () => {
        console.log('User disconnected');
      });


     server.listen(5000,()=>{
        console.log('Server is listening on port 5000');

     }) ;




})




