package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.quickstart.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class GreetingResourceTest {
    @InjectMock
    GreetingService greetingService;

    @BeforeEach
    public void prepareMocks() {
        when(greetingService.message())
                .thenReturn("Aloha from Mockito");
    }

    @Test
    public void testHelloEndpoint() {
        given()
                .when()
                .get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello"));
    }

    @Test
    public void testGreetingEndpoint() {
        given()
                .when()
                .get("/hello/greeting")
                .then()
                .statusCode(200)
                .body(is("Aloha from Mockito"));
    }
}
