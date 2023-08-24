package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
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

public class testCase_01  extends DP{
    static WebDriver driver;
    static ExtentReports reports;
    ExtentTest test;
    static String last_generated_username;

    @BeforeTest
    public void createDriver() throws MalformedURLException{
        driver = DriverSingleton.getDriver();
        reports = ReportSingleton.getReport();
    }

    @Test(description="User Onboarding Flow",groups = "Login Flow",priority = 1,dataProvider = "testcase1",dataProviderClass = DP.class)
    public void TestCase01(String username,String password) throws InterruptedException,IOException{
        // driver = DriverSingleton.getDriver();
        test = ReportSingleton.createTest(reports,"User Onboarding Flow");
        SeleniumWrapper wrapper = new SeleniumWrapper(driver);
        SoftAssert sa = new SoftAssert();
        boolean status;
        HomePage home = new HomePage(driver);
        // home.navigateToRegister();
        wrapper.navigateToUrl("https://qtripdynamic-qa-frontend.vercel.app/pages/register");
        RegisterPage register = new RegisterPage(driver);
        status = register.registerUser(username,password,true);
        test.log(LogStatus.PASS,"User Registration");
        sa.assertTrue(status,"Failed to register user");
        last_generated_username = register.last_generated_username;
        LoginPage login = new LoginPage(driver);
        login.performLogin(last_generated_username,password);
        status = home.isUserLogin();
        sa.assertTrue(status,"Failed to Login");
        test.log(LogStatus.PASS, "User Login");
        status = home.logoutUser();
        sa.assertFalse(status,"Failed to Logout");
        test.log(LogStatus.PASS, "User Logout");
        // reports.endTest(test);
        // reports.flush();
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.capture(driver)));
}
    @AfterTest
    public void flush(){
        ReportSingleton.flush(reports);
    }
}
