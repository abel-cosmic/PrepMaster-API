#
# Build stage
#
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
