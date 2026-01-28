package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadDataFromExcelUtils {
    Object[][] data = null;
    XSSFWorkbook xssfWorkbook = null;
    FileInputStream fis = null;

    public Object[][] readDataFromExcel(String filePath,String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);
        xssfWorkbook = new XSSFWorkbook(fis);
        Sheet sheet = xssfWorkbook.getSheet(sheetName);
        int rowNum=sheet.getLastRowNum();
        int col=sheet.getRow(0).getLastCellNum();
        data = new Object[rowNum][col];
       for(int i=1;i<=rowNum;i++)
       {
           Row row = sheet.getRow(i);
           for(int j=0;j<col;j++)
           {
               Cell cell = row.getCell(j);
               data[i-1][j]=cell;
           }
       }


      return  data;




    }
}