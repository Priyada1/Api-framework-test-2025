package TestCases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonSchemaValidationTest {


    @Test
    public void schemaValidation() {
        Response res = given().contentType(ContentType.JSON).log().all().when()
                .get("https://reqres.in"+ "/api/users?page=2");
        String jsonResponseBody = res.body().asString();
        System.out.println("validating Json Schema Using RestAssured JsonSchemaValidator >>>>>>>>>>>>>"+jsonResponseBody);
        assertThat(jsonResponseBody, matchesJsonSchemaInClasspath("jsonSchema.json"));
    }
}