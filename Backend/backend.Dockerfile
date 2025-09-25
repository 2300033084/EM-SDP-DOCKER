# Stage 1: Build the Java application with Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy the Maven wrapper files first to leverage Docker's caching
COPY Backend/Employee-Server/mvnw .
COPY Backend/Employee-Server/.mvn/ .mvn

# Copy the pom.xml to download dependencies
COPY Backend/Employee-Server/pom.xml .

# Copy the source code
COPY Backend/Employee-Server/src ./src

# Build the JAR, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Create the final, lightweight image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built JAR from the build stage into the final image
COPY --from=build /app/target/Employee-Server-0.0.1-SNAPSHOT.jar app.jar

# Expose the correct default Spring Boot port
EXPOSE 8080 

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
