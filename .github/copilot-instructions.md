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

---

## Coding Standards

### Controllers
- Use `@RestController` for REST endpoints
- Use `@GetMapping`, `@PostMapping`, etc. for HTTP method mappings
- Use `@RequestParam` with `defaultValue` for optional parameters
- Note: Spring treats empty string params (`?name=`) the same as using defaultValue

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

---

## Commands

### Build & Test
```bash
mvn clean install          # Build and run all tests
mvn test                   # Run tests only
mvn package -DskipTests    # Package without tests
```

### Run Application
```bash
# Option 1: Maven (recommended for development)
mvn spring-boot:run

# Option 2: JAR file (after packaging)
java -jar target/helloworld-0.0.1-SNAPSHOT.jar
```

### Server Management
```bash
# Kill any process using port 8080 before starting
lsof -ti:8080 | xargs kill -9 2>/dev/null

# Check if server is running
curl -s http://localhost:8080/ || echo "Server not running"
```

### Test Endpoints
```bash
curl http://localhost:8080/                      # Returns: Hello, World!
curl http://localhost:8080/greet                 # Returns: Hello, World!
curl "http://localhost:8080/greet?name=Alice"    # Returns: Hello, Alice!
```

---

## API Endpoints

| Method | Path | Parameters | Response |
|--------|------|------------|----------|
| GET | `/` | None | "Hello, World!" |
| GET | `/greet` | `name` (optional, default: "World") | "Hello, {name}!" |

---

## Development Environment
- Uses devcontainer with Microsoft Java 21 image
- VS Code extensions: Java Extension Pack, Spring Boot tools
- Port 8080 is forwarded for local development
- Do NOT add `postCreateCommand` or `postStartCommand` to devcontainer.json


---

## Copilot Workflow Guidelines

### When Creating New Files
1. Always create files with complete, working code
2. Include all necessary imports
3. Follow existing package structure: `com.example.helloworld`

### When Running the Server
1. First check if port 8080 is free: `lsof -ti:8080`
2. Kill existing processes if needed before starting
3. Use background execution (`&`) when starting server for testing
4. Wait 3-5 seconds after starting before making HTTP requests

### When Writing Tests
1. Understand Spring's `@RequestParam` behavior:
   - Empty string `""` with `defaultValue` uses the default value
   - Tests should reflect actual Spring behavior, not assumed behavior
2. Run `mvn test` to validate all tests pass before considering task complete
3. Use `WebEnvironment.RANDOM_PORT` for integration tests to avoid port conflicts

### When Troubleshooting
- **Port already in use**: Kill existing Java processes with `pkill -f helloworld` or `lsof -ti:8080 | xargs kill -9`
- **Build failures**: Run `mvn clean` then rebuild
- **Test failures**: Check Spring documentation for actual behavior vs expected

### Preferred Patterns
- DO: Run `mvn clean install` to build and test in one step
- DO: Check server health with curl before running integration tests manually
- DO: Use absolute paths when referencing files
- DON'T: Assume empty string behavior without testing
- DON'T: Start multiple server instances on the same port
