
FROM openjdk:11.0-oracle
#FROM openjdk:11-jdk-apline

COPY ./target/public-service-0.0.1-SNAPSHOT.jar /home/adidas/

WORKDIR /home/adidas/

RUN sh -c 'touch public-service-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar", "public-service-0.0.1-SNAPSHOT.jar"]