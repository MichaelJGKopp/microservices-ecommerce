package io.michaeljgkopp.github.microservices.order;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateOrder() {
        String requestBody = """
                {
                    "skuCode": "iphone_15",
                    "price": 1000,
                    "quantity": 2
                }
                """;

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/orders")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("orderNumber", Matchers.matchesPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"))
                .body("skuCode", Matchers.equalTo("iphone_15"))
                .body("price", Matchers.equalTo(1000))
                .body("quantity", Matchers.equalTo(2));
    }
}
