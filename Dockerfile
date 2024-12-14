FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=target /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]