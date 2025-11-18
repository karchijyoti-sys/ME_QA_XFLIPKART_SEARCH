package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static demo.wrappers.Wrappers.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.testng.Assert;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
     @Test(enabled=false)
    public void testCase01()
    {
        driver.get("https://www.flipkart.com/");
        // WebElement close=driver.findElement(By.cssSelector("._30XB9F"));
        // click(driver,close);
        try{
            Thread.sleep(2000);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        WebElement searchinputtext=driver.findElement(By.name("q"));
        enterText(searchinputtext,"Washing Machine");
        int starrating=4;
        WebElement searchbutton=driver.findElement(By.xpath("//button[@type='submit']"));
        click(driver,searchbutton);
        WebElement popularity=driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
        click(driver,popularity);
        System.out.println(driver.getCurrentUrl());
        String url=driver.getCurrentUrl();
        if(url.contains("popularity")){
        List<WebElement> listofrating=driver.findElements(By.xpath("//div[@class='_5OesEi']//div[text()<='"+starrating+"']"));
         int ratingcount=ratingverify(driver,listofrating,starrating);
         Assert.assertEquals(ratingcount,listofrating.size());
        }  

}
   @Test(enabled=false)
   public void testCase02(){
    int discount=10;
       driver.get("https://www.flipkart.com/");
    //    WebElement close=driver.findElement(By.cssSelector("._30XB9F"));
    //    waitforelement(driver,close);
    //   click(driver,close);
     handlePopup(driver,By.cssSelector("._30XB9F"));
       WebElement searchinputtext=driver.findElement(By.name("q"));
       
        enterText(searchinputtext,"iPhone"); 
        WebElement searchbutton=driver.findElement(By.xpath("//button[@type='submit']"));
        click(driver,searchbutton);
       

        List<WebElement> parentCart=driver.findElements(By.xpath("//div[contains(@class, 'tUxRFH')]//div[contains(@class, 'UkUFwK')] //span[number(translate(substring-before(., ' '), '%', '')) >= '"+discount+"']"));
       //  List<WebElement> parentCard=driver.findElements(By.xpath("//div[contains(@class, 'tUxRFH')]//div[contains(@class, 'UkUFwK')] //span[number(translate(substring-before(., ' '), '%', '')) >= 15]"));
        // Map<String,String> map=new HashSet<>();
        boolean status=printDiscountTitle(driver,parentCart,discount);
        Assert.assertTrue(status,"Test case failed");
        
        

 }
   @Test(enabled=true)
   public void testCase03()
   {
     driver.get("https://www.flipkart.com/");
    //    WebElement close=driver.findElement(By.cssSelector("._30XB9F"));
    //    waitforelement(driver,close);
    //    click(driver,close);
       handlePopup(driver,By.cssSelector("._30XB9F"));
       WebElement searchinputtext=driver.findElement(By.name("q"));
        enterText(searchinputtext,"Coffee Mug"); 
        WebElement searchbutton=driver.findElement(By.xpath("//button[@type='submit']"));
        click(driver,searchbutton);
         try{
         Thread.sleep(2000);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
         
         
            
          By locator=By.cssSelector(".Wphh3N");
          reviewproducts(driver,locator,5);
         
       

   }
     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

   

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}