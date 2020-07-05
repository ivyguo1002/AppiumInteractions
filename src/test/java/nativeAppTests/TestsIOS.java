package nativeAppTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import util.BaseTestIOS2;

public class TestsIOS extends BaseTestIOS2 {
    @Test
    public void test(){
        driver.findElementByAccessibilityId("Alert Views").click();
        driver.findElementByXPath("//*[@value='Text Entry']").click();
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("hello");
        driver.findElementByName("OK").click();
        driver.navigate().back();

        //scroll in IOS
        Dimension size = driver.manage().window().getSize();
        int x = size.getWidth() / 2;
        int starty = (int) (size.getHeight() * 0.60);
        int endy = (int) (size.getHeight() * 0.10);
        //driver.swipe(x, starty, x, endy, 2000);

        driver.findElementByAccessibilityId("Steppers").click();
        driver.findElementByAccessibilityId("Increment").click();
        driver.findElementByAccessibilityId("Increment").click();
        System.out.println(driver.findElementsByClassName("XCUIElementTypeStaticText").get(1).getText());

        System.out.println(driver.findElementsByClassName("XCUIElementTypeStaticText").get(2).getText());
        driver.findElementByAccessibilityId("Decrement").click();

        System.out.println(driver.findElementsByClassName("XCUIElementTypeStaticText").get(1).getText());

        //navigate back
        driver.navigate().back();

        driver.findElementByAccessibilityId("Picker View").click();
        driver.findElementByName("Green color component value").sendKeys("220");
        driver.findElementsByClassName("XCUIElementTypePickerWheel").get(0).sendKeys("55");
        driver.findElementByXPath("//*[@name='Blue color component value']").sendKeys("130");

    }

    @Test
    public void switches() {
        //todo
        //driver.scrollTo("Switches").click();
        System.out.println(driver.findElements(By.className("UIASwitch")).get(0).getAttribute("value"));
        driver.findElements(By.className("UIASwitch")).get(0).click();
        System.out.println(driver.findElements(By.className("UIASwitch")).get(0).getAttribute("value"));

    }

    @Test
    public void Pickers() {
        //todo
        //driver.scrollTo("Picker View").click();
        System.out.println(driver.findElements(By.className("UIAPickerWheel")).get(0).getAttribute("value"));
        driver.findElements(By.className("UIAPickerWheel")).get(0).sendKeys("85");
        driver.findElements(By.className("UIAPickerWheel")).get(1).sendKeys("215");
        System.out.println(driver.findElements(By.className("UIAPickerWheel")).get(0).getAttribute("value"));

    }

    @Test
    public void Alerts() {
        //todo
        //how to scroll in ios
        //driver.scrollTo("Alert Views").click();
        driver.findElementByName("Simple").click();
        driver.switchTo().alert().accept();

    }
}
