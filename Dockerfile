# Use the AdoptOpenJDK base image with Java 17
FROM adoptopenjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container (Assuming the JAR file is already built)
COPY target/Prepmaster-API-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot application is running on (adjust the port number if needed)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
