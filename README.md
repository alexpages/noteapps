## noteapps
### Functional Description

This repository contains two applications:
- **Firstapp**: Server-side Java + Spring Boot application that enables three endpoints to create and retrieve notes. It connects to a MySQL DB through Docker.
- **Secondapp**: Client-side. It sets a @Bean for WebClient and enables a test class to test the active FirstApp.

Here are some features of the overall configuration:

- **Firstapp**:
  * Exception handler and custom exceptions
  * Data validation
  * Minimal testing through JUnit and MockMVC
  * Usage of Docker to set up two containers (Spring Boot application for FirstApp and MySQL DB)
  * Modularity and interface usage
  * One spring profile is available:
      * *docker*: It stablishes necessary variables and configuration. It is used in the start.sh script
- **Secondapp**:
  * WebClient usage
  * Tests to communicate to running Firstapp.

### How to run this project
1. First, download the two projects and set them up separately in your preferred IDE (import existing maven project).
2. Ensure that Docker Desktop is running or similar.
3. Go to the root of the Firstapp and run:
```
./start.sh
```
- Now that Firstapp is running, run the tests from Secondapp

```
NOTE: for mac M1 users, you should add a line into the docker-compose.yml to specify the platform:

      build:
         context: .
         dockerfile: Dockerfile
         platform: linux/amd64      #Specifies platform
      ports:
```
### What to expect
- After running the tests from SecondApp, the user should be able to see on the console the outputs from communicating with FirstApp.
- Also, the user can communicate directly with FirstApp through Docker and "http://localhost:8080". Please refer to the `FirstAppApi` class for usage in POSTMAN.

### Endpoints
#### Place Note
  - Method: `POST`
  - URL path: `/note`
  - Request body:
    ```
    {
        "message": "first message"
    }
    ```
  - Response:
    Header: `HTTP 201`
    Body:
      ```
      {
          "id": 1,
          "message": "first message"
      }
      ```
    or
    Header: `HTTP 400` or `HTTP 500`
    Body example:
      ```
     {
         "status": "INTERNAL_SERVER_ERROR",
         "throwable": null,
         "error": "NoteServiceImpl > getNoteById > There was an error retrieving Note with ID: 100",
         "timestamp": "2024-02-03 09:19:18"
     }
      ```
#### Get Note
  - Method: `GET`
  - URL path: `/note/:noteId`
  - Request body: NA
  - Response:
    Header: `HTTP 200`
    Body:
      ```
      {
          "id": 1,
          "message": "first message"
      }
      ```
    or
    Header: `HTTP 404`
    Body: NA
#### Get Notes
  - Method: `GET`
  - URL path: `/note`
  - Request body: NA
  - Response:
    Header: `HTTP 200`
    Body:
      ```
      {
          "id": 1,
          "message": "first message"
      },
      {
          "id": 2,
          "message": "second message"
      }
      ```
    or
    Header: `HTTP 200`
    Body:
      ```
      []
      ```
