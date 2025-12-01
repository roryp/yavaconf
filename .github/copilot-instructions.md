# Copilot Instructions for Spring Hello World

This is a Spring Boot 4.0.0 REST API project using Java 21 with Maven. Spring Boot 4.0.0 uses a modular architecture with renamed starters and relocated test packages.

## Dev environment setup

- Java 21 is required (devcontainer provides this automatically)
- Port 8080 is forwarded for local development
- Do NOT add `postCreateCommand` or `postStartCommand` to devcontainer.json

## Build and test commands

| Command | Purpose |
|---------|---------|
| `mvn clean install` | Build project and run all tests (use this to validate changes) |
| `mvn test` | Run tests only |
| `mvn spring-boot:run` | Start the application on port 8080 |
| `mvn clean` | Clear target directory if build issues occur |

Wait 3-5 seconds after starting before making HTTP requests.

## Testing instructions

- Run `mvn clean install` after any code change to verify compilation and tests pass
- All 15 tests must pass before considering a task complete
- Test the running application with curl:
  - `curl http://localhost:8080/` → returns `Hello, World!`
  - `curl http://localhost:8080/greet` → returns `Hello, World!`
  - `curl "http://localhost:8080/greet?name=Alice"` → returns `Hello, Alice!`

## Code style and conventions

### Controllers
- Use `@RestController` for REST endpoints
- Use `@GetMapping` for HTTP GET mappings
- Use `@RequestParam` with `defaultValue` for optional parameters
- Package: `com.example.helloworld`

### Testing (Spring Boot 4.0.0 specific)
- Unit tests: Use `@WebMvcTest` from `org.springframework.boot.webmvc.test.autoconfigure`
- Integration tests with MockMvc: Use `@SpringBootTest` + `@AutoConfigureMockMvc` from `org.springframework.boot.webmvc.test.autoconfigure`
- Integration tests with TestRestTemplate: Use `@SpringBootTest` + `@AutoConfigureTestRestTemplate` from `org.springframework.boot.resttestclient.autoconfigure`
- Import `TestRestTemplate` from `org.springframework.boot.resttestclient`
- Use `@DisplayName` for descriptive test names
- Follow naming convention: `methodName_condition_expectedResult`

## Spring Boot 4.0.0 dependency notes

The project uses these Spring Boot 4.0.0 modular dependencies:

| Dependency | Purpose |
|------------|---------|
| `spring-boot-starter-webmvc` | Main web starter (replaces `spring-boot-starter-web`) |
| `spring-boot-starter-test` | Core test support |
| `spring-boot-starter-webmvc-test` | MockMvc and `@WebMvcTest` support |
| `spring-boot-resttestclient` | `TestRestTemplate` support |

## API endpoints

| Method | Path | Parameters | Response |
|--------|------|------------|----------|
| GET | `/` | None | `Hello, World!` |
| GET | `/greet` | `name` (optional, default: "World") | `Hello, {name}!` |

## PR instructions

- Run `mvn clean install` and ensure all 15 tests pass before committing
- Test endpoints manually with curl after starting the application
- Update tests when adding or modifying endpoints
