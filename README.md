<h1 align="center">
    OfferRestService
</h1>

A RESTful facade backend API with one service:
- Offer service (*offer entity: id, title, description, price, duration, expired*)

## Toolset
- Spring Boot
- Spring MVC
- Spring Data JPA
  * out-of-the-box DAO-generation at runtime via method-naming conventions
  * declarative transaction-boundaries control
- Hibernate
- Jackson Annotations
  * custom serialization of references (@JsonIdentityInfo, @JsonIdentityReference)
- H2 embedded in memory db
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
