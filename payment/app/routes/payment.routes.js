module.exports = app => {
  const payments = require("../controllers/payment.controller.js");
  var router = require("express").Router();
  // Create a new Payment
  router.post("/", payments.create);
  // Retrieve all Payments
  router.get("/", payments.findAll);
  app.use('/payments', router);
};