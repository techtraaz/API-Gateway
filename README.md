# Simple API Gateway (Spring Boot)

A lightweight API Gateway built using Spring Boot that dynamically routes incoming HTTP requests to backend services based on API configuration.

##  Features
- Single entry point for all APIs
- Dynamic request routing
- Supports GET, POST, PUT, DELETE, etc.
- API Key based access control
- Forwards headers, query params, and request body

##  Upcoming Features
- Rate limiting, Logging, Auth


## 🧩 Tech Stack
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- RestTemplate
- MySQL 

## 📂 Project Structure

## 🏗 Architecture
![API Gateway Architecture](imp/gateway_archi.png)

## How It Works
1. Client calls the API Gateway endpoint
2. API key is validated
3. Gateway resolves the target backend URL
4. Request is forwarded to the backend service
5. Response is returned to the client
