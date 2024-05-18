# Project name Flakeville Ski Resort Application - with PowderTracker®

## Description

A Java Spring Boot application that serves as a server for a fictive Ski Resort Application. 

The application uses various things like Spring Security 6 with JSON Web Token for authorization and authentication, NoSQL MongoDB for data storage, Microsoft Azure deployment and JUnit unit-test for testing.

This project is an examwork of a 2-year higher java YH-education at Campus Mölndal, gothenburg, Sweden, where i as student wanted to both combine as many previous learned technologies as possible and equally the same learn new ones.

The Application is meant to be the ultimate Ski Resort application for both managers, employees and guests at the resort.
* Managers can handle typical and overall employee and guest requests, 
* Employees can handle the daily running of the resort as well the common store, and some of its own data.
* Guests (Users) can book a stay, rent equipment, book ski lessons, and see the current weather and snow conditions.

In the creation and nagging of following both the TRAP-plan (In swedish)

<p align="center">
<img src="src/main/resources/TRAP-Plan(swedish).png"/>
</p>

I learned <u>alot</u> about endpoints, http-requests and common work about how connection between a server and a client works, which was for me very grateful.

## Architecture
Brief architecture overview of the application can be viewed here

<p align="center">
<img src="src/main/resources/UML Diagram.jpg"/>
</p>

## Installation

1. Clone the repository
```https://github.com/Cristoffer85/SnofjallbyWithPT-Backend``` from Github
2. In application.properties, change the following to your own settings:
```spring.data.mongodb.uri=${MONGODB_URI:mongodb://localhost:27017/<your database name>}``` the MONGODB_URI is a environment variable that you can set in your IDE or in your deployment environment. By default if the MONGODB_URI is not set, it will use the local MongoDB database.
3. In configuration package -> OriginConfiguration change the * in ```.allowedOrigins("*")````to your own client URL (if you have one, otherwise keep as * or change to localhost:8080 if you wish to test it locally.
4. Run the application in your IDE or (quick-cheat) here: ```mvn spring-boot:run```

## Usage

For use of this application (without client, which is located at ```https://github.com/Cristoffer85/SnofjallbyWithPT-Frontend```)
you can use POSTMAN or similar API testing tool to test the endpoints.

The following are exported collection-data from Postman (click, open and view every JSON file in raw mode to get the data, then import in Postman.  
The Tokens for authentication and authorization are generated in the body response under "jwt: " in the login endpoint and use them for various authorization in the application (check features below in this document for authorized actions per role).

1. SIGN IN AND SIGN UP Endpoints:  
[Reg-Login [Sec-All].postman_collection.json](assets%2FReg-Login%20%5BSec-All%5D.postman_collection.json)


2. ADMIN Endpoints:  
[Admin -Employees.postman_collection.json](assets%2FAdmin%20-Employees.postman_collection.json)  
[Admin -Users.postman_collection.json](assets%2FAdmin%20-Users.postman_collection.json)


3. EMPLOYEE Endpoints:  
[Employee.postman_collection.json](assets%2FEmployee.postman_collection.json)  


4. USER Endpoints:  
[User.postman_collection.json](assets%2FUser.postman_collection.json) 


5. WEATHER Endpoints:  
[PowderTracker-WeatherAPI.postman_collection.json](assets%2FPowderTracker-WeatherAPI.postman_collection.json)


6. STORE Endpoints:  
[Store.postman_collection.json](assets%2FStore.postman_collection.json)


7. SKI LIFT Endpoints:  
[Skilifts.postman_collection.json](assets%2FSkilifts.postman_collection.json)


## Credits
Classmates from school, Myself, my Family, mighty duck rubber duck and some coPilot and chatGPT for debugging.

## License
🏆 MIT License

## Badges
![badmath](https://img.shields.io/badge/Java-100%25-blue)

## Features
ADMIN, can:
* Login, handle every CRUD functionality for every Employee and Guest

EMPLOYEE, can:
* Login, handle every CRUD functionality for every product in Store, Ski Lift operations and some personal data.

GUEST (User), can:
* Sign up, Login, Book a stay, Rent equipment, Book ski lessons, View previous orders and total cost, See current weather and snow conditions, manage some of its personal data.


## Tests
JUNit tests are located in the test folder and can be run from there. Covering every service class.
