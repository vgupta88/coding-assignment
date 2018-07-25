# Invoice Discount API


This API is a microservice created as part of a technical assessment to calculate discounts for an invoice considering few business rules.

## Requirement
  - JDK 1.8
  - Apache Maven 3.x
  - SonarQube

## Technologies
- Spring Boot
- Java 8
- JUnit

## Code & Tests Coverage
The API comes with JaCoCo plugin to use with Sonar integration that enables it to cover code as well as tests via a valid SonarQube installation. 

### Setting it up

#### Running tests
```sh
$ mvn clean test
```
#### Local Sonar Environment
```sh
$ mvn clean install sonar:sonar
$ java -jar coding-assignment-0.0.1-SNAPSHOT.jar
```
#### External Sonar Environment
```sh
$ mvn clean install -Dsonar.host.url=<url>
$ java -jar coding-assignment-0.0.1-SNAPSHOT.jar
```

## Usage

Use the following info to use the POST API to calculate discount by providing a bill 
###### Request URL 
http://localhost:8080/assess/discount 
###### Sample Request Body
#
```json
{
  "id": "c8305f5e-1cf4-4938-b577-3fbac5fea5a1",
  "totalAmount": 200,
  "groceriesAmount": 100,
  "nonGroceriesAmount": 100,
  "user": {
    "id": "c8305f5e-1cf4-4938-b577-3fbac5fea5a0",
    "name": "Test User",
    "type": "EMPLOYEE",
    "startDate": "2015-04-23T18:25:43.511Z" ,
    "active": true
  }
}
```
###### Sample Response Body 
# 
```json
{
"id": "1b9ac3f9-61a9-4d86-a552-cebe6a8a9fca",
"discountedBill": 165,
"userId": "c8305f5e-1cf4-4938-b577-3fbac5fea5a0",
"billId": "c8305f5e-1cf4-4938-b577-3fbac5fea5a1"
}
```

## TODO 
- Improve coverage
- Validations
- Database support for user management (so we don't need to pass user object in request)
- Service and DAO Layer

## CI/CD Plan
- Containerize using Docker
- CI using Jenkins to build docker image
- CD Pipeline using container management solution like AWS ECS to deploy the image and manage container
