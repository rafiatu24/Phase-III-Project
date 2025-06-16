package tests.UsersAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersGetTest {

    private final String BASE_URI = "https://fakestoreapi.com";
    private final int EXISTING_USER_ID = 1; // A common existing user ID on the mock API

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for retrieving a single user by their ID using a GET request.
     * Asserts status code, content type, and matching ID.
     */
    @Test
    public void testGetUserById() {
        given()
                .when()
                .get("/users/" + EXISTING_USER_ID)
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("id", equalTo(EXISTING_USER_ID));
    }

    /**
     * Test case for retrieving all users using a GET request.
     * Asserts status code, content type, and that the response is a non-empty array.
     */
    @Test
    public void testGetAllUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("", isA(Iterable.class))
                .body("size()", greaterThan(0));
    }
}
