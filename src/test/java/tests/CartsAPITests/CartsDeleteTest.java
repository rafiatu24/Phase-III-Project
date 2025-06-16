package tests.CartsAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CartsDeleteTest {

    private final String BASE_URI = "https://fakestoreapi.com";
    private final int EXISTING_CART_ID = 3; // A common existing cart ID on the mock API for delete

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for deleting a cart using a DELETE request.
     * Uses a predefined `EXISTING_CART_ID` for reliable execution with the mock API.
     * Asserts status code, content type, and the ID of the "deleted" cart.
     */
    @Test
    public void testDeleteCart() {
        given()
                .when()
                .delete("/carts/" + EXISTING_CART_ID)
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("id", equalTo(EXISTING_CART_ID)); // Mock API usually returns the ID of the deleted item
    }
}

