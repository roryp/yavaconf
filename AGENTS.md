# GitHub Copilot Agent Guide for Spring Hello World

This document explains how to use GitHub Copilot as an AI agent to develop, test, and maintain this Spring Boot application.

## What is Agent Mode?

Agent mode allows GitHub Copilot to autonomously perform multi-step tasks like:
- Generating code across multiple files
- Running terminal commands
- Fixing build/test failures iteratively
- Exploring and understanding your codebase

## Prerequisites

1. **VS Code** with GitHub Copilot extension installed
2. **GitHub Copilot Chat** enabled
3. Active GitHub Copilot subscription

## Using Agent Mode

### Starting Agent Mode

In VS Code, open the Copilot Chat panel and use the **Agent** mode (not Ask or Edit mode). You can:
- Click the mode selector dropdown and choose "Agent"
- Or start your prompt with complex, multi-step requests

### Example Prompts for This Project

#### 1. Generate Missing Project Files
```
Generate all the Spring Boot project files as specified in copilot-instructions.md, then run mvn clean install to verify everything works.
```

#### 2. Add a New Endpoint
```
Add a new GET endpoint /health that returns {"status": "UP"} as JSON. Create the model class, update the controller, add unit tests and integration tests, then run all tests.
```

#### 3. Fix Build Failures
```
Run mvn clean install and fix any compilation or test failures.
```

#### 4. Add Request Validation
```
Add input validation to the /greet endpoint to reject names longer than 50 characters. Return a 400 Bad Request with an error message. Add appropriate tests.
```

#### 5. Debug Test Failures
```
Run the tests and explain why any failing tests are failing, then fix them.
```

## Testing the Agent

### Test 1: Verify Project Build

Ask the agent:
```
Run mvn clean install and show me the results
```

**Expected outcome**: Build succeeds with all tests passing.

### Test 2: Test Endpoints

Ask the agent:
```
Start the application with mvn spring-boot:run, wait 5 seconds, then test all endpoints with curl:
- GET http://localhost:8080/
- GET http://localhost:8080/greet
- GET http://localhost:8080/greet?name=Alice
```

**Expected outcomes**:
- `/` returns `Hello, World!`
- `/greet` returns `Hello, World!`
- `/greet?name=Alice` returns `Hello, Alice!`

### Test 3: Code Generation

Ask the agent:
```
Add a POST /echo endpoint that accepts a JSON body with a "message" field and returns the same message. Include unit and integration tests.
```

**Expected outcome**: Agent creates/modifies the controller, adds tests, and runs them to verify.

### Test 4: Iterative Fixing

Ask the agent:
```
Intentionally break the HelloController by removing the @RestController annotation, then ask the agent to run tests and fix any issues.
```

**Expected outcome**: Agent identifies the missing annotation and fixes it.

## Best Practices for Agent Prompts

### Be Specific
❌ "Add tests"  
✅ "Add unit tests for the /greet endpoint covering: default name, custom name, and empty name parameter"

### Request Verification
❌ "Create a new endpoint"  
✅ "Create a new endpoint and run tests to verify it works"

### Provide Context
❌ "Fix the bug"  
✅ "The /greet endpoint returns 'Hello, !' when name is empty. Fix it to return 'Hello, World!' instead"

### Use Multi-Step Instructions
```
1. Add a /goodbye endpoint that returns "Goodbye, {name}!"
2. Create unit tests using @WebMvcTest
3. Create integration tests using @SpringBootTest
4. Run mvn test to verify all tests pass
5. Start the app and test with curl
```

## Project-Specific Instructions

The agent follows rules defined in `.github/copilot-instructions.md`:

| Aspect | Standard |
|--------|----------|
| Framework | Spring Boot 3.5.8 |
| Java Version | 21 |
| Package | `com.example.helloworld` |
| Unit Tests | `@WebMvcTest` with MockMvc |
| Integration Tests | `@SpringBootTest` with `@AutoConfigureMockMvc` or `TestRestTemplate` |
| Validation | Run `mvn clean install` after changes |

## Troubleshooting

### Agent Not Following Instructions
Ensure `.github/copilot-instructions.md` exists and contains project-specific rules.

### Build Failures Persist
Ask:
```
Run mvn clean to clear the target directory, then mvn install with full output
```

### Tests Timing Out
For integration tests that start the server:
```
Increase the test timeout or add @Timeout annotation
```

## Advanced: Creating an AI Agent with Microsoft Agent Framework

For building custom AI agents (not GitHub Copilot), you can use the Microsoft Agent Framework:

### Python Setup

```bash
pip install agent-framework-azure-ai --pre
```

> **Note**: The `--pre` flag is required while Agent Framework is in preview.

### Basic Agent Example

```python
from agent_framework import ChatAgent
from agent_framework.openai import OpenAIChatClient
from openai import AsyncOpenAI

async def create_agent():
    openai_client = AsyncOpenAI(
        base_url="https://models.github.ai/inference",
        api_key="<GITHUB_TOKEN>",
    )
    chat_client = OpenAIChatClient(
        async_client=openai_client,
        model_id="gpt-4o"
    )
    agent = ChatAgent(
        chat_client=chat_client,
        name="SpringHelper",
        instructions="You are a helpful Spring Boot assistant.",
    )
    
    async for chunk in agent.run_stream("How do I create a REST controller?"):
        if chunk.text:
            print(chunk.text, end="", flush=True)
```

### Agent with Tools

```python
from typing import Annotated

def run_maven(
    command: Annotated[str, "Maven command like 'test' or 'clean install'"],
) -> str:
    """Run a Maven command on the project."""
    import subprocess
    result = subprocess.run(
        ["mvn", command], 
        capture_output=True, 
        text=True
    )
    return result.stdout + result.stderr

agent = ChatAgent(
    chat_client=chat_client,
    name="SpringHelper",
    instructions="You help with Spring Boot development.",
    tools=[run_maven],
)
```

## Resources

- [GitHub Copilot Documentation](https://docs.github.com/en/copilot)
- [Microsoft Agent Framework](https://github.com/microsoft/agent-framework)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)
