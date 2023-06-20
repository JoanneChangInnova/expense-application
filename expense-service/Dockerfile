FROM gradle:7.6.1-alpine AS BUILDER
COPY . /app
WORKDIR /app
RUN ./gradlew bootJar && \
    mv build/libs/*.jar expense.jar

FROM openjdk:17-alpine
VOLUME /logs
COPY --from=BUILDER /app/expense.jar /app/expense.jar
EXPOSE 5555
ENTRYPOINT ["java", "-jar", "/app/expense.jar"]