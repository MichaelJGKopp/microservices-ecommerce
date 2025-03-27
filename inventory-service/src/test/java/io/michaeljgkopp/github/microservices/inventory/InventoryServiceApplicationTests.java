package io.michaeljgkopp.github.microservices.inventory;

import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

    }

    @Test
    void shouldReturnTrueWhenProductIsInStockWithSufficientQuantity() {

        var response = RestAssured.given()
                .contentType("application/json")
                .queryParam("skuCode", "iphone_15")
                .queryParam("quantity", 100)
                .when()
                .get("/api/inventory")
                .then()
                .log().all()
                .statusCode(200)
                .body("", Matchers.equalTo(true));
    }

    @Test
    void shouldReturnFalseWhenProductIsInStockWithInsufficientQuantity() {

        var response = RestAssured.given()
                .contentType("application/json")
                .queryParam("skuCode", "iphone_15")
                .queryParam("quantity", 101)
                .when()
                .get("/api/inventory")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(Boolean.class);

        assertFalse(response);
    }

    @Test
    void shouldReturnFalseWhenProductSkuCodeIsNotFound() {	// ToDo: should later throw an exception

        var response = RestAssured.given()
                .contentType("application/json")
                .queryParam("skuCode", "unknown_product")
                .queryParam("quantity", 100)
                .when()
                .get("/api/inventory")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(Boolean.class);

        assertFalse(response);
    }
}
