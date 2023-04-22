FROM adoptopenjdk/openjdk16:alpine
MAINTAINER vikaskumarjagga
COPY target/spring-boot-mongo-0.0.1-SNAPSHOT.jar spring-boot-mongo-0.0.1.jar
ENTRYPOINT ["java","-jar","/spring-boot-mongo-0.0.1.jar"]