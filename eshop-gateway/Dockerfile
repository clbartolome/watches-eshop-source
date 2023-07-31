# Use the official Go image as the base image for building the binary
FROM golang:1.16 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the Go binary inside the container
RUN CGO_ENABLED=0 GOOS=linux go build -o gateway-service main.go

# Use a minimal base image for the final container
FROM alpine:latest

# Create a non-root user for running the microservice
RUN adduser -D -g '' appuser

# Set the working directory inside the container
WORKDIR /app

# Copy the binary from the build container to the final container
COPY --from=build /app/gateway-service .

# Change ownership of the binary to the non-root user
RUN chown appuser:appuser gateway-service

# Expose the port on which the service listens
EXPOSE 8080

# Switch to the non-root user to run the microservice
USER appuser

# Start the microservice when the container starts
CMD ["./gateway-service"]