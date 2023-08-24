package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {
    private WebDriver driver;
    private final int max_retry =3;

    public SeleniumWrapper(WebDriver driver){
        this.driver = driver;
    }

    public boolean click(WebElement elem){
        try{
            if(elem!=null){
                elem.click();
                return true;
            }
        }
        catch(StaleElementReferenceException e){   
            if(elem!=null){
                elem.click();
                return true;
            }
        }
        return false;
    }

    public void sendKeys(WebElement elem,String text){
        if(elem!=null){
            elem.clear();
            elem.sendKeys(text);
        }
    }

    public void navigateToUrl(String url){
        if(!driver.getCurrentUrl().equals(null)){
            driver.navigate().to(url);
        }
    }

    public WebElement findElementwithRetry(By locator){
        int retryCount = 0;
        while (retryCount < max_retry) {
            try {
                return driver.findElement(locator);
            } catch (NoSuchElementException e) {
                retryCount++;
            }
        }
        return null;
    }
}
