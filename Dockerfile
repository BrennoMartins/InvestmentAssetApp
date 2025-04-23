FROM gradle:8.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
