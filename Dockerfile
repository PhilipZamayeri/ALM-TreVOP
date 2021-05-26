FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

ARG JAR_FILE=target/*.jar

WORKDIR /3VOP_app

RUN java --version

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]