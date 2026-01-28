package TestCases;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ReqresApiUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestResponseExtraction {

    ReqresApiUtils utils = new ReqresApiUtils();
    Gson gson = new Gson();

    @Test
    @SuppressWarnings("unchecked")
    public void testDeserialization() {

        Response res = utils.findListUsers();
        Assert.assertEquals(res.getStatusCode(), 200, "Status mismatched");
        Assert.assertTrue(res.getTime()<2000);
        log.info("response : " + res.body().asString());
        System.out.println("Response ::" + res.body().asString());

        Assert.assertEquals(res.jsonPath().getInt("page"), 2);

        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> mapRes = gson.fromJson(res.body().asString(), type);
        List<Map<String, Object>> list = (List<Map<String, Object>>) mapRes.get("data");

        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap.get("id"));
            Assert.assertNotNull(stringObjectMap.get("id"));
        }

        System.out.println("print ids using java 8+ ");
        List<Integer> ids = list.stream().
                map(obj -> (new JSONObject((java.util.Map<?, ?>) obj)).
                        getInt("id")).toList();
        for (int i : ids) {
            System.out.print(i + "    ");
        }

        System.out.println("print Email using java *+");
        List<Map<String, Object>> list2 = list.stream().filter(obj -> obj instanceof Map)
                .map(obj -> (Map<String, Object>) obj).toList();

        int i = 0;
        int size = list2.size();
        while (i < size) {
            System.out.println(list2.get(i).get("email"));
            System.out.println("first-name: " + list2.get(i).get("first_name"));
            System.out.println("id: " + list2.get(i).get("id"));
            i++;
        }

        System.out.println("printing email Using Java8+ feature ");
        list2.forEach(map -> {System.out.println(map.get("email"));
                              System.out.println(map.get("first_name"));
        });

    }
}