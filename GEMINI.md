# Gemini Project Definition

This document provides a summary of the project structure, technologies used, and key commands for building and running the application.

## Project Overview

This project consists of two main parts:

1.  A presentation in two section on software architecture, then the second part on development with Artificial Intelligence Generative, built with Reveal.js.
2.  A hands-on lab composed of several Java microservices that demonstrate concepts like service discovery and API gateways.

## Technologies Used

- **Presentation (docs):**
    - [Reveal.js](https://revealjs.com/)
    - [Node.js](https://nodejs.org/)
    - [Sass](https://sass-lang.com/)
    - [sfeir-school-theme](https://github.com/sfeir-open-source/sfeir-school-theme)

- **Lab:**
    - [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
    - [Spring Boot 3.3.5](https://spring.io/projects/spring-boot)
    - [Spring Cloud 2023.0.3](https://spring.io/projects/spring-cloud)
    - [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
    - [Netflix Eureka](https://github.com/Netflix/eureka)
    - [Maven](https://maven.apache.org/)
    - [Docker](https://www.docker.com/)

## How to Run the Project

### Running the Presentation

The presentation slides can be served locally.

1.  Navigate to the `docs` directory:
    ```bash
    cd docs
    ```
2.  Install the dependencies:
    ```bash
    npm install
    ```
3.  Start the local development server:
    ```bash
    npm start
    ```
    The slides will be available at `http://localhost:4242`.

### Running the Microservices Lab

The lab environment is managed using Docker Compose.

1.  Navigate to the `lab` directory:
    ```bash
    cd lab
    ```
2.  Build and start all the services in the background:
    ```bash
    docker-compose up --build -d
    ```

The main services will be accessible at the following ports:
- **API Gateway:** `http://localhost:9090`
- **Eureka Registry:** `http://localhost:8761`

To stop the services, run:
```bash
docker-compose down
```