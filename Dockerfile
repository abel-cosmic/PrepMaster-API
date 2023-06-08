FROM openjdk:11-jdk-slim

LABEL authors="abelcosmic"

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/PrepMaster-API.jar app.jar

# Expose the port on which your Spring Boot API will run (if applicable)
EXPOSE 8080

# Specify the command to run when the container starts
CMD ["java", "-jar", "app.jar"]
