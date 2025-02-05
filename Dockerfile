
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

RUN apk add --no-cache bash coreutils

ARG MYSQL_HOST
ARG MYSQL_DATABASE
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MAIL_HOST
ARG MAIL_USERNAME
ARG MAIL_PASSWORD
ARG JWT_SECRET

ENV MYSQL_HOST=${MYSQL_HOST}
ENV MYSQL_DATABASE=${MYSQL_DATABASE}
ENV MYSQL_USER=${MYSQL_USER}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}
ENV MAIL_HOST=${MAIL_HOST}
ENV MAIL_USERNAME=${MAIL_USERNAME}
ENV MAIL_PASSWORD=${MAIL_PASSWORD}
ENV JWT_SECRET=${JWT_SECRET}

COPY . .

RUN chmod +x gradlew

RUN java -version

RUN ls -l /app

RUN #sh ./gradlew clean build --no-daemon --stacktrace
RUN ./gradlew clean build --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
