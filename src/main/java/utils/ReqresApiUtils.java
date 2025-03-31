package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReqresApiUtils {

ConfigReader config = new ConfigReader();
String url = "/api/users?page=2";
final String url1 = config.getProperty("reqres_base_url") + url;

    public Response findListUsers()
    {
        Response res = given().contentType(ContentType.JSON).log().all().when()
                .get(url1);
        return res;

    }
}
