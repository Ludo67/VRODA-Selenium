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
public class CreateProductTest {


    ChromeDriver driver;
    private final String SCREENSHOTS = "./src/test/onDemandScreenShots";

    public CreateProductTest(ChromeDriver driver){
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
    @DisplayName("test-Create-A-Product")
    void shouldCreateAProduct(TestInfo testInfo) throws Exception {
        driver.get("http://localhost:4200/addProduct");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\'category_id\']")).sendKeys("1234");
        driver.findElement(By.xpath("//*[@id=\'title\']")).sendKeys("Joe Biden's OnlyFan");
        driver.findElement(By.xpath("//*[@id=\'price\']")).sendKeys("1000000");
        driver.findElement(By.xpath("//*[@id=\'quantity\']")).sendKeys("2");
        driver.findElement(By.xpath("//*[@id=\'description\']")).sendKeys("A very nice picture of Joe Biden.");



        String method = testInfo.getDisplayName();
        createSnapShot(driver, SCREENSHOTS + "\\" + method + "_" + System.currentTimeMillis() + ".png");

        driver.findElement(By.id("submit")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();




    }
}
