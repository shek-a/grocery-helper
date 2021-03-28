# Grocery Helper

The Grocery Helper Application is a Spring Boot application which exposes Restful APIs to be consumed by the
The Grocery Helper Web App https://github.com/shek-a/grocery-helper-web

The Grocery data is stored in a H2 in-memory database.

## Requirements
JDK 1.8 or above is needed to execute this program


## Running the Program

### Test

Run the tests via the gradle wrapper using the following command:

```sh
./gradlew clean test
```
See the 'index.html' report in the build folder
	- build/reports/tests/test/index.html

### Build
To build the Grocery Helper Application, run the following command:

```sh
./gradlew clean build
```

### Run

To Run the Grocery Helper Application, run the following command:

```sh
./gradlew bootRun
```
