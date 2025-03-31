package TestCases;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Covid19TrackerApiUtils;

import java.util.Map;

@Slf4j
public class FindSafeZoneTestUsingLambdaJava8Plus {
    Covid19TrackerApiUtils utils;
    @BeforeClass
    public void setUp()
    {

        utils = new Covid19TrackerApiUtils();
    }



    @Test
    public void safeZoneTestUsingJava8Plus()
    {

       Response result= utils.findSummarySplitData();
        Assert.assertEquals(result.getStatusCode(),200);
        log.info("response Body :"+result.body().asString());

        JSONObject jobject = new JSONObject(result);
        JSONArray jsonArray = jobject.getJSONArray("data");

        Map<String,Integer> map = jsonArray.toList().stream().map(obj -> new JSONObject((Map<?, ?>) obj)).
                filter(jobj -> jobj.getInt("total_boosters_1")>0 && jobj.getInt("total_boosters_2")>0).
                map(jobj -> {
                    int total_active_cases = jobj.getInt("total_cases")-jobj.getInt("total_fatalities")-jobj.getInt("total_recoveries");
                    return Map.of(jobj.getString("province"),total_active_cases);
                }).filter(map1 -> map1.values().stream().findFirst().get()<5000).limit(2).reduce((map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                }).get();

        map.forEach((key,value) -> log.info("Safe Zone Province : "+key+" With Total Active Cases : "+value));







    }


}
