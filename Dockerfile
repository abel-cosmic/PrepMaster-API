FROM openjdk:20-ea-4-jdk

COPY target/Prepmaster-API-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
