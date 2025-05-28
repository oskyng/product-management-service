FROM openjdk:17-jdk-slim

# Crea directorio para app
WORKDIR /app

# Copia JAR y wallet
COPY target/ProductManagement-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la app
EXPOSE 8080

# Ejecuta la app
ENTRYPOINT ["java", "-jar", "app.jar"]