package tests.CartsAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CartsCreateTest {

    private final String BASE_URI = "https://fakestoreapi.com";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Test case for creating a new cart using a POST request.
     * It sends a JSON request body and asserts:
     * - Response status code is 200 OK.
     * - Response 'Content-Type' header is 'application/json; charset=utf-8'.
     * - The 'userId' and 'date' in the response match the ones sent.
     * - An 'id' is present and not null in the response.
     *
     * Note: The Fake Store API is a mock API. IDs generated are temporary and not persistent.
     */
    @Test
    public void testCreateCart() {
        String requestBody = """
            {
              "userId": 5,
              "date": "2023-01-20",
              "products": [
                {"productId":1, "quantity":2},
                {"productId":4, "quantity":1}
              ]
            }
            """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/carts")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("userId", equalTo(5))
                .body("date", equalTo("2023-01-20"))
                .body("id", notNullValue())
                .extract().response();

        int createdCartId = response.path("id");
        System.out.println("Created Cart ID (temporary): " + createdCartId);
    }
}
