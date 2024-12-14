FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package --no-daemon || cat /root/.m2/repository/org/apache/maven/plugins/maven-clean-plugin/*/maven-clean-plugin-*.log

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]