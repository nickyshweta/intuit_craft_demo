# intuit_craft_demo

Problem Statement: Create a Twitter like application for Internal Employees

Design
------------
To be Updated


Problem
------------
Twitter like App for given API Challenge

Tech Spec
---------
1. Java 8
2. Spring Boot, 
3. In-memory database, 
4. Embedded Tomcat
5. Swagger
6. Jacoco/EclEmma
7. Mockito/Junit

Run Spring Boot Application using one of the below method
-----
Run jar from build
------------------
java -jar -Dspring.profiles.active=test target\craftDemo-0.0.1-SNAPSHOT.jar


--Or-- 

Maven
-----
mvn spring-boot:run



Usage
-----
Please follow below steps using swagger UI,curl or any rest client 


Initial Set up (Data set up)
---------------------------
data.sql loads the Users, Tweets and Followers to DB on application start



Post tweet
---------------------------
curl -H "Authorization:V2ViVG9rZW4gW3VzZXJOYW1lPWFkbWluLCBwYXNzd29yZD1hZG1pbjEyM10="  -X POST "http://localhost:8080/twitter/v1/tweet" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"content\": \"This is test message\", \"userId\": 5}"

Get feed
---------------------------
curl -H "Authorization:V2ViVG9rZW4gW3VzZXJOYW1lPWFkbWluLCBwYXNzd29yZD1hZG1pbjEyM10="  -X GET "http://localhost:8080/twitter/v1/feed" -H "accept: application/json"

Follow User
---------------------------
curl -H "Authorization:V2ViVG9rZW4gW3VzZXJOYW1lPWFkbWluLCBwYXNzd29yZD1hZG1pbjEyM10="  -X PUT "http://localhost:8080/twitter/v1/follow" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"followerId\": 5, \"userId\": 1}"

UnFollow User
--------------------------
curl -H "Authorization:V2ViVG9rZW4gW3VzZXJOYW1lPWFkbWluLCBwYXNzd29yZD1hZG1pbjEyM10="  -X DELETE "http://localhost:8080/twitter/v1/unfollow" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"followerId\": 5, \"userId\": 1}"
