# Paylent

Paylent is a web app to track payments between friends.

This repo contains the back end side.

## Gradle commands

- Run `gradle build` to build the project.
- Run `gradle test` to build the project tests.

---

- Run `gradle run` to run the application. The application starts in http://localhost:8080

## Docker configuration

- Run `gradle dockerBuild` to generate the Docker image.
- Run `docker-compose up -d` for container creation.
---
Two containers are created:
- Micronaut app starts in http://localhost:8080
- MongoDB starts in http://localhost:27017

## Native Image

- Install GraalVM.
- Run `gradle nativeCompile` to generate a native image of the project.

## Documentation

- Swagger Interface documentation: http://localhost:8080/swagger-ui

## Pending changes

- Missing Error Handling and transaction rollback.
- Missing DB relations.
- Micronaut Lombok.
- Missing authentication.
- Missing complex validators.
