
FROM openjdk:11.0-oracle

COPY ./target/subscription-service-0.0.1-SNAPSHOT.jar /home/adidas/

WORKDIR /home/adidas/

RUN sh -c 'touch subscription-service-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar", "subscription-service-0.0.1-SNAPSHOT.jar"]
