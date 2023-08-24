package qtriptest;

import java.io.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ReportSingleton {

    private static ExtentReports reports;
    private static ExtentTest test;

    private ReportSingleton(){
    }

    public static ExtentReports getReport(){    
        reports = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports.html");
        reports.loadConfig(new File(System.getProperty("user.dir")+"/extent_customization_configs.xml"));
        return reports;
    }

    public static String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(System.getProperty("user.dir")+"/QTrip_Images/" + System.currentTimeMillis()
        + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
        }

    public static ExtentTest createTest(ExtentReports reports,String testName){
        test = reports.startTest(testName);
        return test;
    }

    public static void flush(ExtentReports reports){
        reports.endTest(test);
        reports.flush();
    }
}