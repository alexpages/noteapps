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
1. First, download the two projects and set them up separately in your preferred IDE.
2. Ensure that Docker Desktop is running or similar.
3. Go to the root of the Firstapp and run:
```
./start.sh
```
- Now that Firstapp is running, run the tests from Secondapp

### What to expect

- After running the tests from SecondApp, the user should be able to see on the console the outputs from communicating with FirstApp.
- Also, the user can communicate directly with FirstApp through Docker and "http://localhost:8080".
