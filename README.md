<h1 align="center">
    OfferRestService
</h1>

<p>It is a simple RESTful software service that allow a merchant to create a new simple offer. Offers, once created, may be queried. After the period of time defined on the offer it should expire and further requests to query the offer should reflect that somehow. Before an offer has expired users may cancel it.</p>

<p>Implementation of a RESTful facade backend API with one service:</p>
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
- H2 console for inspecting database contents

### Unit and Integration Testing
- mocking
- service unit testing
- repository integration testing with embedded in-memory database accessed through Spring JPA *TestEntityManager*
- controller integration testing using *MockMvc* instance to setup a Spring MVC context with a web server

## Prerequisites
- Requires at least Java Runtime 1.8 and maven 3.6

## Quick start
Below all the commands to clone, build and run the project with Maven and Java 8 JDK:
- `git clone https://github.com/alberto963/OffersRestService.git`
- `cd OffersRestService`
- `mvn -T 4 clean install`
- `java -jar target/com-worldpay-ws-offers-web-1.0.jar`
- the embedded servlet container starts at `http://localhost:8091`
- the H2 database console starts at `http://localhost:8091/console`

## Running

### POST offers 
- URL is `http://localhost:8091/worldpay/ws/offer`

`http://localhost:8091/worldpay/ws/offer`

JSON examples to POST an offer:
````
{
	"title": "BREAD",
	"price": 4.49,
	"duration": 100,
	"description": "1 KG white bread"
}
````

`http://localhost:8091/worldpay/ws/offer`

JSON examples to POST an offer:
````
{
	"title": "BEER",
	"price": 7.29,
	"duration": 100000,
	"description": "1 L red beer"
}
````
### GET all offers

- URL is `http://localhost:8091/worldpay/ws/offers`

Examples of returned JSONs:

**http://localhost:8091/worldpay/ws/offers**
````
[
	{
		"offerId": 1,
		"title": "BREAD",
		"description": "1 KG white bread",
		"price": 4.49,
		"duration": 100,
		"createdAt": 1570359705332,
		"expired": true
	},
	{
		"offerId": 2,
		"title": "BEER",
		"description": "1 L red beer",
		"price": 7.29,
		"duration": 100000,
		"createdAt": 1570359721760,
		"expired": false
	}
]
````

### GET offer by title

- URL is `http://localhost:8091/worldpay/ws/offer/{title}`

Examples of returned JSONs:

**http://localhost:8091/worldpay/ws/offer/BREAD**
````
{
		"offerId": 1,
		"title": "BREAD",
		"description": "1 KG white bread",
		"price": 4.49,
		"duration": 100,
		"createdAt": 1570359705332,
		"expired": true
}
````

**http://localhost:8091/worldpay/ws/offer/BEER**
````
{
		"offerId": 2,
		"title": "BEER",
		"description": "1 L red beer",
		"price": 7.29,
		"duration": 100000,
		"createdAt": 1570359721760,
		"expired": false
}
````

### GET offer by id

- URL is `http://localhost:8091/worldpay/ws/offerid/{offerId}`

Examples of returned JSONs:

**http://localhost:8091/worldpay/ws/offerid/1**
````
{
		"offerId": 1,
		"title": "BREAD",
		"description": "1 KG white bread",
		"price": 4.49,
		"duration": 100,
		"createdAt": 1570359705332,
		"expired": true
}
````

**http://localhost:8091/worldpay/ws/offerid/2**
````
{
		"offerId": 2,
		"title": "BEER",
		"description": "1 L red beer",
		"price": 7.29,
		"duration": 100000,
		"createdAt": 1570359721760,
		"expired": false
}
````
### DELETE offer by title

- URL is `http://localhost:8091/worldpay/ws/offer/{title}`

Examples:

`http://localhost:8091/worldpay/ws/offer/BREAD`

`http://localhost:8091/worldpay/ws/offer/BEER`


### DELETE offer by id

- URL is `http://localhost:8091/worldpay/ws/offerid/{offerId}`

Examples:

`http://localhost:8091/worldpay/ws/offerid/1`

`http://localhost:8091/worldpay/ws/offerid/2`

****
