FROM openjdk:17
MAINTAINER Gbubemi
COPY /target/product-service-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8005
ENTRYPOINT ["java","-jar","/app.jar"]