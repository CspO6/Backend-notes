# Usar una imagen base con Java
FROM openjdk:21-jdk-slim

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por Maven
COPY target/notes-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080 (puerto predeterminado de Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]