FROM openjdk:21-jdk-slim

ARG JAR_FILE=target/notes-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /app/notes-0.0.1-SNAPSHOT.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "notes-0.0.1-SNAPSHOT.jar"]