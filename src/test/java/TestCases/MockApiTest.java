package TestCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // Use MOCK web environment
//public class MockApiTest {
//    @Autowired
//    private WebApplicationContext context;
//
//    private int upstreamUserId; // Store the user ID here
//
//    @BeforeClass
//    public void setUp() {
//        // Set up RestAssuredMockMvc to use our MockMvc
//        RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context).build());
//
//        //  Mock the JSONPlaceholder API for creating a user (simulated)
//        upstreamUserId = given()
//                .contentType("application/json")
//                .body(Map.of("name", "John Doe", "job", "Software Engineer")) // Example user data
//                .when()
//                .post("/mock/users") //  Mocked endpoint
//                .then()
//                .statusCode(HttpStatus.CREATED.value()) //Expect 201 Created
//                .extract()
//                .path("id"); // Extract the "id" from the response.  JSONPlaceholder returns this.
//    }
//
//    @Test
//    public void testMyServiceThatUsesUpstreamUserId() {
//        // Now, use the upstreamUserId in your test
//        given()
//                // Setup for calling your service
//                .contentType("application/json")
//                .body(Map.of("userId", upstreamUserId, "data", "Some data for my service")) // Include the ID
//                .when()
//                .post("/your-service/process-user-data") // Replace with your service endpoint
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("status", equalTo("Success"))
//                .body("userId", equalTo(upstreamUserId));
//    }
//
//    //  Additional Mock Endpoints for illustration
//    @Test
//    public void testHelloEndpoint() {
//        given()
//                .when()
//                .get("/mock/hello")
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body(equalTo("Hello from the mock API!"));
//    }
//
//    @Test
//    public void testUserEndpoint() {
//        given()
//                .when()
//                .get("/mock/user/123")
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("id", equalTo(123))
//                .body("name", equalTo("User 123"))
//                .body("email", equalTo("user123@example.com"));
//    }
//
//    @Test
//    public void testEchoEndpoint() {
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("message", "This is a test");
//        requestBody.put("number", 42);
//
//        given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/mock/echo")
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("message", equalTo("This is a test"))
//                .body("number", equalTo(42));
//    }
//
//    @Test
//    public void testGreetEndpoint() {
//        given()
//                .param("name", "John")
//                .when()
//                .get("/mock/greet")
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body(equalTo("Hello, John!"));
//    }
//}