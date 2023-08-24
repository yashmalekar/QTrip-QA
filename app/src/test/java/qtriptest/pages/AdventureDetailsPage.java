
package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {
    WebDriver driver;
    SeleniumWrapper wrapper = new SeleniumWrapper(driver);
    @FindBy(className = "text-left")
    WebElement adventure_name;
    @FindBy(name="name")
    WebElement name_txtBox;
    @FindBy(name = "date")
    WebElement date_txtBox;
    @FindBy(name = "person")
    WebElement person_txtBox;
    @FindBy(className = "reserve-button")
    WebElement reserve_Btn;
    @FindBy(className = "alert-success")
    WebElement success_Msg;

    public AdventureDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public void bookAdventure(String adventure,String name,String date,String persons) throws InterruptedException{
        if(adventure_name.getText().toLowerCase().equals(adventure.toLowerCase())){
            wrapper.click(adventure_name);
            // adventure_name.click();
            Thread.sleep(3000);
        }
        wrapper.sendKeys(name_txtBox, name);
        wrapper.sendKeys(date_txtBox, date);
        wrapper.sendKeys(person_txtBox, persons);
        wrapper.click(reserve_Btn);
        // name_txtBox.sendKeys(name);
        // date_txtBox.sendKeys(date);
        // person_txtBox.clear();
        // person_txtBox.sendKeys(persons);
        // reserve_Btn.click();
        Thread.sleep(3000);
    }

    public Boolean isBookingSuccessful(){
        return success_Msg.isDisplayed();
    }
}