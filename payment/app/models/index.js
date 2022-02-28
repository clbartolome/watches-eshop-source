const appConfig = require("../config/app.config.js");

const mongoose = require("mongoose");
mongoose.Promise = global.Promise;

const db = {};

var url = `mongodb://${appConfig.mongo.user}:${appConfig.mongo.pass}@${appConfig.mongo.host}:${appConfig.mongo.port}/${appConfig.mongo.database}?${appConfig.mongo.connectionParameters}`;

db.mongoose = mongoose;
db.url =url;
db.payments = require("./payment.model.js")(mongoose);

module.exports = db;
