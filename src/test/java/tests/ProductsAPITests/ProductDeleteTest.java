package tests.ProductsAPITests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductDeleteTest {

    private final String BASE_URI = "https://fakestoreapi.com";
    // Using a known, existing product ID for mock API consistency.
    // The Fake Store API typically has product with ID 1.
    private final int EXISTING_PRODUCT_ID = 1;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for deleting a product using a DELETE request.
     * Uses a predefined `EXISTING_PRODUCT_ID` for reliable execution with the mock API.
     * Asserts status code, content type header, and the ID of the "deleted" product
     * (as returned by the mock API).
     */
    @Test
    public void testDeleteProduct() {
        given()
                .when()
                .delete("/products/" + EXISTING_PRODUCT_ID) // Send DELETE request to /products/{id}
                .then()
                .statusCode(200)                         // Assert that the status code is 200
                .header("Content-Type", equalTo("application/json; charset=utf-8")) // Validate Content-Type header
                .body("id", equalTo(EXISTING_PRODUCT_ID));  // Assert the 'id' in the response
    }
}
