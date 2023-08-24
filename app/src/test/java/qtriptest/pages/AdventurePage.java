package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventurePage {
    WebDriver driver;
    SeleniumWrapper wrapper = new SeleniumWrapper(driver);
    @FindBy(id="duration-select")
    WebElement filter_btn;
    @FindBy(id="category-select")
    WebElement category_btn;
    @FindBy(id="search-adventures")
    WebElement adventure_txtBox;

    public AdventurePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public void setFilterValue(String duration){
        wrapper.click(filter_btn);
        // filter_btn.click();
        List<WebElement> fil_opt = driver.findElements(By.xpath("//*[@id='duration-select']/option"));
        for(WebElement option:fil_opt){
            if(option.getText().toLowerCase().equals(duration.toLowerCase()))
            {
                wrapper.click(option);
                // option.click();
            }
        }
    }
    
    public void searchAdventure(String adventure) throws InterruptedException{
        wrapper.sendKeys(adventure_txtBox, adventure);
        // adventure_txtBox.sendKeys(adventure);
        Thread.sleep(1000);
    }

    public Boolean getResultCount(int count){
        List<WebElement> data = driver.findElements(By.xpath("//div[@class='col-6 col-lg-3 mb-4']"));
        if(data.size()==count){
            return true;
        }
        else{
            return false;
        }
    }

    public void setCategoryValue(String category){
        wrapper.click(category_btn);
        // category_btn.click();
        List<WebElement> cat_opt = driver.findElements(By.xpath("//*[@id='category-select']/option"));
        for(WebElement option:cat_opt){
            if(option.getText().toLowerCase().equals(category.toLowerCase())){
                wrapper.click(option);
                // option.click();
            }
        }
    }
    public void clearFilters() throws InterruptedException{
        List<WebElement> clear_btn = driver.findElements(By.className("ms-3"));
        for(WebElement clear:clear_btn){
            wrapper.click(clear);
            // clear.click();
        }
        Thread.sleep(2000);
    }
}