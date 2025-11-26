# Mutant Detector API

API desarrollada en **Spring Boot** para detectar si un ADN corresponde a un mutante, según los requisitos del examen de MercadoLibre.

---

## 📌 Descripción del Proyecto
Este proyecto implementa una API REST que permite:
- Verificar si un ADN es mutante mediante un endpoint POST.
- Almacenar los ADN verificados en una base de datos **H2**.
- Obtener estadísticas de las verificaciones realizadas mediante un endpoint GET.

---

## 🛠 Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 3.5.7**
- **Maven**
- **H2 Database** (base de datos embebida)
- **JUnit 5** y **Mockito** para tests
- **Jacoco** para medir cobertura de código
- **Docker** para despliegue

---

## 📂 Estructura del Proyecto
```
mutant-detector/
├── src/
│   ├── main/
│   │   ├── java/com/mercadolibre/mutant_detector/
│   │   │   ├── controller/       # Controladores REST
│   │   │   ├── model/            # Entidades JPA
│   │   │   ├── repository/       # Repositorios Spring Data JPA
│   │   │   └── service/          # Lógica de negocio
│   │   └── resources/            # Archivos de configuración
│   └── test/                     # Tests unitarios y de integración
├── Dockerfile                    # Configuración para Docker
├── pom.xml                       # Dependencias y configuración de Maven
└── README.md                     # Documentación del proyecto
```

---

## 🚀 Instalación y Ejecución

### Requisitos Previos
- Java 17 o superior
- Maven 3.6.3 o superior
- Docker (opcional, para despliegue)

### Pasos para Ejecutar el Proyecto Localmente
1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/nikitoh33/mutant-detector.git
   cd mutant-detector
   ```

2. **Compilar y ejecutar la aplicación**:
   ```bash
   mvn spring-boot\:run
   ```

3. **Acceder a la aplicación**:
   - La API estará disponible en: [http://localhost:8080](http://localhost:8080)
   - Consola de H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     - **JDBC URL**: `jdbc:h2:mem:testdb`
     - **User Name**: `sa`
     - **Password**: (dejar vacío)

---

## 📡 Endpoints Disponibles

### 1. Verificar si un ADN es mutante
- **URL**: `POST /mutant`
- **Body**:
  ```json
  {
    "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
  }
  ```
- **Respuestas**:
  - `200 OK`: El ADN es mutante.
  - `403 Forbidden`: El ADN no es mutante.

### 2. Obtener estadísticas de verificaciones
- **URL**: `GET /stats`
- **Respuesta**:
  ```json
  {
    "count_mutant_dna": 2,
    "count_human_dna": 3,
    "ratio": 0.666
  }
  ```

---

## 🧪 Pruebas
El proyecto incluye:
- **Tests unitarios** para la lógica de negocio (`MutantService`).
- **Tests de integración** para los controladores (`MutantController`, `StatsController`).
- **Tests para el repositorio** (`DnaRecordRepository`).

### Ejecución de Tests
```bash
mvn test
```

### Cobertura de Código
Para generar el informe de cobertura con Jacoco:
```bash
mvn jacoco\:report
```
El informe se genera en `target/site/jacoco/index.html`. **Cobertura actual: >80%**.

---

## 📊 Base de Datos H2
- La base de datos **H2** se configura automáticamente al iniciar la aplicación.
- Tabla `DNA_RECORD`: Almacena los ADN verificados con su respectivo hash y resultado (mutante o no).

---

## 🔧 Configuración
El archivo `application.properties` incluye la configuración para H2 y Spring Boot:
```properties
spring.datasource.url=jdbc\:h2\:mem\:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

---

## 📦 Despliegue
### Despliegue Local
Para generar el `.jar` ejecutable:
```bash
mvn package
```
El archivo `.jar` se generará en `target/mutant-detector-0.0.1-SNAPSHOT.jar`. Puedes ejecutarlo con:
```bash
java -jar target/mutant-detector-0.0.1-SNAPSHOT.jar
```

### Despliegue con Docker
1. **Construir la imagen de Docker**:
   ```bash
   docker build -t mutant-detector .
   ```

2. **Ejecutar el contenedor**:
   ```bash
   docker run -p 8080:8080 mutant-detector
   ```

---

## 📝 Diagrama de Secuencia
```mermaid
sequenceDiagram
    participant Cliente
    participant API
    participant BaseDeDatos

    Cliente->>API: POST /mutant (JSON con ADN)
    API->>API: Valida y procesa ADN (isMutant)
    API->>BaseDeDatos: Guarda resultado en DNA_RECORD
    BaseDeDatos-->>API: Confirmación
    API-->>Cliente: 200 OK o 403 Forbidden
```

---

## 🤝 Contribuciones
Este proyecto fue desarrollado como parte de un examen técnico para MercadoLibre. ¡Las contribuciones son bienvenidas!

---

## 📄 Licencia
Este proyecto está bajo la licencia MIT.

---

## 📬 Contacto
Para cualquier duda o sugerencia, contacta a:
- **Nombre**: Nicolás Quispe
- **Email**: tu-email@example.com
