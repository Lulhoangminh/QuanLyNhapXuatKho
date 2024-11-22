FROM maven:3.9.9-eclipse-temurin-21
COPY . .
RUN ./mvnw clean package -DskipTests


FROM openjdk:21-slim

COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

