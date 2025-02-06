# 1️⃣ 빌드 스테이지
FROM amazoncorretto:17-alpine AS builder
WORKDIR /app

# 필수 패키지 설치
RUN apk add --no-cache bash coreutils

# Gradle Wrapper 복사 및 실행 권한 부여
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src/

# Gradle 실행 권한 부여
RUN chmod +x gradlew

# Gradle 캐시 최적화 (dependencies 미리 다운로드)
RUN ./gradlew dependencies --no-daemon

# Spring Boot 애플리케이션 빌드
RUN ./gradlew clean build --no-daemon --stacktrace

# 2️⃣ 실행 스테이지
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Spring Boot 실행을 위한 포트 노출
EXPOSE 8080

# 컨테이너 실행 시 JAR 파일 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
