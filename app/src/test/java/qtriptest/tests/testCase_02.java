package qtriptest.tests;

import qtriptest.DP;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_02 {
    static WebDriver driver;
    static ExtentReports reports;
    ExtentTest test;

    @BeforeTest
    public void createDriver() throws MalformedURLException{
        driver = testCase_01.driver;
        reports = testCase_01.reports;
        // reports = ReportSingleton.getReport();
    }

    @Test(description = "Verify that Search & Filters work fine",groups = "Search and Filter flow",priority = 2,dataProvider = "testcase2",dataProviderClass = DP.class)
    public void TestCase02(String city, String category, String duration, String expected_filcount,String unexpected_filcount) throws InterruptedException,IOException{
        // driver = DriverSingleton.getDriver();
        test = ReportSingleton.createTest(reports, "Verify that Search & Filters work fine");
        HomePage home = new HomePage(driver);
        AdventurePage adventure = new AdventurePage(driver);
        SeleniumWrapper wrapper = new SeleniumWrapper(driver);
        SoftAssert sa = new SoftAssert();
        wrapper.navigateToUrl("https://qtripdynamic-qa-frontend.vercel.app/");
        // home.navigateToHome();
        home.SearchCity("Dombivli");
        sa.assertFalse(home.AssertAutoCompleteText("Dombivli"));
        test.log(LogStatus.PASS, "Invalid city");
        home.SearchCity(city);
        sa.assertTrue(home.AssertAutoCompleteText(city));
        home.SelectCity();
        test.log(LogStatus.PASS, "Valid city");
        adventure.setFilterValue(duration);
        adventure.setCategoryValue(category);
        sa.assertTrue(adventure.getResultCount(Integer.valueOf(expected_filcount)));
        test.log(LogStatus.PASS, "Filtered result count");
        adventure.clearFilters();
        sa.assertTrue(adventure.getResultCount(Integer.valueOf(unexpected_filcount)));
        test.log(LogStatus.PASS, "Filter removed result count");
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.capture(driver)));
    }

    @AfterTest
    public void endTest(){
        ReportSingleton.flush(reports);
    }
}
