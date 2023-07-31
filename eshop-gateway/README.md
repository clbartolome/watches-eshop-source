# Watches eShop: Eshop-Gateway

## Run locally

```sh
# Start a mock server (npm install -g json-server)
json-server --watch mock/mock-server.json

# Open new terminal and configure mocker urls environment variables
export ORDER_SERVICE_URL=http://localhost:3000/order
export CATALOG_SERVICE_URL=http://localhost:3000/catalog
export PAYMENT_SERVICE_URL=http://localhost:3000/payment
export PORT=8081

# Run main class
go run main.go
```

## Create Image