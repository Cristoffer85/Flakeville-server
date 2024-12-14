FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package --no-daemon > build.log 2>&1 || (cat build.log && exit 1)
RUN cat build.log

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]