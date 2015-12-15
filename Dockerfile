FROM java:openjdk-8-jdk

EXPOSE 8080 8443

WORKDIR /app

COPY gradle gradle
COPY build.gradle gradlew ./

RUN ./gradlew assemble && ./gradlew clean copyBundles launcherConfig loggerConfig configurationConfig

ADD . ./

ENTRYPOINT ["/app/gradlew"]

CMD ["run"]

