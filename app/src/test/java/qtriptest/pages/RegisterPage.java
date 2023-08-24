package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.UUID;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {
    WebDriver driver;
    public String last_generated_username;
    @FindBy(xpath="//input[@type='email']")
    WebElement email_TxtBox;
    @FindBy(xpath="//input[@type='password']")
    WebElement pass_TxtBox;
    @FindBy(xpath="//input[@name='confirmpassword']")
    WebElement conf_pass;
    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement reg_Btn;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public Boolean registerUser(String username, String password,boolean makeUserDynamic) throws InterruptedException{
        SeleniumWrapper wrapper = new SeleniumWrapper(driver);
        if (makeUserDynamic){
            username = username+"."+UUID.randomUUID().toString();
        }
        wrapper.sendKeys(email_TxtBox, username);
        wrapper.sendKeys(pass_TxtBox, password);
        wrapper.sendKeys(conf_pass, password);
        wrapper.click(reg_Btn);
        // email_TxtBox.sendKeys(username);
        // pass_TxtBox.sendKeys(password);
        // conf_pass.sendKeys(password);
        // reg_Btn.click();
        this.last_generated_username = username;
        Thread.sleep(5000);
        return this.driver.getCurrentUrl().endsWith("/login");
    }
}
