# Pokedex API

This project is a REST API for managing Pokemon data, including user registration, authentication, and CRUD operations on Pokemon entities. 

The application is implemented using Ports and Adapters architecture (Hexagonal architecture).

The application is composed of two Microservices:

1. Pokemon service: This handles the CRUD operations on Pokemon entities including validation of token on private endpoints. Token is generated through User Service Login endpoint.
2. User service: This handles the user creation and login which generates an authorization token.

The projects are built with Java 17, Spring Boot 3, Spring Security, Gradle 8.7 (included as gradlew in project) and Mongo DB as database.

## Problem that this REST API is solving:

1. Create a registration service that receives an email and a password.

- Validate email is a valid email address.
- Validate email is not already registered in the database.
- Validate password contains at least 10 characters, one lowercase letter, one uppercase letter, and one of the following characters: !, @, #, ? or ].
- If any of the above is invalid, send a meaningful response.

2. Allow login into the server with an email and a password.

- Validate email is a valid email address
- Validate email is already registered in the database
- Validate password contains at least 10 characters, one lowercase letter, one uppercase letter, and one of the following characters: !, @, #, ? or ].
- Validate email and password matches for a previously registered user.
- If any of the above is invalid send back a meaningful response.
- If all of the above are valid send back a payload including some way for users to identify themselves for subsequent requests. That way to identify users should be invalid after 20 minutes and the user must log in again to continue communication with the server.

3. Allow logged-in users to do CRUD operations into a table/collection of the Pokemons.

- Users should be able to create a new element that can only be retrieved by themselves (Private item), or that can be retrieved by others (Public item).
- Users should be able to read all public elements in the table/collection.
- Users should be able to read all elements created by themselves.
- Users should be able to edit at least one field in one of their private items.
- Validate that users are trying to read or update their own private elements, otherwise send a meaningful response.

## Project Architecture Considerations

Our project is structured following the Ports and Adapters architecture (often referred to as Hexagonal Architecture). This approach helps in separating the core logic of the application from the services it interacts with. Below is an overview of how our project packages are organized:

### Domain Package
- **Purpose:** Houses the core business models, exceptions, and interfaces.
- **Contents:**
  - **Business Models:** Classes that represent the fundamental business objects.
  - **Business Exceptions:** Custom exceptions that handle domain-specific errors.
  - **Interfaces:** Define the rules and contracts for external adapters to interact with the business logic.

### Application Package
- **Purpose:** Contains application-specific logic and data transfer objects.
- **Contents:**
  - **Request and Response Objects:** DTOs used for handling and responding to API requests.
  - **Validation Rules:** Ensures that incoming data adheres to business rules.
  - **Use Cases:** Encapsulates the business logic specific to each feature or requirement of the application.

### Infrastructure Package
- **Purpose:** Supports interaction with external systems such as databases and third-party services, and includes components specific to the Spring Framework.
- **Contents:**
  - **Integration Classes:** Manage the communication with databases and external APIs.
  - **Spring-related Functionality:** Includes configurations and beans specific to Spring, enhancing or enabling its features.
  - **API Endpoints:** Define the entry points for the external requests into our application.

This architectural setup ensures that our application remains adaptable to changes in the business environment or technology stack, promoting maintainability and scalability.

## Some projects considerations

### User Service:

  - User service data is preloaded with a user which email is "admin@example.com" and password is "testpass#123KA". The class that preloads the data in the userservice project is com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.dataloader.UserDataLoader.
 
  - The endpoint "/api/auth/login" is managed by the class com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.security.filter.JsonEmailPasswordAuthenticationFilter.

  - The endpoint "/api/user" is managed by the class com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.adapter.controller.AuthRestController

### Pokemon Service:

- Pokemon service data is preloaded with 8 public pokemons which owner is "admin@example.com". The class that preloads the data in the pokemonservice project is com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.dataloader.PokemonDataLoader.

- The endpoints under "/api/private/pokemon" are managed by the class com.roselinorozco.pokedex.pokemonservice.infrastructure.inbound.rest.pokemon.adapter.controller.PrivatePokemonRestController.

- The endpoints under "/api/public/pokemon" are managed by the class com.roselinorozco.pokedex.pokemonservice.infrastructure.inbound.rest.pokemon.adapter.controller.PublicPokemonRestController.

