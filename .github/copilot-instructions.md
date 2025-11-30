# Copilot Instructions for Spring Hello World

## Project Overview

This is a Spring Boot 4.0.0 REST API project using Java 21 with Maven. Spring Boot 4.0.0 introduced a modular architecture with renamed starters and relocated test packages.

## Tech Stack

- Framework: Spring Boot 4.0.0
- Language: Java 21
- Build Tool: Maven
- Testing: JUnit 5, Spring Boot Test, MockMvc, TestRestTemplate
- Package: `com.example.helloworld`

## Initial Setup (Required After Clone)

After cloning, check if `pom.xml` exists. If not, generate all project files in this order:

1. `pom.xml` - Maven build file with Spring Boot 4.0.0 and Java 21
   - Use `spring-boot-starter-webmvc` (not `spring-boot-starter-web`)
   - Add `spring-boot-starter-webmvc-test` for MockMvc test support
   - Add `spring-boot-resttestclient` for TestRestTemplate support
2. `src/main/java/com/example/helloworld/HelloWorldApplication.java` - Main application class with `@SpringBootApplication`
3. `src/main/java/com/example/helloworld/HelloController.java` - REST controller
4. `src/main/resources/application.properties` - Application configuration
5. `src/test/java/com/example/helloworld/HelloControllerTest.java` - Unit tests using `@WebMvcTest`
6. `src/test/java/com/example/helloworld/HelloControllerIntegrationTest.java` - Integration tests using `@SpringBootTest` with `@AutoConfigureMockMvc`
7. `src/test/java/com/example/helloworld/HelloWorldApplicationTests.java` - Integration tests using `@SpringBootTest` with `TestRestTemplate`

Always run `mvn clean install` after generating files to verify compilation and tests pass.

## Build and Test Commands

```bash
mvn clean install          # Build and run all tests (use this to validate)
mvn test                   # Run tests only
mvn spring-boot:run        # Start the application
```

Wait 3-5 seconds after starting before making HTTP requests.

## API Endpoints

| Method | Path | Parameters | Response |
|--------|------|------------|----------|
| GET | `/` | None | "Hello, World!" |
| GET | `/greet` | `name` (optional, default: "World") | "Hello, {name}!" |

## Coding Standards

### Controllers
- Use `@RestController` for REST endpoints
- Use `@GetMapping` for HTTP GET mappings
- Use `@RequestParam` with `defaultValue` for optional parameters
- Spring treats empty string params (`?name=`) the same as using defaultValue

### Testing
- Unit tests: Use `@WebMvcTest` to test controllers in isolation
  - Import from `org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest`
- Integration tests: Use `@SpringBootTest` with either:
  - `@AutoConfigureMockMvc` (import from `org.springframework.boot.webmvc.test.autoconfigure`)
  - `WebEnvironment.RANDOM_PORT` + `TestRestTemplate` with `@AutoConfigureTestRestTemplate`
    - Import `TestRestTemplate` from `org.springframework.boot.resttestclient`
    - Import `@AutoConfigureTestRestTemplate` from `org.springframework.boot.resttestclient.autoconfigure`
- Use `@DisplayName` for descriptive test names
- Follow naming convention: `methodName_condition_expectedResult`
- Test happy path, edge cases (empty parameters), and error handling (404, 405)

## Validation Checklist

Before considering any task complete:
1. Run `mvn clean install` - all tests must pass
2. Start the application with `mvn spring-boot:run`
3. Test endpoints with curl:
   - `curl http://localhost:8080/` returns "Hello, World!"
   - `curl http://localhost:8080/greet` returns "Hello, World!"
   - `curl "http://localhost:8080/greet?name=Alice"` returns "Hello, Alice!"

## Development Environment

- Uses devcontainer with Microsoft Java 21 image
- Port 8080 is forwarded for local development
- Do NOT add `postCreateCommand` or `postStartCommand` to devcontainer.json
- Playwright MCP server enabled for browser-based testing via Copilot

## Troubleshooting

- Build failures: Run `mvn clean` then rebuild
- Test failures: Check Spring documentation for actual `@RequestParam` behavior vs expected

## Spring Boot 4.0.0 Migration Notes

Spring Boot 4.0.0 introduced a modular architecture. Key changes:

| Spring Boot 3.x | Spring Boot 4.0.0 |
|-----------------|-------------------|
| `spring-boot-starter-web` | `spring-boot-starter-webmvc` |
| `spring-boot-starter-test` (includes all) | `spring-boot-starter-test` + `spring-boot-starter-webmvc-test` + `spring-boot-resttestclient` |
| `org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest` | `org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest` |
| `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc` | `org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc` |
| `org.springframework.boot.test.web.client.TestRestTemplate` | `org.springframework.boot.resttestclient.TestRestTemplate` |
| TestRestTemplate auto-configured | Requires `@AutoConfigureTestRestTemplate` annotation |
