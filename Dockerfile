# Use Ubuntu as the base image for the build stage
FROM ubuntu:latest AS build

# Update the package manager
RUN  apt-get update

# Install OpenJDK 17
RUN  apt-get install -y openjdk-20-jdk

# Install maven 
RUN apt-get install -y maven

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . .

# Build the Maven project using JDK 17
RUN mvn package

# Use Ubuntu with OpenJDK 17 as the base image for the final stage
FROM ubuntu:latest

# Update the package manager
RUN  apt-get update

# Install OpenJDK 17
RUN  apt-get install -y openjdk-20-jdk

# Expose port 8080
EXPOSE 8080

# Set the working directory to /app
WORKDIR /app

# Copy the executable JAR file from the build stage to the final stage
COPY --from=build /PrepMaster-API/target/Prepmaster-API-0.0.1-SNAPSHOT.jar prepmaster-api.jar

# Set the entrypoint to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
