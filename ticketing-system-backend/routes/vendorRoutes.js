const express = require('express'); 
const router = express.Router(); 

const vendorController = require('../controllers/venodorController'); 

// Define routes for vendors
router.get('/', vendorController.getAllVendors);
router.post('/', vendorController.createVendor);

module.exports = router; 
