
package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
    WebDriver driver;
    // @FindBy(xpath = "//a[text()='Reservations']")
    @FindBy(xpath = "//*[@id='navbarNavDropdown']/ul/li[2]/a")
    WebElement reservations_btn;
    @FindAll({
        @FindBy(xpath = "//*[@id='reservation-table']/tr[1]/th")
    })
    List<WebElement> transaction_id;
    SeleniumWrapper wrapper = new SeleniumWrapper(driver);

    public HistoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public String getReservations(String name) throws InterruptedException{
        wrapper.click(reservations_btn);
        // reservations_btn.click();
        Thread.sleep(5000);
        String t_id="";
        for(WebElement id:transaction_id){
            String text = id.findElement(By.xpath("./following::td[1]")).getText().toLowerCase();
            if(name.toLowerCase().equals(text)){
                t_id = id.getText();
            }
        }
        return t_id;
    }

    public Boolean checkReservation(String t_id){
        String pageSource = driver.getPageSource();
        if(pageSource.contains(t_id)){
            return true;
        }  
        else{
            return false;
        }
    }

    public void cancelReservations(String name) throws InterruptedException{
        // List<WebElement> transaction_id = driver.findElements(By.xpath("//tbody[@id='reservation-table']//th"));
        for(WebElement id:transaction_id){
            String text = id.findElement(By.xpath("./following::td[1]")).getText().toLowerCase();
            if(name.toLowerCase().equals(text)){
                id.findElement(By.xpath("./following::td//button")).click();
                Thread.sleep(3000);
            }
        }
    }
}