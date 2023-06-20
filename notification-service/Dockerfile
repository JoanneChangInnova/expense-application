FROM gradle:7.6.1-alpine AS BUILDER
COPY . /app
WORKDIR /app
RUN ./gradlew bootJar && \
    mv build/libs/*.jar notification.jar

FROM openjdk:17-alpine
VOLUME /logs
COPY --from=BUILDER /app/notification.jar /app/notification.jar
EXPOSE 5556
ENTRYPOINT ["java", "-jar", "/app/notification.jar"]