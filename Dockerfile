# Stage 1: Build the application with Maven
FROM maven:3.6.3-openjdk-8-slim AS build

# Set working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application with a lightweight JRE image
FROM openjdk:8-jre-slim

# Set application details
ENV APP_NAME=render-schedulers
ENV APP_VERSION=0.0.1-SNAPSHOT
ENV JAR_FILE=${APP_NAME}-${APP_VERSION}.jar

# Create a non-root user for security
RUN useradd -ms /bin/bash appuser
USER appuser

# Copy the built JAR file from the build stage
COPY --from=build /app/target/${JAR_FILE} app.jar

# Expose the port your app runs on (default 8080 for Spring Boot)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
