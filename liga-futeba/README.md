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

## Swagger documentation
http://localhost:8080/swagger-ui.html

## Explore Rest APIs

### 1. Team categories

GET /api/v1/teamCategories

GET /api/v1/teamCategories/{id}

GET /api/v1/teamCategories/list/{name}

GET /api/v1/teamCategories/list/{year}

POST /api/v1/teamCategories

PUT /api/v1/teamCategories/{id}

DELETE /api/v1/teamCategories

DELETE /api/v1/teamCategories/{id}

### 2. Game places

GET /api/v1/places

GET /api/v1/places/{id}

GET /api/v1/places/list/{name}

POST /api/v1/places

PUT /api/v1/places/{id}

DELETE /api/v1/places

DELETE /api/v1/places/{id}

### 3. Player positions

GET /api/v1/playerPositions

GET /api/v1/playerPositions/{id}

GET /api/v1/playerPositions/list/{name}

POST /api/v1/playerPositions

PUT /api/v1/playerPositions/{id}

DELETE /api/v1/playerPositions

DELETE /api/v1/playerPositions/{id}

### 4. Players

GET /api/v1/players

GET /api/v1/players/{id}

GET /api/v1/players/list/{name}

GET /api/v1/players/list/{year}

POST /api/v1/players

PUT /api/v1/players/{id}

DELETE /api/v1/players

DELETE /api/v1/players/{id}

### 5. Teams

GET /api/v1/teams

GET /api/v1/teams/{id}

GET /api/v1/teams/list/{name}

GET /api/v1/teams/list/{year}

POST /api/v1/teams

PUT /api/v1/teams/{id}

DELETE /api/v1/teams

DELETE /api/v1/teams/{id}

### 6. Games

GET /api/v1/games

GET /api/v1/games/{id}

GET /api/v1/games/list/{year}

POST /api/v1/games

PUT /api/v1/games/{id}

DELETE /api/v1/games

DELETE /api/v1/games/{id}