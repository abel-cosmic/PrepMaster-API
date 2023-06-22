FROM ubuntu:latest

# Update package lists and install necessary dependencies
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    postgresql

# Set the working directory inside the container
WORKDIR /PrepMaster-API

# Copy the Spring Boot JAR file into the container
COPY ../target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Expose the port on which your Spring Boot API will run (if applicable)
EXPOSE 8080

# Specify the command to run when the container starts
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar demo.jar"]
