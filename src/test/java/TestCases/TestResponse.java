package TestCases;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Covid19TrackerApiUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TestResponse {

    Covid19TrackerApiUtils utils;
    @BeforeClass
    public void setUp()
    {
        utils = new Covid19TrackerApiUtils();

    }

    @Test
    public void findSafeZone() {

        System.out.println("Test-started");

        Response result = utils.findSummarySplitData();

        System.out.println(result.body().asString());

        Gson gson = new Gson();
        JsonObject jo=gson.fromJson(result.body().asString(), JsonObject.class);

        JsonArray ja= jo.get("data").getAsJsonArray();

        ja.forEach(jsonElement -> System.out.println(jsonElement.getAsJsonObject().get("province")));


    }


    @Test
    public void jsonResponseTest() throws FileNotFoundException {
/*
Use toJson() when you want to convert a Java object directly to a JSON string.
Use toJsonTree() when you need to manipulate the JSON structure before getting the final JSON string.
 */
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("src/main/resources/testJsonResponse.json");
          //  String res = gson.fromJson(fileReader, String.class);


            JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);

            System.out.println(jsonObject.get("obs"));

            JsonArray jsonArray = jsonObject.getAsJsonArray("obs");
            jsonArray.forEach(jsonElement -> System.out.println(jsonElement.getAsJsonObject().get("speciesCode")));
            jsonArray.forEach(jsonElement -> System.out.println(jsonElement.getAsJsonObject().get("obsAux")));
           // JsonArray jsonArray1=jsonArray.forEach(jsonElement -> jsonElement.getAsJsonObject().get("obsAux").getAsJsonArray());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void wissenInterviewTest(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("user_name","abcd");
        map.put("password","abcd124557");

        Gson gson = new Gson();
        String body= gson.toJson(map.toString()); //Use toJson() when you want to convert a Java object directly to a JSON string.



        Response res = given().contentType("application/json").body(body).post("http://auth-token/api");

        //String token = res.jsonPath().get("token");

        JsonObject resObject = gson.fromJson(res.body().asString(),JsonObject.class);

        String token = String.valueOf(resObject.get("token"));

        Response  res2 = given().contentType("application/json").header(new Header("token",token)).get("httops://get-api/1");

    }

    @Test
    public void testPostRequestWithJsonTree() {
        // 1. Create a JsonObject for the payload
        JsonObject payload = new JsonObject();
        payload.addProperty("name", "John Doe");
        payload.addProperty("age", 30);

        // 2. Convert the JsonObject to a JsonElement using toJsonTree()
        Gson gson = new Gson();
        JsonElement payloadJsonElement = gson.toJsonTree(payload);

        // 3. Manipulate the JsonElement (add a new field)
        JsonObject payloadJsonObject = payloadJsonElement.getAsJsonObject(); //important step
        payloadJsonObject.addProperty("city", "New York");

        // 4. Convert the modified JsonElement back to a JsonObject.  Not strictly necessary, but shows the flow
        //JsonObject modifiedPayload = payloadJsonElement.getAsJsonObject();

        // 5. Set the base URI
        Response response = given().contentType("application/json").header(new Header("token","12334444")).body(payloadJsonObject.toString()).when()
                .post("http://httpbin.org"+"/post");

        // 9. Print the response
        response.prettyPrint();

        // 10. Assert the response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        JsonObject jsonObjectRes = gson.fromJson(response.body().asString(),JsonObject.class); //deserialization
        JsonObject json = jsonObjectRes.get("json").getAsJsonObject();
        Assert.assertEquals(json.get("city").getAsString(),"New York", "City should be New York");


        // 11.  Example Assertion:  Verify the city in the response.
        Assert.assertEquals(response.jsonPath().getString("json.city"), "New York", "City should be New York");
        Assert.assertEquals(response.jsonPath().getString("json.name"), "John Doe", "Name should be John Doe");
        Assert.assertEquals(response.jsonPath().getInt("json.age"), 30, "Age should be 30");
    }


}