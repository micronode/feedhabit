FROM java:openjdk-8-jdk

EXPOSE 8080 8443

WORKDIR /app

COPY gradle gradle
COPY gradlew ./

COPY build.gradle ./
COPY src src

ENTRYPOINT ["/app/gradlew"]

CMD ["run"]

