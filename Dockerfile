FROM amazoncorretto:11-alpine-jdk
LABEL authors="sijanstu"
MAINTAINER sijanstu
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]