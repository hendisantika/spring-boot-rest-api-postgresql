FROM openjdk:17-oracle
LABEL maintainer="Hendi Santika <hendisantika@yahoo.co.id>"
LABEL description="This example Spring Boot REST API PostgreSQL."
MAINTAINER Hendi Santika <hendisantika@yahoo.co.id>
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
