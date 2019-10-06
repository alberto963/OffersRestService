<h1 align="center">
    OfferRestService
</h1>

A RESTful facade backend API with one service:
- Offer service (*offer entity: id, title, description, price, created at, duration, expired*)

## Toolset
- Spring Boot
- Spring MVC
- Spring Data JPA
- Lombok
- Hibernate
- Jackson Annotations
- H2 embedded in memory db
- Maven
- Git
- Mockito

## Features
### Implemented requirements
- endpoint for providing current status of an offer (in JSON)
- endpoint for creating and deleting offers
- backend services able to handle multiple POST requests independently at the same time (with generated ids)

### Unit and Integration Testing
- mocking
- service unit testing
- repository integration testing with embedded in-memory database accessed through Spring JPA *TestEntityManager*
- controller integration testing using *MockMvc* instance to setup a Spring MVC context with a web server

## Prerequisites
- Requires at least Java Runtime 1.8

## Quick start
Below all the commands to clone, build and run the project with Maven and Java 8 JDK:
- `git clone https://github.com/alberto963/OffersRestService.git`
- `cd OffersRestService`
- `mvn -T 4 clean install`
- `java -jar target/com-worldpay-ws-offers-web-1.0.jar`
- the embedded servlet container starts at `http://localhost:8091`

## Running

### POST offers 
- URL is `http://localhost:8091/worldpay/ws/offer`

JSON examples to POST an offer:
````
{
	"title": "BREAD",
	"price": 4.49,
	"duration": 100,
	"description": "1 KG white bread"
}

{
	"title": "BEER",
	"price": 7.29,
	"duration": 100,
	"description": "1 L red beer"
}
````

### GET offer by title

- URL is `http://localhost:8091/worldpay/ws/offer/{title}`

Examples of returned JSONs:

**http://localhost:8091/worldpay/ws/offer/BREAD**
````
{
	"title": "BREAD",
    "description": "Bread",
    "price": 4.49,
    "expired": false
}
````

**http://localhost:8091/worldpay/ws/offer/BEER**
````
{
	"title": "BEER",
	"description": "1 L red beer",
	"price": 7.29,
	"expired": true
}
````

### GET offer by id

- URL is `http://localhost:8091/worldpay/ws/offerid/{offerId}`

Examples of returned JSONs:

**http://localhost:8091/worldpay/ws/offerid/1**
````
{
	"title": "BREAD",
    "description": "Bread",
    "price": 4.49,
    "expired": false
}
````

**http://localhost:8091/worldpay/ws/offerid/2**
````
{
	"title": "BEER",
	"description": "1 L red beer",
	"price": 7.29,
	"expired": true
}
````
### DELETE offer by title

- URL is `http://localhost:8091/worldpay/ws/offer/{title}`

Examples:

**http://localhost:8091/worldpay/ws/offer/BREAD**

**http://localhost:8091/worldpay/ws/offer/BEER**


### DELETE offer by id

- URL is `http://localhost:8091/worldpay/ws/offerid/{offerId}`

Examples:

**http://localhost:8091/worldpay/ws/offerid/1**

**http://localhost:8091/worldpay/ws/offerid/2**

****
