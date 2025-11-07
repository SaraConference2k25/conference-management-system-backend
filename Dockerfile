# Dockerfile
FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /workspace

# copy only pom first for better cache usage
COPY pom.xml .
COPY src ./src

# build the application (skip tests for faster builds; remove -DskipTests to run tests)
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app

# Optional: set a default JAVA_OPTS, can be overridden when running the container
ENV JAVA_OPTS=""

# copy the built jar from the builder stage (matches the first jar in target/)
COPY --from=builder /workspace/target/*.jar app.jar

EXPOSE 8069

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
