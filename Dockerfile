# Usar una imagen base con OpenJDK 17
FROM eclipse-temurin:17-jdk-jammy

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el Maven Wrapper y el pom.xml
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

# Copiar el código fuente
COPY src/ src/

# Construir el proyecto
RUN ./mvnw clean package

# Exponer el puerto en el que corre la aplicación
EXPOSE $PORT

# Ejecutar la aplicación
CMD ["java", "-jar", "-Dserver.port=$PORT", "target/mutant-detector-0.0.1-SNAPSHOT.jar"]
