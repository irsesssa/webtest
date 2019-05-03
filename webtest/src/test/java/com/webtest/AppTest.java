package com.webtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class AppTest {

    @Test
    public void LaunchChrome_Method1()throws Exception  {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Drivers\\chromedriver.exe");

        WebDriver driver=new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

        String url = PropertyReader.getUrl("googleUrl");
        driver.get(url);
        searchByWord(driver);
    }

    private void searchByWord(WebDriver driver) throws Exception {
        WebElement searchInput = driver.findElement
                (By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input"));
        searchInput.sendKeys("Flower");

        try {
            WebElement searchButton = driver.findElement
                    (By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[3]/center/input[1]"));
            searchButton.click();
        } catch (Exception e) {
            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[2]/div[2]/div/center/input[1]"));
            searchButton.click();
        }

        List<WebElement> listOfSites = driver.findElements(By.className("g"));

        List<String> resultLinks = new ArrayList<String>();
        Map<String,String> urlAndTitle =new HashMap<String, String>();

        for (WebElement element : listOfSites) {
            String url = element.findElement(By.tagName("a")).getAttribute("href");
            resultLinks.add(url);
        }


        for (String url : resultLinks) {
            System.out.println(url);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
            driver.navigate().to(url);
            urlAndTitle.put(driver.getTitle(),url);
        }
        driver.close();

        new webtest.WorkBookCreater().doSomething(urlAndTitle);
    }

}

