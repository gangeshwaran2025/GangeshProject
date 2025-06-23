package org.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

      

        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("iphone", Keys.ENTER);

        Thread.sleep(3000); 
        
        int Count = 0;

        while (true) {
            List<WebElement> products = driver.findElements(By.xpath("//div[@data-id]"));
            Count+= products.size();
            
            List<WebElement> next = driver.findElements(By.xpath("//span[text()='Next']"));
            
            if (next.isEmpty()) {
                break; 
            }
            WebElement next1 = next.get(0);
            String classValue = next1.getAttribute("class");
            if (classValue.contains("disabled")) {
                break; // Last page reached
            }
            next1.click();
            Thread.sleep(3000);


        }
        System.out.println(" Total iPhone products: " +Count);
        System.out.println("gambo");


    }
}

        