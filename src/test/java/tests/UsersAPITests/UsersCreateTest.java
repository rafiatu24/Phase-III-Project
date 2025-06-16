package tests.UsersAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersCreateTest {

    private final String BASE_URI = "https://fakestoreapi.com";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for creating a new user using a POST request.
     * It sends a JSON request body and asserts:
     * - Response status code is 200 OK.
     * - Response 'Content-Type' header is 'application/json; charset=utf-8'.
     * - An 'id' is present and not null in the response.
     * Removed assertions for email and username as the mock API does not echo them back.
     *
     * Note: The Fake Store API is a mock API. IDs generated are temporary and not persistent.
     */
    @Test
    public void testCreateUser() {
        String requestBody = """
            {
              "email": "testuser@example.com",
              "username": "testuser123",
              "password": "strongPassword1!",
              "name": {
                "firstname": "Test",
                "lastname": "User"
              },
              "address": {
                "city": "newyork",
                "street": "123 Test St",
                "number": 10,
                "zipcode": "10001",
                "geolocation": {
                  "lat": "40.7128",
                  "long": "-74.0060"
                }
              },
              "phone": "1-234-567-8901"
            }
            """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                // Removed assertion: .body("email", equalTo("testuser@example.com"))
                // Removed assertion: .body("username", equalTo("testuser123"))
                .body("id", notNullValue()) // Assert that an 'id' is present and not null
                .extract().response();

        int createdUserId = response.path("id");
        System.out.println("Created User ID (temporary): " + createdUserId);
    }
}
