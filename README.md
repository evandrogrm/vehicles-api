# Getting Started

### Steps to build application

#### Mysql
To run database place in the root of api (vehicles/api-java) and run

`docker-compose -f docker-db.yml up`


#### Application API
To run the application place in the root of api (vehicles/api-java) and run the commands in sequence:

1. `mvn clean install`
   
This will build and install by maven the application

2. `docker build -t application/vehicles .`

This will create an image called _application/vehicles_

3. `docker-compose -f docker-app.yml up`

This step will turn it up the image we just created

#Contacts

If anyone has any questions or adjustments can contact me on:

* Developer: Evandro Gaspar Ribeiro

* E-mail: evandrogrm@hotmail.com

_Best regards_
