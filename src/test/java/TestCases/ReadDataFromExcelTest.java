package TestCases;

import org.testng.annotations.Test;
import utils.ReadDataFromExcelUtils;

import java.io.IOException;

public class ReadDataFromExcelTest {
    String filePath = "/Users/chakrapanipriyadarshi/Desktop/Api-framework-test-2025/Api-framework-test-2025/src/main/resources/TestData1.xlsx";
    String sheetName = "";
    ReadDataFromExcelUtils utils = new ReadDataFromExcelUtils();
    @Test
    public void testReadDataFromExcel() throws IOException {
        sheetName = "Team";
        Object[][] data= utils.readDataFromExcel(filePath,sheetName);

        System.out.println("Taking data from excel without data provider: "+data[0][0]+" | "+data[0][1]+" | "+data[0][2]+" | "+data[0][3]);

        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[i].length;j++)
            {
                System.out.print(data[i][j]+" ||| ");
            }
        }

        for (Object[] datum : data) {
            for (Object o : datum) {
                System.out.println(o + " | ");
            }
        }



    }

}