- The endpoint "/api/service/randomnumber" is managed by the class com.roselinorozco.pokedex.pokemonservice.infrastructure.inbound.rest.randomnumber.adapter.controller.RandomNumberRestController.

## Prerequisites for Running the Projects

Before you start, ensure you have the following installed:

  - **Docker**.
  - **Docker Compose**.
  - **Git**.
  - **Java 17**.
  - **An IDE**: While optional, an IDE like IntelliJ IDEA is recommended for easier code navigation.
  - **Postman**.

## Tested Technology Versions

The applications were tested and confirmed to work with the following versions of technologies on different operating systems:

### Windows 11:

- Docker version 26.1.1, build 4cf5afa
- Docker Compose version v2.27.0-desktop.2

### Linux - Ubuntu 22.04.4 LTS:

- Docker version 26.1.4, build 5650f9b
- Docker Compose version v2.27.0-desktop.2
- java 17.0.8 2023-07-18 LTS

### Macbook Pro ARM Processor, MacOS Sonoma version 14.5:

  - Docker version 26.1.1, build 4cf5afa.
  - Docker Compose version v2.27.0-desktop.2
  - Java 17 Amazon Corretto 17.0.11 - aarch64

## Run the Application locally using Docker Compose:

To get the application running on your local machine you will need to have installed Docker and Docker compose and follow these steps:

1. **Clone the Repository**:
   - Open a terminal and navigate to the folder where you want the project.
   - Run the cloning command: `git clone https://github.com/roselinorozco/pokedex-api.git`. It will create a folder called pokedex-api.

### Running the User Service:

2. **Prepare to Run**:
   - Navigate to the `userservice` root folder within the cloned directory.
   - Open a terminal at this location.

3. **Start the Service**:

   - On Windows:
      Execute: `docker-compose -f docker-compose.yml up --build`

   - On Linux:
      Execute: `docker compose -f docker-compose.yml up --build`
   
   - On MacOS ARM:
      Execute: `docker-compose -f docker-compose-arm64.yml up --build`

   - Wait until the service is fully operational.

4. **Access the Service**:
   - The User Service will be available at `http://127.0.0.1:8095/`.

### Running the Pokemon Service:

5. **Prepare to Run**:
   - Navigate to the `pokemonservice` root folder within the cloned directory.
   - Open a terminal at this location.

6. **Start the Service**:
   - Execute: `docker-compose -f docker-compose.yml up --build`. if Linux or Windows operative system is used, or `docker-compose -f docker-compose-arm64.yml up --build` if MacOS ARM system is used.

7. **Access the Service**:
   - The Pokemon Service will be available at `http://127.0.0.1:8096/`.

## Postman Collection for testing endpoints

- Included in the `pokedex-api` folder, you will find the Postman collection named `pokedex-local-application-requests.postman_collection.json` that can be imported into Postman for its use. This collection is pre-configured with all the endpoints implemented on the different services available for use. It provides a convenient way to test and interact with these services directly from Postman in a local environment.

- Included in the `pokedex-api` folder, you will find the Postman collection named `pokedex-google-cloud-application-requests.postman_collection.json` that can be imported into Postman for its use. This collection is pre-configured with all the endpoints implemented on the different services available for use. It provides a convenient way to test and interact with these services hosted in Google cloud directly from Postman.

## Opening Projects in an IDE (e.g., IntelliJ IDEA)

- The `userservice` and `pokemonservice` are located under the folder `pokedex-api`. For code inspection, these projects need to be opened separately as independent projects within your IDE.

## Running the Unit Tests

  - On userservice folder run "./gradlew test" to run 38 unit tests  successfully for User Service Project.

  - On pokemonservice folder run "./gradlew test" to run 64 unit tests successfully for Pokemon Service Project.

## Access to the application through a Cloud Provider

The application is deployed using Google Cloud App Engine. The URLs to access are the next:

Pokemon service is available at https://pokedex-api-pokemon-service.uc.r.appspot.com/

User service is available at https://pokedex-api-user-service.uc.r.appspot.com/
 

## API Endpoints

User Service:

For this service, all APIs are public, and not authorization token is required.

1. Register User: This allows the user registration and requires an email and a password.

   POST /api/user

   - Request body:

   ```json
     {
        "email": "user1@example.com",
        "password":  "57Blocks#12"
     }
    ```
   - Response a HTTP status code 201 and the user email created, or the corresponding error message.

