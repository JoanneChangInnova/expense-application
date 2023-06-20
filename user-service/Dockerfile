FROM gradle:7.6.1-alpine AS BUILDER
COPY . /app
WORKDIR /app
RUN ./gradlew bootJar && \
    mv build/libs/*.jar user.jar

FROM openjdk:17-alpine
VOLUME /logs
COPY --from=BUILDER /app/user.jar /app/user.jar
EXPOSE 5557
ENTRYPOINT ["java", "-jar", "/app/user.jar"]
