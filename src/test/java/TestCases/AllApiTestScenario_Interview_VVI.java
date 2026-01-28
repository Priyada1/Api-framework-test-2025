package TestCases;


import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Optional;

import static io.restassured.RestAssured.given;


@Slf4j
public class AllApiTestScenario_Interview_VVI {
    JSONObject payload;
    String firstName;
    @BeforeClass
    public void setUp(){

        firstName = "abc"+ RandomUtils.nextInt(1,1000);
        payload = new JSONObject();
        payload.put("firstname",firstName);
        payload.put("lastname","xyz"+RandomUtils.nextInt(1001,2000));
        payload.put("totalprice",4000);
        payload.put("depositpaid",true);
        payload.put("additionalneeds","breakfast");

        // Set checkin date as today and checkout date as tomorrow
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        JSONObject dateRanges = new JSONObject();
        dateRanges.put("checkin", today.format(formatter));
        dateRanges.put("checkout", tomorrow.format(formatter));
        payload.put("bookingdates",dateRanges);
    }

    @Test(priority = 1)
    public void sendGetRequestAndValidateStatusCode() {
        log.info("Scenario 1: Send GET request and validate status code");
        // Implementation will be added here

        Response response = given().baseUri("https://restful-booker.herokuapp.com").contentType("application/json")
                .header(new Header("access-token","12345677")).body(payload.toString()).when().post("/booking");

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,response.prettyPrint());
        JsonPath jsonPath = response.jsonPath();

        Assert.assertNotNull(jsonPath.get("bookingid"));

        LinkedHashMap<String, Object> bookingObject = jsonPath.getJsonObject("booking");

        JSONArray jsonArray = new JSONArray(Optional.ofNullable(jsonPath.get("test")));

        Assert.assertEquals(bookingObject.get("firstname").toString(),firstName);
    }

    @Test(priority = 2)
    public void sendPostRequestWithJsonBodyFromFile() {
        log.info("Scenario 2: Send POST request with JSON body from a file");
        // Implementation will be added here
    }

    @Test(priority = 3)
    public void chainTwoApisExtractTokenReuseInNextCall() {
        log.info("Scenario 3: Chain two APIs (extract token, reuse in next call)");
        // Implementation will be added here
    }

    @Test(priority = 4)
    public void validateFieldsInsideJsonResponseUsingJsonPath() {
        log.info("Scenario 4: Validate fields inside a JSON response using JSONPath");
        // Implementation will be added here
    }

    @Test(priority = 5)
    public void uploadFileUsingMultipartApiRequest() {
        log.info("Scenario 5: Upload a file using multipart API request");
        // Implementation will be added here
    }

    @Test(priority = 6)
    public void addHeadersAndQueryParamsDynamicallyToRequest() {
        log.info("Scenario 6: Add headers and query params dynamically to request");
        // Implementation will be added here
    }

    @Test(priority = 7)
    public void validateResponseTimeAndSchemaOfApi() {
        log.info("Scenario 7: Validate response time and schema of an API");
        // Implementation will be added here
    }

    @Test(priority = 8)
    public void createReusableApiUtilityClassForGetPost() {
        log.info("Scenario 8: Create a reusable API utility class for GET/POST");
        // Implementation will be added here
    }

    @Test(priority = 9)
    public void retryFailedApiCallThreeTimesBeforeFailing() {
        log.info("Scenario 9: Retry failed API call 3 times before failing");
        // Implementation will be added here
    }

    @Test(priority = 10)
    public void assertNestedJsonFieldUsingRestAssured() {
        log.info("Scenario 10: Assert a nested JSON field using RestAssured");
        // Implementation will be added here
    }

    @Test(priority = 11)
    public void requestSpecificationVsResponseParsing() {
        log.info("Scenario 11: RequestSpecification vs Response Parsing");
        // Implementation will be added here
    }

    @Test(priority = 12)
    public void responseAsStringVsResponseAsClass() {
        log.info("Scenario 12: Response.asString() vs Response.as(Class)");
        // Implementation will be added here
    }

    @Test(priority = 13)
    public void validateApiWithPathParameters() {
        log.info("Scenario 13: Validate API with Path Parameters");
        // Implementation will be added here
    }

    @Test(priority = 14)
    public void validateApiWithFormParameters() {
        log.info("Scenario 14: Validate API with Form Parameters");
        // Implementation will be added here
    }

    @Test(priority = 15)
    public void handleCookieInRequestAndResponse() {
        log.info("Scenario 15: Handle Cookie in Request and Response");
        // Implementation will be added here
    }

    @Test(priority = 16)
    public void createAndSendPatchRequest() {
        log.info("Scenario 16: Create and send a PATCH request");
        // Implementation will be added here
    }

    @Test(priority = 17)
    public void deleteRequestAndValidateResponse() {
        log.info("Scenario 17: Delete request and validate response");
        // Implementation will be added here
    }

    @Test(priority = 18)
    public void deserializeResponseArrayIntoPojoList() {
        log.info("Scenario 18: Deserialize response array into POJO list");
        // Implementation will be added here
    }

    @Test(priority = 19)
    public void logRequestAndResponseDetails() {
        log.info("Scenario 19: Log request and response details");
        // Implementation will be added here
    }

    @Test(priority = 20)
    public void extractFullResponseAndUseAssertions() {
        log.info("Scenario 20: Extract full response and use assertions");
        // Implementation will be added here
    }

}