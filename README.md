# Project Inventory
***
The project is an inventory filled with objects of type Inventory. You can perform basic functions and queries.

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)

### General Info
***
The project is at the state that all basic HTTP methods, i.e. PUT, POST, GET and DELETE, are implemented. This enables the functions, adding, updating and deleting objects, as well as querying the objects for their ID and location. The objects are stored in an h2 database. Each object has a name, an ID and a location. 
### Swagger
***

When the server is running, you can view the endpoints via Swagger

[Swagger Link](http://localhost:8080/swagger-ui/index.html#/)

## Technologies
***
A list of technologies used within the project:
* [Gradle](https://gradle.org/install/): Version 8.5
* [Spring Boot](https://spring.io/): Version 3.2.1
* [Java](https://www.java.com/de/download/manual.jsp): Version 17

## Dependencies
***
There are a number of dependencies used in the project. Browse the build.gradle file for details of libraries and versions used.

## Building the Project
***

* ./gradlew bootrun

## Execute the integration tests
***

* ./gradlew test

## Curl Example
***

* curl -X GET http://localhost:8080/inventory

