FROM openjdk:8u171-jre-slim
LABEL authors="sijanstu"
MAINTAINER sijanstu
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
