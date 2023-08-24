package qtriptest.tests;

import qtriptest.DP;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_03 {
    static WebDriver driver;
    static ExtentReports reports;
    ExtentTest test;
    static String last_generated_username;

    @BeforeTest
    public void createDriver() throws MalformedURLException{
        driver = testCase_02.driver;
        reports = testCase_02.reports;
    }

    @Test(description = "Verify that adventure booking and cancellation works fine",groups = "Booking and Cancellation Flow",priority = 3,dataProvider = "testcase3",dataProviderClass = DP.class)
    public void TestCase03(String username,String password,String city,String adventureName,String name, String date,String count) throws InterruptedException,IOException{
        // driver = DriverSingleton.getDriver();
        test = ReportSingleton.createTest(reports, "Verify that adventure booking and cancellation works fine");
        SoftAssert sa = new SoftAssert();
        HomePage home = new HomePage(driver);
        SeleniumWrapper wrapper = new SeleniumWrapper(driver);
        wrapper.navigateToUrl("https://qtripdynamic-qa-frontend.vercel.app/pages/register");
        // home.navigateToRegister();
        RegisterPage register = new RegisterPage(driver);
        Assert.assertTrue(register.registerUser(username, password, true),"Registration failed");
        test.log(LogStatus.PASS, "User Registration");
        last_generated_username = register.last_generated_username;
        LoginPage login = new LoginPage(driver);
        login.performLogin(last_generated_username, password);
        test.log(LogStatus.PASS, "User Login");
        home.SearchCity(city);
        home.SelectCity();
        AdventurePage ad = new AdventurePage(driver);
        ad.searchAdventure(adventureName);
        AdventureDetailsPage adventure = new AdventureDetailsPage(driver);
        adventure.bookAdventure(adventureName, name, date, count);
        sa.assertTrue(adventure.isBookingSuccessful(),"Booking Failed");
        test.log(LogStatus.PASS, "Adventure booking successful");
        HistoryPage history = new HistoryPage(driver);
        String t_id = history.getReservations(name);
        history.cancelReservations(name);
        driver.navigate().refresh();
        sa.assertFalse(history.checkReservation(t_id),"Reservation still there");
        test.log(LogStatus.PASS, "Reservation cancellation");
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.capture(driver)));
    }

    @AfterTest
    public void endTest(){
        ReportSingleton.flush(reports);
    }
}
