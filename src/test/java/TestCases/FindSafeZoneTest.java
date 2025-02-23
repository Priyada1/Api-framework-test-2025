package TestCases;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Covid19TrackerApiUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FindSafeZoneTest {

   Covid19TrackerApiUtils utils;
    @BeforeClass
    public void setUp()
    {
        utils = new Covid19TrackerApiUtils();

    }

    @Test
    public void findSafeZone(){

        System.out.println("Test-started");

        Response result = utils.findSummarySplitData();
        Assert.assertEquals(result.getStatusCode(),200);
        System.out.println("result:   "+result.body().asString());

        JSONObject jsonObject = new JSONObject(result.body().asString()); // Will Convert String to JSONObject

        JSONArray jsonArray = jsonObject.getJSONArray("data");
        Map<String, Object> map = new HashMap<>();

        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject job= jsonArray.getJSONObject(i);
            //Map<String,Object> innerMap = new HashMap<>();
            int total_active_cases=0;
            if(job.getInt("total_boosters_1")>0 && job.getInt("total_boosters_2")>0)
            {
                total_active_cases = job.getInt("total_cases")-job.getInt("total_fatalities")-
                        job.getInt("total_recoveries");
                if(total_active_cases<5000 && map.size()<2)
                {
                    map.put(job.get("province").toString(),total_active_cases);
                }

            }

        }


        for(Map.Entry<String,Object> obj2 : map.entrySet())
        {
            System.out.println("Safe Zone Province : "+obj2.getKey()+" With Total Active Cases : "+obj2.getValue());

        }


    }
}
