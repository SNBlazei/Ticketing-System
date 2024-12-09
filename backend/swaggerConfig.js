const swaggerJsdoc = require('swagger-jsdoc');

const options = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Real-Time Event Ticketing System API',
      version: '1.0.0',
      description: 'API documentation for the Real-Time Event Ticketing System',
    },
    servers: [
      {
        url: 'http://localhost:5000', // URL for  local server
      },
    ],
  },
  apis: ['./controllers/ticketcontroller.js'], //  path to your controller file
};

const swaggerSpec = swaggerJsdoc(options);
module.exports = swaggerSpec;
