FROM openjdk:17-oracle
MAINTAINER Hendi Santika <hendisantika@yahoo.co.id>
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
