package tests.CartsAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CartsUpdateTest {

    private final String BASE_URI = "https://fakestoreapi.com";
    private final int EXISTING_CART_ID = 2; // A common existing cart ID on the mock API for update

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for updating an existing cart using a PUT request.
     * Uses a predefined `EXISTING_CART_ID` for reliable execution with the mock API.
     * Asserts status code, content type, and updated userId/date.
     */
    @Test
    public void testUpdateCart() {
        String updatedBody = """
            {
              "userId": 7,
              "date": "2024-06-15",
              "products": [
                {"productId":3, "quantity":5}
              ]
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .when()
                .put("/carts/" + EXISTING_CART_ID)
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("userId", equalTo(7))
                .body("date", equalTo("2024-06-15"))
                .body("id", equalTo(EXISTING_CART_ID)); // Mock API usually returns the same ID
    }
}
