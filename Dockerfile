FROM openjdk:8-jdk-alpine
EXPOSE 8089
COPY target/tpAchatProject-1.0.1-SNAPSHOT.jar tpAchatProject-1.0.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-1.0.jar"]
