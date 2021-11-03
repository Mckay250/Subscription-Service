
FROM openjdk:11.0-oracle

COPY ./target/email-service-0.0.1-SNAPSHOT.jar /home/adidas/

WORKDIR /home/adidas/

RUN sh -c 'touch email-service-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar", "email-service-0.0.1-SNAPSHOT.jar"]
