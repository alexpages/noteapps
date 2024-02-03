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

### How to run this project - Windows
1. First, download the two projects and set them up separately in your preferred IDE (import existing maven project).
2. Ensure that Docker Desktop is running or similar.
3. Go to the root of the Firstapp and run:
```
./start.sh
```
 (You can also run docker-compose up manually).

4. Run Secondapp as `maven install` or run manually the tests as JUnit.

### How to run this project - Mac
1. First, download the two projects and set them up separately in your preferred IDE.
2. Ensure that Docker Desktop is running or similar.
3. NOTE: for Mac M1 users, you should add a line into the docker-compose.yml to specify the platform:
```
      build:
         context: .
         dockerfile: Dockerfile
         platform: linux/amd64      #Specifies platform
      ports:
```
4. Go to the root of the Firstapp and manually docker-compose up.
5. Run Secondapp as `maven install` or run manually the tests as JUnit.

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
### Evidences
The following two images serve as an additional test evidence of the correct functionality for both Mac M1 and Windows:

#### Windows
![Windows](https://github.com/alexpages/noteapps/assets/99218116/611249e7-1c93-4f3c-904b-913778be5e61)

#### Mac M1
![Mac M1](https://github.com/alexpages/noteapps/assets/99218116/79cf4120-b3ec-4175-879f-fd00b9f986c3)
