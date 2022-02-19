# Paylent

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

- Run `gradle nativeCompile` to generate a native image of the project.