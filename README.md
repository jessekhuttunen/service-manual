# Service Manual Project

## Overview
This is a demo project with a backend implementation using Java and Spring Boot.
Purpose of this project is to have REST API which meets the following requirements:

User must be able to

* create a new maintenance task.
* update an existing maintenance task.
* assign maintenance task to a factory device.
* delete an existing maintenance task.
* fetch all the maintenance tasks.
* fetch maintenance tasks related to specific factory device.
  * Fetched maintenance tasks must be sorted primarily by their classification and secondarily by their entry time
(from the newest to the oldest).

## Database
Application is using in-memory H2 database.
As the application starts, the database is populated by INSERT queries located in `data.sql` file
(servicemanual/src/main/resources).

### H2 Console
Changes to the database can be verified with H2 Console.
Console can be opened by inserting URL below to your browser:

`http://localhost:8080/h2-console`

This opens a login page where you must do following:

1. Set `jdbc:h2:mem:testdb` as `JBDC URL`.
2. Press **Connect** button.

This opens up a webpage where you can manage your database and make SQL queries.

## Starting the application with Docker

1. Navigate to the root directory of the project (where Dockerfile is).
2. Build Docker image for example with command 
    - `docker build -t service-manual .`
3. Create and run the Docker container with command
    - `docker run --name service-manual -p 8080:8080 service-manual`

## Starting the application with cmd / terminal

1. Open command prompt / terminal from root directory of service manual project.
2. Start the application with command:
```
mvnw spring-boot:run
```
on Windows, or
```
mvn spring-boot:run
```
on Mac.

## Testing

Testing of the API can be done for example with cURL or Postman.
Application is using port **8080**.

### Testing with cURL

To test API with cURL, open a new command prompt or terminal and start typing request commands.
Below are some example cURL requests:

#### Get every factory device in the database
```
curl http://localhost:8080/factorydevices
```

#### Get factory device with specific ID
```
curl http://localhost:8080/factorydevices/1
```

#### Get every maintenance task in the database
```
curl http://localhost:8080/tasks
```

#### Get maintenance tasks relating to a specific factory device
```
curl http://localhost:8080/factorydevices/1/tasks
```
Example above should return every maintenance task from the factory device with an ID of 1.

#### Add new maintenance task
```
curl -d "{ \
\"entryTime\": 1640988000000, \
\"description\": \"New maintenance task\", \
\"classification\": \"MINOR\", \
\"taskCompleted\": false }" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/tasks
```
In example above, we are adding a new maintenance task to the database with the information below:

```json
{
  "entryTime": 1640988000000,
  "description": "New maintenance taks",
  "classification": "MINOR",
  "taskCompleted": false
}
```
POST request also returns the created maintenance task as response.

#### Update an existing maintenance task
```
curl -d "{ \
\"classification\": \"MAJOR\" }" \
-H "Content-Type: application/json" \
-X PUT http://localhost:8080/tasks/6
```
In example above, we are updating classification of the maintenance task with an ID of 2.

PUT request also returns the updated maintenance task as response.

#### Assign maintenance tasks to a specific factory device
```
curl -X PUT http://localhost:8080/tasks/6/factorydevice/3
```
In example above, we are assigning maintenance task with an ID of 6 to a factory device with an ID of 3.

PUT requests also returns the updated maintenance task as response.

#### Delete maintenance task
```
curl -X DELETE http://localhost:8080/tasks/3
```
In example above, we are deleting a maintenance task with an ID of 3.

DELETE request also returns the deleted maintenance task as response.

### Testing with Postman

Below are links to Postman environment and request collection which can be used to test the service manual API.

[Service Manual Postman Environment]()

[Service Manual Postman Collection]()

### H2 Console
Changes to the database can also be verified with H2 Console.
Console can be opened by inserting URL below to your browser:

`http://localhost:8080/h2-console`

This opens a login page where you can just press **Connect**,
and it opens up a webpage where you can see manage your database and make SQL queries.

### Unit tests
There area also some unit tests implemented for `FactoryDeviceController` and `MaintenanceTaskController` classes.
Purpose of these tests is only to verify basic functionality on GET, POST, PUT and DELETE requests.
Tests can be run with following command:

```
mvn test
```