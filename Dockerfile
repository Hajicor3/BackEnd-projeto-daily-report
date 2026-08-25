# Build stage - imagem específica para Maven
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build

# Copia só pom.xml primeiro (aproveita cache)
COPY pom.xml .
RUN mvn dependency:resolve

# Depois copia o código
COPY src src
RUN mvn clean package -DskipTests

# Runtime stage - imagem pequena
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Cria usuário não-root
RUN addgroup -g 1000 appuser && \
    adduser -D -u 1000 -G appuser appuser

# Copia JAR
COPY --from=builder --chown=appuser:appuser /build/target/daily-report-0.0.1-SNAPSHOT.jar app.jar

# Configurações
ENV JAVA_OPTS="-Xms256m -Xmx512m"
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

USER appuser

