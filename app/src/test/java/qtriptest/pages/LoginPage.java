package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    WebDriver driver;
    @FindBy(xpath="//input[@name='email']")
    WebElement email_txtBox;
    @FindBy(xpath="//input[@name='password']")
    WebElement pass_txtBox;
    @FindBy(xpath="//button[@type='submit']")
    WebElement login_Btn;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public void performLogin(String username, String password) throws InterruptedException{
        SeleniumWrapper wrapper = new SeleniumWrapper(driver);
        wrapper.sendKeys(email_txtBox, username);
        wrapper.sendKeys(pass_txtBox, password);
        wrapper.click(login_Btn);
        // email_txtBox.sendKeys(username);
        // pass_txtBox.sendKeys(password);
        // login_Btn.click();
        Thread.sleep(2000);
        if(driver.switchTo().alert().getText().equals("You have logged in Succesfully !")){
            driver.switchTo().alert().accept();
        }
        Thread.sleep(5000);
    }
}
