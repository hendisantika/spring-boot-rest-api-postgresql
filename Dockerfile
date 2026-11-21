FROM eclipse-temurin:25-jre
LABEL maintainer="Hendi Santika <hendisantika@yahoo.co.id>"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
