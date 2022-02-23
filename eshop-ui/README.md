# EshopUi

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


## NPM and Angular notes

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 13.2.4:
```sh
npm install @angular/cli -g

ng new eshop-ui

npm install bootstrap

ng g c components/watch-list

```

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

### Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

Followed tutorial: [link](https://www.positronx.io/mean-stack-tutorial-angular-7-crud-bootstrap/)
