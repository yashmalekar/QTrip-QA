package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register";
    @FindBy(xpath="//div[contains(text(),'Logout')]")
    WebElement logout_Btn;
    @FindBy(xpath="//a[contains(text(),'Register')]")
    WebElement reg_Btn;
    @FindBy(id="autocomplete")
    WebElement search_txtBox;
    @FindBy(id="results")
    WebElement city_txt;

    SeleniumWrapper wrapper = new SeleniumWrapper(driver);
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public void navigateToHome(){
        if(!driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/")){
            driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        }
    }

    public void navigateToRegister(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(url);
        }
    }

    public Boolean isUserLogin(){
        return logout_Btn.getText().equals("Logout");
    }

    public Boolean logoutUser() throws InterruptedException{
        wrapper.click(logout_Btn);
        // logout_Btn.click();
        Thread.sleep(3000);
        return reg_Btn.getText().equals("Register");
    }

    public void SearchCity(String city) throws InterruptedException{
        Thread.sleep(2000);
        wrapper.sendKeys(search_txtBox, city);
        // search_txtBox.clear();
        // search_txtBox.sendKeys(city);
        Thread.sleep(1000);
    }

    public Boolean AssertAutoCompleteText(String city){
        if(city_txt.getText().toLowerCase().equals(city.toLowerCase())){
            return true;
        }
        else{
            return false;
        }
    }

    public void SelectCity() throws InterruptedException{
        wrapper.click(city_txt);
        // city_txt.click();
        Thread.sleep(5000);
    }
}
