# Toll Plaza Locator API

A Java Spring Boot RESTful API to find toll plazas on the route between two Indian pincodes. The API dynamically filters toll plazas based on the route distance using adaptive geographical filtering, delivering precise results with robust error handling.

## Project Overview

This project aims to provide a backend REST API that, given two Indian pincodes as source and destination, calculates the route distance and returns a filtered list of toll plazas located within an adaptive radius around the source location. The filtering radius scales dynamically based on the route distance, ensuring relevant toll plazas are returned without overwhelming results.

---

## Features

- RESTful API with JSON communication.  
- Adaptive filtering radius based on route distance with caps and buffer.  
- Data sourced from Indian pincodes and toll plazas CSV files.  
- Robust validation for pincodes and route inputs.  
- Proper error handling with meaningful messages.  
- Basic unit tests using JUnit to verify key functionality.  
- Modular and clean codebase following best design practices.

---

## Technology Stack

- Java 11+  
- Spring Boot 2.x  
- Maven (build & dependency management)  
- JUnit 5 (unit testing)  
- CSV data files (for pincodes & toll plazas)  
- Git & GitHub for version control

---

## Setup & Running the Project

### Prerequisites

- Java 11 or newer installed  
- Maven installed  
- Git installed (optional, for cloning repository)

### Steps

1. Clone the repository:

```
git clone https://github.com/SekharSunkara6/Toll-Plaza-Locator.git
cd Toll-Plaza-Locator
```

2. Build the project:

```
mvn clean install
```

3. Run the application:

```
mvn spring-boot:run
```

4. The API server starts on `http://localhost:8080`

---

## API Documentation

### Endpoint: Get Toll Plazas on Route

**POST** `/api/v1/toll-plazas`

**Request Body Example**:

```
{
  "sourcePincode": "560064",
  "destinationPincode": "411045"
}
```

**Response Example**:

```
{
  "route": {
    "sourcePincode": "560064",
    "destinationPincode": "411045",
    "distanceInKm": 733.13
  },
  "tollPlazas": [
    {
      "name": "Some Toll Plaza",
      "latitude": 17.0,
      "longitude": 75.0,
      "distanceFromSource": 150.3
    }
    // More toll plazas
  ]
}
```

---

## Testing

- Use **Postman** or similar tools to POST requests to the API.  
- Included comprehensive unit tests using JUnit 5 in `src/test/java/` for key service components.  
- Run tests using Maven:

```
mvn test
```

---

## Error Handling

| Error Scenario                | Response Example                                   |
|------------------------------|---------------------------------------------------|
| Same source and destination   | `{ "error": "Source and destination pincodes cannot be the same" }` |
| Invalid pincodes              | `{ "error": "Invalid source or destination pincode" }`            |
| Missing required fields       | HTTP 400 with validation error messages              |

---

## Folder Structure

```
Toll-Plaza-Locator/
├── src/
│   ├── main/
│   │   ├── java/com/example/tollplaza/      # Source code
│   │   ├── resources/                        # CSV files & config
│   ├── test/java/com/example/tollplaza/     # Unit tests
├── images/
│   └── screenshots/                          # API test proof images
├── .gitignore
├── pom.xml
├── README.md
```

---

## Screenshots

### 1. Working Request - Valid Pincodes

![Working Request](images/screenshots/working_request.png)

---

### 2. Error - Invalid Pincode

![Invalid Pincode](images/screenshots/invalid_pincode_error.png)

---

### 3. Error - Same Pincodes

![Same Pincode](images/screenshots/same_pincode_error.png)

---

## Future Improvements

- Add integration tests covering API controllers  
- Implement advanced filtering based on actual route polyline  
- Add database persistence with efficient upsert operations  
- Enhance test coverage for edge cases and performance  
- Setup CI/CD pipeline for automated build and test

---

## Author

**Sekhar Sunkara**

- GitHub: https://github.com/SekharSunkara6  
- Email: [sekharsunkara2002@gmail.com]
- Portfolio: https://sekharsunkaraportfolio.netlify.app/

---

Thank you for reviewing this project!
