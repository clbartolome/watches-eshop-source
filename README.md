# watches-eshop-source

## Application Diagram

![architecture](architecture.png)

## Components
- [Catalog](catalog/README.md)
- [eShop User Interface](eshop-ui/README.md)
- [Payment](payment/README.md)
- [Order](order/README.md)

## OpenShift Deployment

- Create project

```sh
# Create project
oc new-project watches-eshop

```

- Deploy Catalog
```sh
# Postgresql
oc new-app postgresql-persistent \
  --param DATABASE_SERVICE_NAME=catalog-db \
  --param POSTGRESQL_USER=user \
  --param POSTGRESQL_PASSWORD=pass \
  --param POSTGRESQL_DATABASE=catalog-db \
  -n watches-eshop

# Postgresql Labels
oc label dc catalog-db \
app.kubernetes.io/part-of=watches-eshop \
app.openshift.io/runtime=postgresql \
-n watches-eshop

# Catalog
oc new-app --name=catalog \
  https://github.com/clbartolome/watches-eshop-source --context-dir=catalog \
  -i ubi8-openjdk-11:1.3 \
  -e DB_HOST=catalog-db \
  -e DB_PORT=5432 \
  -e DB_NAME=catalog-db \
  -e DB_USER=user \
  -e DB_PASS=pass \
  -n watches-eshop

# Expose service
oc expose svc catalog

# Validate
curl http://catalog-watches-eshop.{cluster-domain}/watches | jq

# Catalog Labels
oc label deploy catalog \
app.kubernetes.io/part-of=watches-eshop \
app.openshift.io/runtime=spring-boot \
-n watches-eshop

# Catalog Annotations
oc annotate deploy catalog app.openshift.io/connects-to='[{"apiVersion":"apps.openshift.io/v1","kind":"DeploymentConfig","name":"catalog-db"}]' -n watches-eshop
```

- Deploy Order
```sh
# Postgresql
oc new-app postgresql-persistent \
  --param DATABASE_SERVICE_NAME=order-db \
  --param POSTGRESQL_USER=user \
  --param POSTGRESQL_PASSWORD=pass \
  --param POSTGRESQL_DATABASE=order-db \
  -n watches-eshop

# Postgresql Labels
oc label dc order-db \
app.kubernetes.io/part-of=watches-eshop \
app.openshift.io/runtime=postgresql \
-n watches-eshop

# Catalog
oc new-app --name=order \
  https://github.com/clbartolome/watches-eshop-source --context-dir=order \
  -i ubi8-openjdk-11:1.3 \
  -e DB_HOST=order-db \
  -e DB_PORT=5432 \
  -e DB_NAME=order-db \
  -e DB_USER=user \
  -e DB_PASS=pass \
  -n watches-eshop

# Expose service
oc expose svc order

# Validate
curl http://order-watches-eshop.{cluster-domain}/watches | jq

# Catalog Labels
oc label deploy order \
app.kubernetes.io/part-of=watches-eshop \
app.openshift.io/runtime=quarkus \
-n watches-eshop

# Catalog Annotations
oc annotate deploy order app.openshift.io/connects-to='[{"apiVersion":"apps.openshift.io/v1","kind":"DeploymentConfig","name":"order-db"}]' -n watches-eshop
```