package TestCases;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UploadFileTest {
    //private final Properties prop;
    String url;
    File file;
    @BeforeClass
    public void setUp() throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(fileReader);

        url= (String) prop.get("upload_file_api_url");
        file = new File("src/main/resources/File-upload-doc.pdf");

    }


    @Test
    public void uploadData(){
      System.out.println("URL LINK : "+url);

        Response res = given().multiPart("file",file).when().post(url+"post");
        Assert.assertEquals(res.getStatusCode(),200);
        System.out.println(res.body().asString());
        Gson gson = new Gson();
        JsonObject jo = gson.fromJson(res.body().asString(),JsonObject.class);
        Assert.assertEquals(jo.get("url").getAsString(),url+"post");

    }
}