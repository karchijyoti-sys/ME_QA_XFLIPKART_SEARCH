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
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
     @Test
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
        WebElement searchbutton=driver.findElement(By.xpath("//button[@type='submit']"));
        click(driver,searchbutton);
        WebElement popularity=driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
        click(driver,popularity);
        System.out.println(driver.getCurrentUrl());
        String url=driver.getCurrentUrl();
         int count=0;
         try{
         Thread.sleep(2000);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        if(url.contains("popularity")){
        List<WebElement> listofrating=getElements(driver,By.cssSelector("._5OesEi div"));
       
         visibilityOfAllElements(listofrating,driver);
         List<Double> listofvalues=new ArrayList<>();
        for(WebElement res:listofrating)
        {
            String str=res.getText().trim();
            listofvalues.add(Double.valueOf(str));
        }
        System.out.println(listofvalues.size()  +"----"+listofvalues);
        for(Double d:listofvalues)
        {
            if(d<=4 )
            {
                count++;
            }
        }
        
        System.out.println("cout of stasrtas:----"+count);
        }
       

    }
   @Test
   public void testCase02(){
       driver.get("https://www.flipkart.com/");
    //    WebElement close=driver.findElement(By.cssSelector("._30XB9F"));
    //    waitforelement(driver,close);
    //   click(driver,close);
     handlePopup(driver,By.cssSelector("._30XB9F"));
       WebElement searchinputtext=driver.findElement(By.name("q"));
       
        enterText(searchinputtext,"iPhone"); 
        WebElement searchbutton=driver.findElement(By.xpath("//button[@type='submit']"));
        click(driver,searchbutton);
         try{
         Thread.sleep(2000);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
         List<WebElement> parentCard=driver.findElements(By.cssSelector(".tUxRFH"));
        // List<WebElement> titlelist=parentCard.findElements(By.cssSelector(".KzDlHZ"));
        // List<WebElement> discount=parentCard.findElements(By.cssSelector(".Wphh3N"));
        List<Double> percen=new ArrayList<>();
        
        for(WebElement ele:parentCard){
            String cardTitle=ele.findElement(By.cssSelector(".KzDlHZ")).getText();
            List<WebElement> discountsOfProducts=ele.findElements(By.cssSelector(".UkUFwK"));
            if(discountsOfProducts.isEmpty())
            {
               continue;
            }
            String Discounttext=discountsOfProducts.get(0).getText().trim();
            System.out.println("List of discount "+Discounttext);
            double discount=Double.valueOf(Discounttext.replace("% off", "").trim());
            if(discount > 17)
            {
                System.out.println(cardTitle+"---"+ discount +"% off");
            }else{
                System.out.println(cardTitle+"---"+ discount +"% off" +" is less than 17");
            }
            
            
        
        }
        



   }
   @Test
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
         List<WebElement> parentCard=driver.findElements(By.cssSelector(".slAVV4"));
         List<Integer> review=new ArrayList<>();
         for(WebElement card:parentCard){
            
          List<WebElement> reviewele=card.findElements(By.cssSelector(".Wphh3N"));
          if(!reviewele.isEmpty()){
            String str=reviewele.get(0).getText().replaceAll("[^0-9]", "").trim();
            //System.out.println(reviewele.get(0).getText());
            review.add(Integer.parseInt(str));
           }
         }
        // List<Integer> review=new ArrayList<>();
         System.out.println(parentCard.size()+" ----"+review.size());
         Collections.sort(review,Collections.reverseOrder());
        // System.out.println(review);

        for(int i=0;i<5;i++)
        {
            System.out.println(review.get(i));
            String title=driver.findElement(By.xpath("//div[contains(@class,'slAVV4')]//span[@class='Wphh3N'       and translate(text(), ',()','')='"+review.get(i)+"']/parent::div//preceding-sibling::a[@class='wjcEIp']")).getText();
            WebElement imageurlele=driver.findElement(By.xpath("//div[contains(@class,'slAVV4')]//span[@class='Wphh3N'       and translate(text(), ',()','')='"+review.get(i)+"']/parent::div/parent::div//img[@class='DByuf4']"));
            String imageurl=imageurlele.getAttribute("src");
            System.out.println("Title and Image url of "+i+" Element is "+title+" And imageurl is "+imageurl);
        }


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