# Watches eShop: Payment

## Run locally

```sh
# Start a mock server (npm install -g json-server)
json-server --watch src/mock/mock-server.json

# Open new terminal and start ng server
ng serve
```

## OpenShift notes

```sh
# https://developers.redhat.com/blog/2019/11/27/handling-angular-environments-in-continuous-delivery-with-red-hat-openshift#how_to_apply_the_configuration_in_red_hat_openshift

# Create configmap
oc create configmap config --from-file=<configMapLocation>/config.json

# Set volume
oc set volume dc/angular --add --type=configmap --configmap-name=config --mount-path=/opt/app-root/src/assets/config --overwrite
```


## NPM and Node notes

```sh
# Init NPM on empty project directory
npm init -y

# Install body-parser and express
npm install express body-parser --save