2. User login: This allows the login of a registered user.

   POST /api/auth/login

   - Request body:

   ```json
     {
        "email": "user1@example.com",
        "password":  "57Blocks#12"
     }
    ```
   - Response a HTTP status code 200 and the user email, token and expiry time for the token, or the corresponding error message. The token is also returned directly to the headers.

Pokemon Service:

For this service, the APIs are composed of private endpoints and public endpoints, the private endpoints are the ones used to do the CRUD operations on Pokemon entities, such as create, update, or delete. The public endpoints are the ones used to retrieve all public Pokemons, and to retrieve a random number. 

Private Endpoints

Access to private endpoints is restricted to registered users who are logged into the application. To access these endpoints, you must include an Authorization header in your requests. This header should contain a token, which is valid for 20 minutes.

Obtaining the Token:

Log in through the User Service's login endpoint.
Upon successful authentication, you will receive a response that includes an Authorization header. This header contains your access token.

Using the Token:

When making requests to private endpoints in the Pokemon service, you must include the Authorization header with the token you received.


1. Create a Pokemon: Allow the Pokemon creation with the next fields: 

   - name: A String containing the name of the Pokemon, just letters are allowed.
   - type: A String containing the type of the Pokemon, just letters are allowed.
   - legendary: A boolean value indicating if the Pokemon is legendary or not. 
   - abilities: A String containing the abilities of the Pokemon, this needs to be separated by commas. Minimum one ability and maximum 4 abilities must be provided.
   - weight: An integer value indicating the weight of the Pokemon.
   - Height: An integer value indicating the height of the Pokemon.
   - level: An integer value indicating the level of experience of the Pokemon.
   - publicItem: A boolean value indicating if the Pokemon is public or private.

   POST /api/private/pokemon:
   
   - Request Body

   ```json
     {
        "name": "Chikorita",
        "type":  "normal",
        "legendary": "false",
        "abilities": "Hidden Leaf, Tackle",
        "weight": 40,
        "height": 2,
        "level": 24,
        "publicItem": "true"
     }
   ```
   - Response an HTTP status code 201 with the content of the created Pokemon, or the corresponding error message.

2. Update a Pokemon: Allows the modification of the publicItem field of a registered Pokemon. The Pokemon id is required as a Path variable.

   PATCH /api/private/pokemon/{id}  

   - Request Body

   ```json
     {
        "publicItem": "true"
     }
   ```
   - Response an HTTP status code 200 with the content of the updated Pokemon, or the corresponding error message.

3. Delete a Pokemon: Allows the deletion of a registered Pokemon. The Pokemon id is required as a Path variable.

   DELETE /api/private/pokemon/{id}  

   - Request Body: Empty.
   - Response an HTTP status code 200 indicating the Pokemon was delete, or the corresponding error message.

4. Get Pokemons by Owner: Retrieves all Pokemons(public and private) of a registered user. Requires the parameters page and size, which are required to have the results paginated.

   GET /api/private/pokemon?page=0&size=3

   - Request Body: Empty.
   - Response an HTTP status code 200 with the paginated list of Pokemon, or the corresponding error message.

Public endpoints:

The public endpoint doesn´t require the authorization headers.

1. Get Public Pokemons: Retrieves all Pokemons registered as public. Requires the parameters page and size, which are required to have the results paginated.

   GET /api/public/pokemon?page=3&size=3

   - Request Body: Empty.
   - Response an HTTP status code 200 with the paginated list of Pokemons, or the corresponding error message.

2. Get Random Number: Returns a random number between 1 and 100.000. This is calling the next API to return the random number: http://www.randomnumberapi.com/api/v1.0/random?min=1&max=100000&count=1

   GET /api/service/random number

   - Request Body: Empty.
   - Response an HTTP status code 200 with the random number, or the corresponding error message.

## Commons errors responses

User Service

Register User

1. Email exists:
   
   HTTP status code 409
   ```json
    {
     "status": "CONFLICT",
     "message": "Email is already registered.",
     "errors": [
        "Email monica2@example.com is already registered"
     ]
    }
   ```
2. Invalid email:

   HTTP status code 400
   ```json
    {
    "status": "BAD_REQUEST",
    "message": "Invalid field email.",
    "errors": [
        "The provided email address is invalid."
    ]
    }
   ```
