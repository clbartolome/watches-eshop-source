# Watches eShop: order

Quarkus MicroService to Watch eShop Orders.

Extensions installed:
- RestEasy: `mvn quarkus:add-extension -Dextensions="resteasy-jackson"`
- Health: `mvn quarkus:add-extension -Dextensions="quarkus-smallrye-health"`
- OpenApi: `mvn quarkus:add-extension -Dextensions="smallrye-openapi"`
- Metrics: `mvn quarkus:add-extension -Dextensions="quarkus-smallrye-metrics"`
- Postgresql (panache): `mvn quarkus:add-extension -Dextensions="quarkus-hibernate-orm-panache, quarkus-jdbc-postgresql, quarkus-jdbc-h2"`

! TIP: use `mvn quarkus:list-extensions` to get a list with all extensions.

## API Documentation & Other Tools

API calls:

- Get all orders [GET]: `/orders`. Example
  ```sh
  curl -X GET http://localhost:8080/orders/ | jq
  ```
- Create order [POST]: `/orders`. Example
  ```sh
  curl -X POST -d '{"detail": "Carlos Lopez - Omega Seamaster 3 (5000 €) - 07/10/2022"}' -H "Content-Type: application/json" http://localhost:8080/orders/ -v
  ```
- Update order state (next one) [PUT]: `/orders/next/{id}`. Example
  ```sh
  curl -X PUT  http://localhost:8080/orders/next/1 | jq
  ```

API live documentation and other helpfull resources can be found at:

- Health: `/q/health/live` | `/q/health/ready` | `/q/health`
- OpenApi: `/openapi`
- Swagger: `/q/swagger-ui`
- Metrics: `/q/metrics/application` or `curl -H "Accept: application/json" http://localhost:8080/q/metrics/application`


## Configuration

Done via Environment Variables (quarkus prod profile - quarkus.profile=prod):

- **DB_HOST**: PostgreSQL host url
- **DB_PORT**: PostgreSQL port
- **DB_NAME**: PostgreSQL database for catalog
- **DB_USER**: PostgreSQL user name
- **DB_PASS**: PostgreSQL user password

## Run Locally

- Create PostgressDB:
```sh
podman run -d --name order-db \
  -e POSTGRES_USER=develop \
  -e POSTGRES_PASSWORD=develop \
  -e POSTGRES_DB=order-db \
  -p 5432:5432 \
  postgres:10.5
```

- Run Application in development mode:
```shell script
mvn compile quarkus:dev
```



