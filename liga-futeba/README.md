# Sample REST CRUD API with Spring Boot, Mysql, JPA and Hibernate

## Steps to Setup

### 1. Clone the application
https://github.com/dmiranda83/ligafuteba.git

### 2. Create Mysql database
 create database liga_futeba
 
### 3. Change mysql username and password as per your installation
open src/main/resources/application.properties

change spring.datasource.username and spring.datasource.password as per your mysql installation

### 4. Build and run the app using maven

## mvn package
java -jar target/futeba-0.0.1-SNAPSHOT.war

Alternatively, you can run the app without packaging it using -

## mvn spring-boot:run
The app will start running at http://localhost:8080.

## Explore Rest APIs

### 1. Team categories

GET /api/v1/teamCategories

GET /api/v1/teamCategories/{categoryId}

GET /api/v1/teamCategories/list/{categoryName}

POST /api/v1/teamCategories

PUT /api/v1/teamCategories/{categoryId}

DELETE /api/v1/teamCategories

DELETE /api/v1/teamCategories/{categoryId}