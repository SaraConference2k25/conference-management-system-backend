# ---------- Stage 1: Build ----------
FROM maven:3.9.8-eclipse-temurin-17 AS build

WORKDIR /app

# Cache dependencies first (VERY IMPORTANT)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Now copy source and package
COPY src ./src
RUN mvn -B -DskipTests package


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
