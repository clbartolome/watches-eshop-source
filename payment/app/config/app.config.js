module.exports = {
  port: process.env.PORT || 5000,
  mongo: {
    host: process.env.DB_HOST || "localhost",
    port: process.env.DB_PORT || 27017,
    database: process.env.DB_NAME || "payment-db", 
    connectionParameters: process.env.DB_PARAMS || "authSource=admin",
    user: process.env.DB_USER || "admin",
    pass: process.env.DB_PASS || "pass"
  }
};