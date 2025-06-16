package tests.CartsAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CartsGetTest {

    private final String BASE_URI = "https://fakestoreapi.com";
    private final int EXISTING_CART_ID = 1; // A common existing cart ID on the mock API
    private final int EXISTING_USER_ID_FOR_CART = 1; // A common existing user ID for carts

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for retrieving a single cart by its ID using a GET request.
     * Asserts status code, content type, and matching ID.
     */
    @Test
    public void testGetCartById() {
        given()
                .when()
                .get("/carts/" + EXISTING_CART_ID)
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("id", equalTo(EXISTING_CART_ID));
    }

    /**
     * Test case for retrieving all carts using a GET request.
     * Asserts status code, content type, and that the response is a non-empty array.
     */
    @Test
    public void testGetAllCarts() {
        given()
                .when()
                .get("/carts")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("", isA(Iterable.class))
                .body("size()", greaterThan(0));
    }

    /**
     * Test case for retrieving carts for a specific user.
     * Asserts status code, content type, and that the response is a non-empty array.
     */
    @Test
    public void testGetCartsByUserId() {
        given()
                .when()
                .get("/carts/user/" + EXISTING_USER_ID_FOR_CART)
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("", isA(Iterable.class))
                .body("size()", greaterThan(0))
                .body("[0].userId", equalTo(EXISTING_USER_ID_FOR_CART)); // Verify at least one cart belongs to the user
    }
}
