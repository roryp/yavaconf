# Spring Boot Hello World REST API

A simple Spring Boot REST API demonstrating basic HTTP endpoint configuration with greeting functionality.

## Tech Stack

- **Framework:** Spring Boot 3.5.8
- **Language:** Java 21
- **Build Tool:** Maven
- **Testing:** JUnit 5, Spring Boot Test, MockMvc, TestRestTemplate

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/roryp/yavaconf.git
cd yavaconf
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on port `8080` by default.

## API Endpoints

| Method | Path | Parameters | Response |
|--------|------|------------|----------|
| GET | `/` | None | `Hello, World!` |
| GET | `/greet` | `name` (optional, default: "World") | `Hello, {name}!` |

### Examples

```bash
# Get default greeting
curl http://localhost:8080/
# Response: Hello, World!

# Get default greeting via /greet endpoint
curl http://localhost:8080/greet
# Response: Hello, World!

# Get personalized greeting
curl "http://localhost:8080/greet?name=Alice"
# Response: Hello, Alice!
```

## Build and Test Commands

| Command | Description |
|---------|-------------|
| `mvn clean install` | Build and run all tests |
| `mvn test` | Run tests only |
| `mvn spring-boot:run` | Start the application |
| `mvn clean package` | Package the application as a JAR |

## Project Structure

```
yavaconf/
├── .devcontainer/
│   └── devcontainer.json          # VS Code devcontainer configuration
├── .github/
│   └── copilot-instructions.md    # Copilot instructions
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/helloworld/
│   │   │       ├── HelloWorldApplication.java  # Main application class
│   │   │       └── HelloController.java        # REST controller
│   │   └── resources/
│   │       └── application.properties          # Application configuration
│   └── test/
│       └── java/
│           └── com/example/helloworld/
│               ├── HelloControllerTest.java           # Unit tests
│               ├── HelloControllerIntegrationTest.java # Integration tests with MockMvc
│               └── HelloWorldApplicationTests.java     # Integration tests with TestRestTemplate
├── .gitignore
├── pom.xml                         # Maven build configuration
└── README.md                       # This file
```

## Testing

The project includes comprehensive tests using different testing approaches:

### Unit Tests (`HelloControllerTest.java`)

Uses `@WebMvcTest` to test the controller in isolation without loading the full Spring context.

```bash
mvn test -Dtest=HelloControllerTest
```

### Integration Tests

#### MockMvc Integration (`HelloControllerIntegrationTest.java`)

Uses `@SpringBootTest` with `@AutoConfigureMockMvc` for full integration testing with MockMvc.

```bash
mvn test -Dtest=HelloControllerIntegrationTest
```

#### TestRestTemplate Integration (`HelloWorldApplicationTests.java`)

Uses `@SpringBootTest` with `WebEnvironment.RANDOM_PORT` and `TestRestTemplate` for testing against a running server.

```bash
mvn test -Dtest=HelloWorldApplicationTests
```

### Run All Tests

```bash
mvn test
```

## Development Environment

This project includes a devcontainer configuration for VS Code, providing a consistent development environment with:

- Java 21
- Maven
- Spring Boot extensions
- GitHub Copilot integration

To use the devcontainer:

1. Install [Visual Studio Code](https://code.visualstudio.com/)
2. Install the [Dev Containers extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
3. Open the project folder in VS Code
4. When prompted, click "Reopen in Container" or use the command palette (F1) and select "Dev Containers: Reopen in Container"

## Configuration

The application can be configured via `src/main/resources/application.properties`:

| Property | Default | Description |
|----------|---------|-------------|
| `server.port` | `8080` | HTTP server port |

## License

This project is provided as-is for demonstration purposes.
