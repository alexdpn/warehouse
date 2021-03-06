# FOOD WAREHOUSE - CRUD APP
This REST API provides useful endpoints to perfom CRUD operations on a food warehouse backend.

The application uses the following technologies: Java 8, Jersey, Junit, Swagger, Maven and Jetty.

# How to start the application with Docker
1. Clone the repository
3. Build the docker image
```
docker build --tag warehouse:1.0 .
```
4. Start the container
```
docker run --network host -d warehouse:1.0
```
5. Access the endpoints using the port 8081 and the http protocol

# How to start the application without Docker
1. Clone the repository
2. Build the project with maven
3. Open the project in an IDE
4. Start the main method from the WarehouseApplication class or run the jar file using the following command:
```
java -jar warehouse-1.0-SNAPSHOT.jar
```
5. Access the endpoints using the port 8081 and the http protocol

# Documentation
The documentation is generated by Swagger. Currently, the documentation is available in JSON and YAML formats.
1. JSON format address:
```
    http://localhost:8081/swagger.json
```
2. YAML format address:
```
    http://localhost:8081/swagger.yaml
```