3. Invalid Password:

   HTTP status code 400
   ```json
    {
     "status": "BAD_REQUEST",
     "message": "Invalid password.",
     "errors": [
        "Password must be at least 10 characters long and include one lowercase letter, one uppercase letter and one special character !, @, #, ? or ]."
     ]
    }
   ```

Login

1. Bad credentials:

   HTTP status code 401
   ```json
   {
    "status": "UNAUTHORIZED",
    "message": "Bad Credentials",
    "errors": [
        "The provided email address is invalid."
    ]
   }
   ```

   HTTP status code 401
   ```json
   {
    "status": "UNAUTHORIZED",
    "message": "Bad Credentials",
    "errors": [
        "Password does not match for email user7@example.com"
    ]
   }
   ```

Pokemon Service

Create Pokemon

1. Missing a field:

 HTTP status code 400
 ```json
 {
    "status": "BAD_REQUEST",
    "message": "Invalid field level.",
    "errors": [
        "level cannot be null"
    ]
 }
 ```

2. Invalid argument:

HTTP status code 400
```json
  {
    "status": "BAD_REQUEST",
    "message": "Register not permitted",
    "errors": [
        "Invalid abilities format. Each ability should consist of letters and can include multiple words, separated by commas."
    ]
  }
```

Update Pokemon

1. Missing a field:

HTTP status code 400
```json
 {
    "status": "BAD_REQUEST",
    "message": "Invalid field publicItem.",
    "errors": [
        "Update not allowed. The publicItem value is required."
    ]
 }
```
2. Pokemon not found:

HTTP status code 404
```json
  {
    "status": "NOT_FOUND",
    "message": "Pokemon Not Found",
    "errors": [
        "The Pokémon with the provided ID is not registered."
    ]
  }
```
3. Update not allowed:

HTTP status code 401
```json
 {
    "status": "UNAUTHORIZED",
    "message": "Update Forbidden",
    "errors": [
        "Update not allowed. This Pokémon belongs to another user."
    ]
 }
```

Delete Pokemon

1. Delete not allowed

HTTP status code 401
```json
 {
    "status": "UNAUTHORIZED",
    "message": "Pokémon belongs to another owner",
    "errors": [
        "Deletion not allowed. This Pokémon belongs to another owner."
    ]
 }
```

Get Public Pokemons

1. Pagination required

HTTP status code 400
```json
 {
    "status": "BAD_REQUEST",
    "message": "Invalid Page or Size",
    "errors": [
        "Page and size must be provided"
    ]
 }
```

Get Private Pokemons

1. Pagination required

HTTP status code 400
```json
 {
    "status": "BAD_REQUEST",
    "message": "Invalid Page or Size",
    "errors": [
        "Page and size must be provided"
    ]
 }
```

## API rate limiting

API Rate Limits

To ensure fair usage and protect our services, we implement rate limits on API requests. Below, you will find the specific limits for different types of endpoints in our API. These limits help prevent abuse and ensure that our services remain fast and reliable for everyone.

Rate Limits by Endpoint
Our API endpoints are categorized into public, private, and service endpoints, each with specific rate limits:

Public Endpoints (/api/public/**):

Limit: Up to 50 requests every 5 minutes.

Private Endpoints (/api/private/**):

Limit: Up to 40 requests every 2 minutes.

Service Endpoints (/api/service/**):

Limit: Up to 20 requests every 5 minutes.

What Happens If You Exceed the Limit?

If you exceed these limits, your requests will be temporarily blocked, and you will receive a response with the HTTP status code 429 Too Many Requests. This is to inform you that you have hit the cap for allowed requests in the set timeframe.

## Disclaimer

### General
This software and associated documentation files (the "Software"), provided by the author and contributors, are for informational and educational purposes only. The author and contributors make no representations or warranties of any kind, express or implied, as to the accuracy, reliability, suitability, or availability with respect to the Software or the information, products, services, or related graphics contained in the Software for any purpose. Any reliance you place on such information is therefore strictly at your own risk.

### Limitation of Liability
In no event shall the author or contributors be liable for any direct, indirect, incidental, special, exemplary, or consequential damages (including, but not limited to, procurement of substitute goods or services; loss of use, data, or profits; or business interruption) however caused and on any theory of liability, whether in contract, strict liability, or tort (including negligence or otherwise) arising in any way out of the use of this Software, even if advised of the possibility of such damage.

## Author

Roselin Orozco - roselin.orozco.arbelaez@gmail.com

Any question can be send to the email indicated. Thanks.
