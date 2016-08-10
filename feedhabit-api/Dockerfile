FROM java:openjdk-8-jdk

EXPOSE 8080 8443

WORKDIR /app

# initialise gradle..
COPY gradle gradle
COPY gradlew ./
RUN ./gradlew

COPY build.gradle ./

RUN ./gradlew assemble && ./gradlew clean copyBundles launcherConfig loggerConfig configurationConfig

ADD . ./

ENTRYPOINT ["/app/gradlew"]

CMD ["run"]

