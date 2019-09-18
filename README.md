<h1 align="center">
    OfferRestService
</h1>

A RESTful facade backend API with one service:
- Offer service (*offer entity: id, description, price, duration*)

## Toolset
- Spring Boot
- Spring MVC
- Spring Data JPA
  * out-of-the-box DAO-generation at runtime via method-naming conventions
  * declarative transaction-boundaries control
- Hibernate
- Jackson Annotations
  * custom serialization of references (@JsonIdentityInfo, @JsonIdentityReference)
- Apache Derby
- Maven
- Git
- Mockito

## Features
### Implemented requirements
- endpoint for providing current status of an offer (in JSON)
- endpoint for creating and deleting offers
- backend services able to handle multiple POST requests independently at the same time

### Nuances
- upon POSTing offers in parallel, a concurrency-control through transactional serialization is enforced to prevent phantom-reads

### Unit and Integration Testing
- mocking
- service unit testing
- repository integration testing with embedded in-memory database accessed through Spring JPA *TestEntityManager*
- controller integration testing using *MockMvc* instance to setup a Spring MVC context with a web server

## Prerequisites
- Requires at least Java Runtime 1.8 - [download](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

## Quick start
Below all the commands to clone, build and run the project with Maven and Java 8 JDK:
- `git clone https://github.com/alberto963/OffersRestService.git`
- `cd OffersRestService`
- `mvn -T 4 clean install`
- `java -jar target/com-worldpay-ws-offers-web-1.0.jar`
- the embedded servlet container starts at `http://localhost:4001`

## Running

### POST offers 
- URL is `http://localhost:4001/worldpay/ws/offer`

JSON examples to POST an offer:
````
{
  "offerId": 1,
  "price": 1,
  "duration": 1,
  "description": "Bread"
}

{
  "offerId": 2,
  "price": 1,
  "duration": 1,
  "description": "Beer"
}
````

### GET offer

- URL is `http://localhost:4001/worldpay/ws/offer/{offerId}`

Examples of returned JSONs:

**http://localhost:4001/worldpay/ws/offer/1**
````
{
    "offerId": 1,
    "description": "Bread"
}
````

**http://localhost:4001/worldpay/ws/offer/2**
````
{
    "offerId": 2,
    "description": "Beer"
}
````
****
