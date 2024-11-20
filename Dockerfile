# Use an official JDK 21 image
FROM eclipse-temurin:21-jdk as builder

# Set the working directory inside the container
WORKDIR /src

# Copy the Spring Boot application JAR file into the container
COPY target/bill-management-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application will run on
EXPOSE 8082

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
