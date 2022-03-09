const express = require("express");
const cors = require("cors");
const appConfig = require("./app/config/app.config.js");

const app = express();


app.use(cors());
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
app.get('/health/liveness', (req, res) => {
  res.status(200).send('Ok');
});

app.get('/health/readiness', (req, res) => {
  if (db.mongoose.connection.readyState == 1){
    res.status(200).send('Ok');
  }
  res.status(500).send('Application not ready');
  
});

app.get('/', async (_req, res, _next) => {
	// optional: add further things to check (e.g. connecting to dababase)
	const healthcheck = {
		uptime: process.uptime(),
		message: 'OK',
		timestamp: Date.now()
	};
	try {
		res.send(healthcheck);
	} catch (e) {
		healthcheck.message = e;
		res.status(503).send();
	}
});


require("./app/routes/payment.routes")(app);

// set port, listen for requests
const PORT = appConfig.port;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});