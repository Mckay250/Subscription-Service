# Subscription-Service
An application built on the micro-service architecture for subscriptions using with Java's Spring Framework

This project consists of 3 microservices(Public service a.k.a gateway server, Subscription service and Email service)

Docker is used in containerizing the application so ensure you have docker set up on your computer before attempting to run the services below

Steps to follow when running the application

# Step 1
clone the project or download the zip and extract into a directory on your pc

# Step 2
## cd into the dirctory and you should find five(5) sub-directories.

## cd into kafka-compose and run the command below
  docker-compose up -d

# Step 3
## Run the public service by following the commands below
  cd public-service/
  ## build the jar file
  mvn package
  ## build a docker image using the docker file
  docker build -t public-service:latest . 
  ## create and run a container from the docker image built above
  docker-compose up -d 
  

# Step 4
## Run the email service by following the commands below
  cd email-service/
  ## build the jar file
  mvn package
  ## build a docker image using the docker file
  docker build -t email-service:latest .
  ## create and run a container from the docker image built above
  docker-compose up -d 


# Step 5
## Run the subscription service by following the commands below
  cd subscription-service/
  ## build the jar file
  mvn package   
  ## build a docker image using the docker file
  docker build -t subscription-service:latest .
  ## create and run a container from the docker image built above
  docker-compose up -d 


