package webAppTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.BaseTest2;

public class TestsChrome extends BaseTest2 {
    @Test
    public void testConfig(){

    }
    @Test
    public void testNavigation(){
        driver.get("http://facebook.com");
        driver.findElementById("m_login_email").sendKeys("qwerty");
        driver.findElementById("m_login_password").sendKeys("12345");
        driver.findElementByName("login").click();

    }
    @Test
    public void testScroll(){
        driver.get("http://cricbuzz.com");
        driver.findElementByXPath("//a[@href='#menu']").click();
        driver.findElementByCssSelector("a[title='Cricbuzz Home']").click();
        System.out.println(driver.getCurrentUrl());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,480)", "");
        Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Top Stories']")).getAttribute("class").contains("header"));
    }
}
