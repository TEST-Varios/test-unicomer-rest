FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
EXPOSE 9060
ARG JAR_FILE=target/test-unicomer-restapi-1.0.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]