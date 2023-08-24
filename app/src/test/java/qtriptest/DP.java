package qtriptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DP {

    @DataProvider(name="testcase1")
    public static Object[][] getData() throws IOException{
        File filename = new File("src/test/resources/DatasetsforQTrip.xlsx");
        FileInputStream file = new FileInputStream(filename);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("TestCase01");

        int rowcount = sheet.getLastRowNum();
        int colscount = sheet.getRow(0).getLastCellNum();

        Object testdata[][] = new Object[rowcount][colscount-1];

        for(int i=1;i<=rowcount;i++){
            XSSFRow row = sheet.getRow(i);
            for(int j=1;j<colscount;j++){
                XSSFCell cell = row.getCell(j);
                switch(cell.getCellType()){
                    case STRING:testdata[i-1][j-1]=cell.getStringCellValue();
                                break;
                    case NUMERIC:testdata[i-1][j-1]=String.valueOf(cell.getNumericCellValue());
                                break;
                    default:break;
                }
            }
        }
        file.close();
        workbook.close();
        return testdata;
    }

    @DataProvider(name="testcase2")
    public static Object[][] getData1() throws IOException{
        File filename = new File("src/test/resources/DatasetsforQTrip.xlsx");
        FileInputStream file = new FileInputStream(filename);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("TestCase02");

        int rowcount = sheet.getLastRowNum();
        int colscount = sheet.getRow(0).getLastCellNum();

        Object testdata[][] = new Object[rowcount][colscount-1];

        for(int i=1;i<=rowcount;i++){
            XSSFRow row = sheet.getRow(i);
            for(int j=1;j<colscount;j++){
                XSSFCell cell = row.getCell(j);
                switch(cell.getCellType()){
                    case STRING:testdata[i-1][j-1]=cell.getStringCellValue();
                                break;
                    case NUMERIC:testdata[i-1][j-1]=cell.getNumericCellValue();
                                break;
                    default:break;
                }
            }
        }
        workbook.close();
        file.close();
        return testdata;
    }

    @DataProvider(name="testcase3")
    public static Object[][] getData2() throws IOException{
        File filename = new File("src/test/resources/DatasetsforQTrip.xlsx");
        FileInputStream file = new FileInputStream(filename);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("TestCase03");

        int rowcount = sheet.getLastRowNum();
        int colscount = sheet.getRow(0).getLastCellNum();

        Object testdata[][] = new Object[rowcount][colscount-1];
        for(int i=1;i<=rowcount;i++){
            XSSFRow row = sheet.getRow(i);
            for(int j=1;j<colscount;j++){
                XSSFCell cell = row.getCell(j);
                switch(cell.getCellType()){
                    case STRING:testdata[i-1][j-1]=cell.getStringCellValue();
                                break;
                    case NUMERIC:testdata[i-1][j-1]=cell.getNumericCellValue();
                                break;
                    default:break;
                }
            }
        }
        workbook.close();
        file.close();
        return testdata;
    }

    @DataProvider(name="testcase4")
    public static Object[][] getData3() throws IOException{
        File filename = new File("src/test/resources/DatasetsforQTrip.xlsx");
        FileInputStream file = new FileInputStream(filename);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("TestCase04");

        int rowcount = sheet.getLastRowNum();
        int colscount = sheet.getRow(0).getLastCellNum();

        Object testdata[][] = new Object[rowcount][colscount-1];
        // String city,adv_name,name,date,count;
        for(int i=1;i<=rowcount;i++){
            XSSFRow row = sheet.getRow(i);
            for(int j=1;j<colscount;j++){
                XSSFCell cell = row.getCell(j);
                switch(cell.getCellType()){
                    case STRING:testdata[i-1][j-1]=cell.getStringCellValue();
                                break;
                    default:break;
                }
                // String arr[] = test.split(";");
                // city = arr[0];
                // adv_name = arr[1];
                // name = arr[2];
                // date = arr[3];
                // count = arr[4];
            }
        }
        workbook.close();
        file.close();
        return testdata;
    }
}
