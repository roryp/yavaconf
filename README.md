# Spring Hello World

A simple Spring Boot 4.0.0 REST API demonstrating basic web application development with Java 21.

## Features

- REST API endpoints with GET request handling
- Spring Boot 4.0.0 modular architecture
- Java 21 support
- Comprehensive test coverage (unit and integration tests)
- GitHub Copilot agent-ready development environment

## Prerequisites

- **Java 21** or later
- **Maven 3.9+** (or use the included Maven wrapper)

## Quick Start

### Clone the repository

```bash
git clone https://github.com/roryp/yavaconf.git
cd yavaconf
```

### Build and run tests

```bash
mvn clean install
```

### Start the application

```bash
mvn spring-boot:run
```

The application will start on port 8080.

### Test the endpoints

```bash
# Root endpoint
curl http://localhost:8080/

# Greeting endpoint (default)
curl http://localhost:8080/greet

# Greeting endpoint with custom name
curl "http://localhost:8080/greet?name=Alice"
```

## API Endpoints

| Method | Endpoint | Parameters | Description | Response |
|--------|----------|------------|-------------|----------|
| GET | `/` | None | Root endpoint | `Hello, World!` |
| GET | `/greet` | `name` (optional, default: "World") | Personalized greeting | `Hello, {name}!` |

### Examples

```bash
# Returns: Hello, World!
curl http://localhost:8080/

# Returns: Hello, World!
curl http://localhost:8080/greet

# Returns: Hello, Alice!
curl "http://localhost:8080/greet?name=Alice"

# Returns: Hello, Bob!
curl "http://localhost:8080/greet?name=Bob"
```

## Project Structure

```
yavaconf/
├── .devcontainer/          # VS Code Dev Container configuration
├── .github/
│   └── copilot-instructions.md  # GitHub Copilot project instructions
├── src/
│   ├── main/
│   │   ├── java/com/example/helloworld/
│   │   │   ├── HelloWorldApplication.java  # Main application class
│   │   │   └── HelloController.java        # REST controller
│   │   └── resources/
│   │       └── application.properties      # Application configuration
│   └── test/
│       └── java/com/example/helloworld/
│           ├── HelloControllerTest.java           # Unit tests
│           ├── HelloControllerIntegrationTest.java # Integration tests
│           └── HelloWorldApplicationTests.java    # Full application tests
├── AGENTS.md               # GitHub Copilot Agent guide
├── pom.xml                 # Maven build configuration
└── README.md               # This file
```

## Build Commands

| Command | Description |
|---------|-------------|
| `mvn clean install` | Build and run all tests |
| `mvn test` | Run tests only |
| `mvn spring-boot:run` | Start the application |
| `mvn clean` | Clean build artifacts |

## Technology Stack

- **Framework**: Spring Boot 4.0.0
- **Language**: Java 21
- **Build Tool**: Maven
- **Testing**: JUnit 5, Spring Boot Test, MockMvc, TestRestTemplate

## Spring Boot 4.0.0 Features

This project uses Spring Boot 4.0.0's new modular architecture:

- `spring-boot-starter-webmvc` - Web MVC starter (replaces `spring-boot-starter-web`)
- `spring-boot-starter-webmvc-test` - MockMvc test support
- `spring-boot-resttestclient` - TestRestTemplate support

### New Package Locations

| Component | Spring Boot 4.0.0 Package |
|-----------|---------------------------|
| `@WebMvcTest` | `org.springframework.boot.webmvc.test.autoconfigure` |
| `@AutoConfigureMockMvc` | `org.springframework.boot.webmvc.test.autoconfigure` |
| `TestRestTemplate` | `org.springframework.boot.resttestclient` |
| `@AutoConfigureTestRestTemplate` | `org.springframework.boot.resttestclient.autoconfigure` |

## Development Environment

### VS Code Dev Container

This project includes a Dev Container configuration for VS Code:

1. Install [Docker](https://www.docker.com/) and [VS Code](https://code.visualstudio.com/)
2. Install the [Dev Containers extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
3. Open the project folder in VS Code
4. Click "Reopen in Container" when prompted

The Dev Container includes:
- Java 21
- Maven
- Spring Boot extensions
- GitHub Copilot

### GitHub Copilot Agent

This project is optimized for GitHub Copilot's Agent mode. See [AGENTS.md](AGENTS.md) for detailed instructions on using AI-assisted development.

## Testing

The project includes three types of tests:

### Unit Tests (`HelloControllerTest.java`)
Tests the controller in isolation using `@WebMvcTest`:
```bash
mvn test -Dtest=HelloControllerTest
```

### Integration Tests (`HelloControllerIntegrationTest.java`)
Tests with full Spring context using `@SpringBootTest` with MockMvc:
```bash
mvn test -Dtest=HelloControllerIntegrationTest
```

### Full Application Tests (`HelloWorldApplicationTests.java`)
Tests with real HTTP server using `TestRestTemplate`:
```bash
mvn test -Dtest=HelloWorldApplicationTests
```

## Configuration

Application settings are in `src/main/resources/application.properties`:

```properties
server.port=8080
```

## License

This project is available for educational and demonstration purposes.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests with `mvn clean install`
5. Submit a pull request

## Resources

- [Spring Boot 4.0.0 Reference](https://docs.spring.io/spring-boot/4.0.0/reference/)
- [Spring Boot 4.0.0 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide)
- [Spring Boot Modularization Blog](https://spring.io/blog/2025/10/28/modularizing-spring-boot)
