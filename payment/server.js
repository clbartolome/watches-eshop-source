const express = require("express");
const cors = require("cors");
const appConfig = require("./app/config/app.config.js");

const app = express();

var corsOptions = {
  origin: "http://localhost:5000"
};

app.use(cors(corsOptions));
// parse requests of content-type - application/json
app.use(express.json());
// parse requests of content-type - application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));

// Database connection
const db = require("./app/models");
db.mongoose
  .connect(db.url, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(() => {
    console.log("Connected to the database!");
  })
  .catch(err => {
    console.log("Cannot connect to the database!", err);
    process.exit();
  });

// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to Payments [Watches eShop]. Payments API in /payments" });
});


require("./app/routes/payment.routes")(app);

// set port, listen for requests
const PORT = appConfig.port;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});