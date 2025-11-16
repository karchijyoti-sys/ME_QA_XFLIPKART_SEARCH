package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void enterText(WebElement ele,String Text)
     {
        try{
          //  ele.clear();
          ele.sendKeys(Text);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
     }

     public static void click(WebDriver driver,WebElement ele)
     {
        try{
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            ele.click();
            Thread.sleep(2000);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
     }

    public static List<WebElement> getElements(WebDriver driver,By locator)
    {
        List<WebElement>  ele =driver.findElements(locator) ; 
        return ele;

    }
    public static void visibilityOfAllElements(List<WebElement> ele,WebDriver driver)
    {
         WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(ele));
    }  
    
    public static void waitforelement(WebDriver driver,WebElement ele)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }
   public static void handlePopup(WebDriver driver, By locator) {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        popup.click();
        System.out.println("Popup closed.");
    } catch (Exception e) {
        // do nothing – popup didn’t appear
        System.out.println("Popup not present.");
    }
}

}
