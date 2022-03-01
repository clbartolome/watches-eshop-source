# Watches eShop: Catalog

## Configuration

Done via Environment Variables:

- **DB_HOST**: PostgreSQL host url
- **DB_PORT**: PostgreSQL port
- **DB_NAME**: PostgreSQL database for catalog
- **DB_USER**: PostgreSQL user name
- **DB_PASS**: PostgreSQL user password

## API Documentation & Other Tools

API calls:

- Get all brands [GET]: `/brands`. Example
  ```sh
  curl -X GET http://localhost:8080/brands/ | jq
  ```
- Get all watches [GET]: `/watches`. Example
  ```sh
  curl -X GET http://localhost:8080/watches/ | jq
  ```
- Get all watches by brand [GET]: `/watches/brands/{brandId}`. Example
  ```sh
  curl -X GET http://localhost:8080/watches/brands/1 | jq
  ```

API live documentation and other helpfull resources can be found at:

- Health: `/actuator/health` 

## Run Locally

Requires podman or other container system

```sh
# Start database
podman run -d --name catalog-db \
  -e POSTGRES_USER=develop \
  -e POSTGRES_PASSWORD=develop \
  -e POSTGRES_DB=catalog-db \
  -p 5432:5432 \
  postgres:10.5

# Once the Database is running, start application using Maven
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DDB_HOST=localhost -DDB_PORT=5432 -DDB_NAME=catalog-db -DDB_USER=develop -DDB_PASS=develop"

# Validate
curl localhost:8080/watches | jq
# or
curl localhost:8080/actuator/health
```