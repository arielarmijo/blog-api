FROM openjdk:11.0.15-jdk-slim-bullseye
COPY target/blogapi-0.0.1.jar blogapi-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/blogapi-0.0.1.jar"]
