package tests.ProductsAPITests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductGetTest {

    private final String BASE_URI = "https://fakestoreapi.com";
    // Using a known, existing product ID for mock API consistency.
    // The Fake Store API typically has product with ID 1.
    private final int EXISTING_PRODUCT_ID = 1;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for retrieving a single product by its ID using a GET request.
     * Uses a predefined `EXISTING_PRODUCT_ID` for reliable execution with the mock API.
     * Asserts status code, content type header, and matching ID in the response.
     */
    @Test
    public void testGetProductById() {
        given()
                .when()
                .get("/products/" + EXISTING_PRODUCT_ID) // Send GET request to /products/{id}
                .then()
                .statusCode(200)                      // Assert that the status code is 200
                .header("Content-Type", equalTo("application/json; charset=utf-8")) // Validate Content-Type header
                .body("id", equalTo(EXISTING_PRODUCT_ID)); // Assert the 'id' in the response
    }

    /**
     * Test case for retrieving all products using a GET request.
     * Asserts status code, content type header, and that the response is a non-empty array.
     */
    @Test
    public void testGetAllProducts() {
        given()
                .when()
                .get("/products")             // Send GET request to /products
                .then()
                .statusCode(200)              // Assert that the status code is 200
                .header("Content-Type", equalTo("application/json; charset=utf-8")) // Validate Content-Type header
                .body("", isA(Iterable.class)) // Assert that the root is an iterable (i.e., a JSON array)
                .body("size()", greaterThan(0)); // Assert that the size of the array is greater than 0
    }
}