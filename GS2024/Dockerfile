FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

COPY --from=builder /app/target/GS2024-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
