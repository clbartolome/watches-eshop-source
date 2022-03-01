# Watches eShop: Payment

## Configuration

Done via Environment Variables:

- **PORT**: NodeJS application exposed port (default value: 5000)
- **DB_HOST**: MongoDB host url (default value: "localhost")
- **DB_PORT**: MongoDB port (default value: 27017)
- **DB_NAME**: MongoDB database for payments (default value: "payment-db",)
- **DB_PARAMS**: MongoDB connection parameters (default value: "authSource=admin")
- **DB_USER**: MongoDB user name(default value: "admin")
- **DB_PASS**: MongoDB user password (default value: "pass)

## API Documentation & Other Tools

API calls:

- Get all brands [GET]: `/payments`. Example
  ```sh
  curl -X GET http://localhost:5000/payments/ | jq
  ```
- Create order [POST]: `/payments`. Example
  ```sh
  curl -X POST \
    -d '{"name": "Carlos López","description": "Rolex - Oyster Perpetual","card": "05891020887217261627","price": 7500}' \
    -H "Content-Type: application/json" \
    http://localhost:5000/payments/ -v
  ```

API live documentation and other helpfull resources can be found at:

- Health: `/health/liveness` | `/health/readiness`

## Run locally

```sh
# Create a Network for mongo and mongo express
podman network create mongo-express-network

# Start mongoDB
podman run -d \
  -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=admin \
  -e MONGO_INITDB_ROOT_PASSWORD=pass \
  --name mongo-payments-server \
  --net mongo-express-network \
  mongo

# Start Mongo express

podman run -d \
  -p 8081:8081 \
  -e ME_CONFIG_MONGODB_ADMINUSERNAME=admin \
  -e ME_CONFIG_MONGODB_ADMINPASSWORD=pass \
  -e ME_CONFIG_MONGODB_SERVER=mongo-payments-server \
  --name mongo-express \
  --net mongo-express-network \
  mongo-express

# Open new terminal and start nodejs app with default properties
node server.js

# Validate
curl localhost:5000/payments -v
```


## Configuration notes

Followed: (https://www.bezkoder.com/node-express-mongodb-crud-rest-api/)



