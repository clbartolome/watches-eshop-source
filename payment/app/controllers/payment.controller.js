const db = require("../models");

const Payment = db.payments;

// Create and Save a new Payment
exports.create = (req, res) => {
  // Validate request
  if (!req.body.title) {
    res.status(400).send({ message: "Content can not be empty!" });
    return;
  }

  // Create a Payment
  const payment = new Payment({
    name: req.body.name,
    description: req.body.description,
    card: req.body.card,
    price: req.body.price
  });

  // Save Payment in the database
  payment
    .save(payment)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Payment."
      });
    });
};

// Retrieve all Payments from the database
exports.findAll = (req, res) => {
  Payment.find()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving payments."
      });
    });
};
