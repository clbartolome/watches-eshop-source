# FROM ubi8/nginx-120
# COPY nginx.conf /etc/nginx/nginx.conf
# COPY ./dist/eshop-ui /usr/share/nginx/html



FROM registry.access.redhat.com/ubi8/nginx-120

# Add application sources
ADD nginx.conf "${NGINX_CONF_PATH}"
ADD dist/eshop-ui .

RUN ls

# Run script uses standard ways to run the application
CMD nginx -g "daemon off;"


### BUILD AND RUN
# FROM node:12.7-alpine AS build
# WORKDIR /usr/src/app
# COPY package.json package-lock.json ./
# RUN npm install
# COPY . .
# RUN npm run build

# FROM nginx:1.17.1-alpine
# COPY nginx.conf /etc/nginx/nginx.conf
# COPY --from=build /usr/src/app/dist/eshop-ui /usr/share/nginx/html
