# microservices-sandbox

#### user docker compose files in next modules:

- statistics to up mongodb
- currency exchange to up postgres
- conversion multiple to up redis and kafka

## api-gateway:

port: 8765

## statistics-service:

- port: 8300
- http://localhost:8300/swagger-ui/index.html

## conversion-multiple-service:

- port: 8200
- http://localhost:8200/swagger-ui/index.html#/

## currency-conversion-service:

- port: 8100

## currency-exchange-service:

- port: 8000

## limits-service:

- port: 8080

## naming-server: 
 - port: 8761
 - http://localhost:8761

# spring-cloud-config-server:

- port: 8888

- https://github.com/deolexx/microservices-remote-config.git

Kafka UI
http://localhost:8088/ui
