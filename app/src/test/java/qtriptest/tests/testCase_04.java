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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_04 {
    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;
    static String last_generated_username;

    @BeforeTest
    public void createDriver() throws MalformedURLException{
        driver = testCase_03.driver;
        reports = testCase_03.reports;
    }

    @Test(description = "Verify that Booking history can be viewed",groups = "Reliability Flow",priority = 4,dataProvider = "testcase4",dataProviderClass = DP.class)
    public void TestCase04(String username,String password,String dataset1,String dataset2,String dataset3) throws InterruptedException,IOException{
        // driver = DriverSingleton.getDriver();
        test = ReportSingleton.createTest(reports, "Verify that Booking history can be viewed");
        HomePage home = new HomePage(driver);
        HistoryPage history = new HistoryPage(driver);
        SoftAssert sa = new SoftAssert();
        SeleniumWrapper wrapper = new SeleniumWrapper(driver);
        wrapper.navigateToUrl("https://qtripdynamic-qa-frontend.vercel.app/");
        wrapper.navigateToUrl("https://qtripdynamic-qa-frontend.vercel.app/pages/register");
        // home.navigateToHome();
        // home.navigateToRegister();
        RegisterPage register = new RegisterPage(driver);
        Assert.assertTrue(register.registerUser(username, password, true),"Failed to register");
        test.log(LogStatus.PASS, "User Registration");
        last_generated_username = register.last_generated_username;
        LoginPage login = new LoginPage(driver);
        login.performLogin(last_generated_username, password);
        test.log(LogStatus.PASS, "User login");
        String arr[] = {dataset1,dataset2,dataset3};
        String t_id[]= new String[arr.length];
        String city,adv_name,name,date,count;
        AdventurePage ad = new AdventurePage(driver);
        AdventureDetailsPage adventure = new AdventureDetailsPage(driver);
        for(int i=0;i<arr.length;i++){
            home.navigateToHome();
            String arr1[] = arr[i].split(";");
            city = arr1[0];
            adv_name = arr1[1];
            name = arr1[2];
            date = arr1[3];
            count = arr1[4];
            home.SearchCity(city);
            home.SelectCity();
            ad.searchAdventure(adv_name);
            adventure.bookAdventure(adv_name, name, date, count);
            sa.assertTrue(adventure.isBookingSuccessful(),"Booking Failed");
            test.log(LogStatus.PASS, "Booking Successful" + city);
            t_id[i] = history.getReservations(name);
        }
        for(int i=0;i<t_id.length;i++){
            sa.assertTrue(history.checkReservation(t_id[i]));
            test.log(LogStatus.PASS, "Id is present" + t_id[i]);
        }
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.capture(driver)));
    }
}
