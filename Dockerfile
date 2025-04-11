
 # Use a lightweight Java image
FROM openjdk:21-jdk-slim 

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/notes-0.0.1-SNAPSHOT.jar notes-0.0.1-SNAPSHOT.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "notes-0.0.1-SNAPSHOT .jar"]

FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=your_root_password
ENV MYSQL_DATABASE=notesdb
ENV MYSQL_USER=notes
ENV MYSQL_PASSWORD=your_password

EXPOSE 3306