# Copilot Instructions for Spring Hello World

## Project Overview
This is a Spring Boot 3.5 REST API project using Java 21 with Maven.

## Tech Stack
- **Framework**: Spring Boot 3.5
- **Language**: Java 21
- **Build Tool**: Maven
- **Testing**: JUnit 5, Spring Boot Test, MockMvc, TestRestTemplate

## Project Structure
```
src/
├── main/java/com/example/helloworld/
│   ├── HelloWorldApplication.java    # Main application class
│   └── HelloController.java          # REST controller
├── main/resources/
│   └── application.properties        # App configuration
└── test/java/com/example/helloworld/
    ├── HelloControllerTest.java              # Unit tests (@WebMvcTest)
    ├── HelloControllerIntegrationTest.java   # Integration tests (@SpringBootTest + MockMvc)
    └── HelloWorldApplicationTests.java       # Integration tests (@SpringBootTest + TestRestTemplate)
```

## Coding Standards

### Controllers
- Use `@RestController` for REST endpoints
- Use `@GetMapping`, `@PostMapping`, etc. for HTTP method mappings
- Use `@RequestParam` with `defaultValue` for optional parameters

### Testing
- **Unit Tests**: Use `@WebMvcTest` to test controllers in isolation
- **Integration Tests**: Use `@SpringBootTest` with either:
  - `@AutoConfigureMockMvc` for faster tests without real HTTP
  - `WebEnvironment.RANDOM_PORT` + `TestRestTemplate` for full HTTP tests
- Use `@DisplayName` for descriptive test names
- Follow naming convention: `methodName_condition_expectedResult`

### Test Coverage Should Include
- Happy path scenarios
- Edge cases (empty parameters, special characters)
- Error handling (404 for missing endpoints, 405 for wrong HTTP methods)
- Default value behavior

## Commands
- **Build**: `mvn clean install`
- **Run**: `mvn spring-boot:run`
- **Test**: `mvn test`
- **Package**: `mvn package`

## API Endpoints
| Method | Path | Parameters | Response |
|--------|------|------------|----------|
| GET | `/` | None | "Hello, World!" |
| GET | `/greet` | `name` (optional, default: "World") | "Hello, {name}!" |

## Development Environment
- Uses devcontainer with Microsoft Java 21 image
- VS Code extensions: Java Extension Pack, Spring Boot tools
- Port 8080 is forwarded for local development
