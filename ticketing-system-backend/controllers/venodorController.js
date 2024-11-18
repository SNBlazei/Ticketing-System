const Vendor = require('../models/vendor');

// Fetch all vendors
exports.getAllVendors = async (req, res) => {
    try {
        const vendors = await Vendor.find();
        res.json(vendors);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
};

// Create a new vendor
exports.createVendor = async (req, res) => {
    const vendor = new Vendor(req.body); // Corrected here
    
    try {
        await vendor.save(); // Corrected here
        res.status(201).json(vendor); // 201 for resource creation
    } catch (err) {
        res.status(400).json({ message: err.message }); // 400 for bad request
    }
};
