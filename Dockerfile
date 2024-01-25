FROM openjdk:17.0.2-jdk-slim
EXPOSE 9999
COPY target/vnk-authorization-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
