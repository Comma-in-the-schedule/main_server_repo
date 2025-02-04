
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# 필수 패키지 설치 (bash, coreutils 등)
RUN apk add --no-cache bash coreutils

COPY . .

RUN chmod +x gradlew

RUN ./gradlew clean build --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
