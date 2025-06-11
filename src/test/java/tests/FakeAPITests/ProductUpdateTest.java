package tests.FakeAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductUpdateTest {

    private final String BASE_URI = "https://fakestoreapi.com";
    // Using a known, existing product ID for mock API consistency.
    // The Fake Store API typically has product with ID 1.
    private final int EXISTING_PRODUCT_ID = 1;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for updating an existing product using a PUT request.
     * Uses a predefined `EXISTING_PRODUCT_ID` for reliable execution with the mock API.
     * Asserts status code, content type header, and updated title/price in the response.
     */
    @Test
    public void testUpdateProduct() {
        String updatedBody = """
            {
              "title": "Updated Product Title from Separate Test",
              "price": 85.25,
              "description": "This product has been updated by an independent test file.",
              "image": "https://i.pravatar.cc",
              "category": "apparel"
            }
            """;

        given()
                .contentType(ContentType.JSON) // Set content type to JSON
                .body(updatedBody)             // Attach the updated request body
                .when()
                .put("/products/" + EXISTING_PRODUCT_ID) // Send PUT request to /products/{id}
                .then()
                .statusCode(200)               // Assert that the status code is 200
                .header("Content-Type", equalTo("application/json; charset=utf-8")) // Validate Content-Type header
                .body("title", equalTo("Updated Product Title from Separate Test")) // Assert the updated title
                .body("price", equalTo(85.25f)); // Assert the updated price (use 'f' for float comparison)
    }
}