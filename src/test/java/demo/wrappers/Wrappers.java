package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
import java.text.NumberFormat;
import java.util.Locale;
import java.text.NumberFormat;
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
     public static boolean printDiscountTitle(WebDriver driver,List<WebElement> element,int discount){
        boolean status=false;
        try{
            
          Map<String,String> map=new HashMap<>();
         for(WebElement ele:element)
         {
            String discountpercentage=ele.getText();
             int discountvalue=Integer.parseInt(ele.getText().replaceAll("[^0-9]","").trim());
             if(discountvalue > discount)
             {
                String title=ele.findElement(By.xpath("./ancestor::a//div[contains(@class, 'KzDlHZ')]")).getText();
                map.put(discountpercentage,title);
                
             }
         }
          for (Map.Entry<String, String> entry : map.entrySet()) {
            
            System.out.println("Iphone Discount percentage is : "+entry.getKey()+" and the tiltle is " +entry.getValue());
             status=true;
            }

        }catch(Exception e)
        {
          e.printStackTrace();
          status=false;
        }
    return status;
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

public static boolean reviewproducts(WebDriver driver,By locator,int n)
    {
        boolean status=false;
        try{
            List<WebElement> reviewlist=driver.findElements(locator);
            HashSet<Integer> setele=new HashSet<>();
            for(WebElement ele:reviewlist)
            {
                int userrview=Integer.parseInt(ele.getText().replaceAll("[^\\d]","").trim());
                setele.add(userrview);

            }
            List<Integer> list=new ArrayList<>(setele);
          Collections.sort(list,Collections.reverseOrder());
          NumberFormat numberformat=NumberFormat.getInstance(Locale.US); 
          System.out.println(list);
          LinkedHashMap<String,String> productdetail=new LinkedHashMap<>();
          for(int i=0;i<5;i++)
          {
             String formatedusercount="(" +numberformat.format(list.get(i)) +")";
             String title=driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"+formatedusercount+"')]/../../a[@class='wjcEIp']")).getText();
             String url=driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"+formatedusercount+"')]/../../a[@class='VJA3rP']")).getAttribute("src");
             String highestreviewcountandtitle=String.valueOf(i+1)+"highest review count is "+formatedusercount+" Title is "+title;
             productdetail.put(highestreviewcountandtitle, url);
          }

          for(Map.Entry<String,String> map:productdetail.entrySet())
          {
            System.out.println(map.getKey()+" and product image"+map.getValue());

          }
         status=true;

        }catch(Exception e)
        {
          e.printStackTrace();
          status=false;
        }
    return status;
}
public static int ratingverify(WebDriver driver,List<WebElement> element,double startrating){ 
    int count =0;
    try{

        for(WebElement ele:element){
          float ratingvalue =Float.parseFloat(ele.getText().replaceAll("[^0-9]","").trim());
          if(ratingvalue<=startrating)
          {
            count++;
          }
          
        }
        System.out.println("Count of washing machine which has start less than or equal to : "+startrating+" : "+count);

    }catch(Exception e)
    {
        e.printStackTrace();
    }
    return count;
}

}


