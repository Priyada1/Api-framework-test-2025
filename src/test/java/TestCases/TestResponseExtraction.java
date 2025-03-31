package TestCases;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ReqresApiUtils;

import java.util.List;
import java.util.Map;

@Slf4j
public class TestResponseExtraction {

    ReqresApiUtils utils = new ReqresApiUtils();

    @Test
    public void test()
    {

        Response res = utils.findListUsers();
        Assert.assertEquals(res.getStatusCode(),200);
        log.info("response : "+res.body().asString());
        System.out.println("Response ::"+res.body().asString());

        Assert.assertEquals(res.jsonPath().getInt("page"),2);

        JSONObject jobj = new JSONObject(res.body().asString());
        JSONArray jsonArray = jobj.getJSONArray("data");

        for(int i=0;i< jsonArray.length();i++)
        {
            System.out.println(jsonArray.getJSONObject(i).get("id"));
            Assert.assertNotNull(jsonArray.getJSONObject(i).get("id"));
        }

        System.out.println("print ids using java 8+ ");
        List<Integer> ids = jsonArray.toList().stream().
                map(obj -> ((JSONObject) new JSONObject((java.util.Map<?,?>) obj)).
                getInt("id")).toList();

        for(int i:ids)
        {
            System.out.print(i+"    ");
        }


        System.out.println("print Email using java *+");





    }
}
