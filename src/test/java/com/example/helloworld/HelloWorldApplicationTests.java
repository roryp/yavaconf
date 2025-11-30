package com.example.helloworld;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class HelloWorldApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Context loads successfully")
    void contextLoads() {
    }

    @Test
    @DisplayName("Integration with TestRestTemplate: GET / returns Hello, World!")
    void index_noParameters_returnsHelloWorld() {
        String url = "http://localhost:" + port + "/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello, World!");
    }

    @Test
    @DisplayName("Integration with TestRestTemplate: GET /greet returns Hello, World!")
    void greet_noParameters_returnsHelloWorld() {
        String url = "http://localhost:" + port + "/greet";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello, World!");
    }

    @Test
    @DisplayName("Integration with TestRestTemplate: GET /greet?name=Charlie returns Hello, Charlie!")
    void greet_withNameParameter_returnsHelloName() {
        String url = "http://localhost:" + port + "/greet?name=Charlie";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello, Charlie!");
    }

    @Test
    @DisplayName("Integration with TestRestTemplate: GET /greet?name= returns Hello, World!")
    void greet_withEmptyNameParameter_returnsHelloWorld() {
        String url = "http://localhost:" + port + "/greet?name=";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello, World!");
    }

    @Test
    @DisplayName("Integration with TestRestTemplate: GET /invalid returns 404")
    void invalidEndpoint_returns404() {
        String url = "http://localhost:" + port + "/invalid";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
