package com.example.vrodaselenium;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

@ExtendWith(SeleniumExtension.class)
public class ViewProductTest {

    ChromeDriver driver;
    private final String SCREENSHOTS = "./src/test/onDemandScreenShots";

    public ViewProductTest(ChromeDriver driver){
        this.driver = driver;
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        System.setProperty("sel.jup.screenshot.at.the.end.of.tests", "whenfailure");
        System.setProperty("sel.jup.screenshot.format", "png");
        System.setProperty("sel.jup.output.folder", "./src/test/failureScreenShots");
    }

    public static void createSnapShot(WebDriver webDriver, String fileWithPath) throws Exception{
        TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fileWithPath);
        FileUtils.copyFile(srcFile, destFile);
    }


    @Test
    @DisplayName("test-View-All-Products")
    void shouldViewProducts(TestInfo testInfo) throws Exception{
        driver.get("http://localhost:8080");
        driver.manage().window().maximize();

        String method = testInfo.getDisplayName();
        createSnapShot(driver,SCREENSHOTS + "\\" + method + "_" + System.currentTimeMillis() + ".png");

        WebElement productsLink = driver.findElement(By.xpath("//a[@href='Products.html']"));
        productsLink.click();

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        driver.quit();
    }

}