# Watches eShop: Catalog

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