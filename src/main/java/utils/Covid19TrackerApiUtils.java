package utils;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static config.EndPoints.GET_SUMMARY_SPLIT;
import static io.restassured.RestAssured.given;

public class Covid19TrackerApiUtils {

    ConfigReader config;
    String x_app_token;
    String BASE_URL;
    public Covid19TrackerApiUtils(){
        config = new ConfigReader();
        x_app_token = config.getProperty("token");
        BASE_URL = config.getProperty("base_url");
        System.out.println("token : "+x_app_token);
    }


    public Response findSummarySplitData() {
        System.out.println("token : "+x_app_token);
        Response res = given().contentType(ContentType.JSON).header(new Header("x-app-token", x_app_token)).when()
                .log().all().get(BASE_URL + GET_SUMMARY_SPLIT);
        System.out.println(res.body().asString());

        return res;
    }







}