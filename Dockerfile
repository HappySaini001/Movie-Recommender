# 1. Build the App
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Run the App
# We use Eclipse Temurin here because the old openjdk image is deprecated
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/movie-recommender-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]