package hybridAppTests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.BaseTest3;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class Tests extends BaseTest3 {
    @Test
    public void testConfig(){

    }
    //Fill the form and shopping page should open
    @Test
    public void testHomePage(){
        driver.findElementByClassName("android.widget.Spinner").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))");
        driver.findElementByXPath("//android.widget.TextView[@text='Australia']").click();
        driver.findElementByClassName("android.widget.EditText").sendKeys("test");
        driver.findElementsByClassName("android.widget.RadioButton").get(1).click();
        //cannot get login button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    //Scroll to item and Add item to cart and verify "added to cart" and "shopping cart number"
    @Test
    public void testShoppingPage(){
        //need manimulate to the shopping page first
        driver.findElementByClassName("android.widget.Spinner").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))");
        driver.findElementByXPath("//android.widget.TextView[@text='Australia']").click();
        driver.findElementByClassName("android.widget.EditText").sendKeys("test");
        driver.findElementsByClassName("android.widget.RadioButton").get(1).click();
        //need manimulate to the shopping page first
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Converse All Star\"))");
        driver.findElementByXPath("//android.widget.TextView[@text='Converse All Star']/following::android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']").click();

        String text = driver.findElementByXPath("//android.widget.TextView[@text='Converse All Star']/following::android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']").getText();
        Assert.assertEquals(text, "ADDED TO CART");
        String cartItemNumber = driver.findElementById("com.androidsample.generalstore:id/counterText").getAttribute("text");
        Assert.assertEquals("1", cartItemNumber);


    }

    @Test
    public void testCheckoutPage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");

        driver.hideKeyboard();

        driver.findElement(By.xpath("//*[@text='Female']")).click();

        driver.findElement(By.id("android:id/text1")).click();

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

        driver.findElement(By.xpath("//*[@text='Argentina']")).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        Thread.sleep(4000);

        int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();

        double sum=0;

        for(int i=0;i<count;i++)

        {

            String amount1= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();

            double amount=getAmount(amount1);

            sum=sum+amount;//280.97+116.97

        }

        System.out.println(sum+"sum of products");



        String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();



        total= total.substring(1);

        double totalValue=Double.parseDouble(total);

        System.out.println(totalValue+"Total value of products");

        Assert.assertEquals(sum, totalValue);

//Mobile Gestures

        WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));

        TouchAction t=new TouchAction(driver);

        t.tap(tapOptions().withElement(element(checkbox))).perform();

        WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));

        t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

        driver.findElement(By.id("android:id/button1")).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
    }

    public static double getAmount(String value)

    {

        value= value.substring(1);

        double amount2value=Double.parseDouble(value);

        return amount2value;

    }

    @Test
    public void testContext() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");

        driver.hideKeyboard();

        driver.findElement(By.xpath("//*[@text='Female']")).click();

        driver.findElement(By.id("android:id/text1")).click();

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

        //   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));

        driver.findElement(By.xpath("//*[@text='Argentina']")).click();

        //driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();



        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        Thread.sleep(4000);

//Mobile Gestures

        WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));

        TouchAction t=new TouchAction(driver);

        t.tap(tapOptions().withElement(element(checkbox))).perform();


        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();


        Thread.sleep(7000);

        Set<String> contexts=driver.getContextHandles();

        for(String contextName :contexts)

        {

            System.out.println(contextName);

        }

        driver.context("WEBVIEW_com.androidsample.generalstore");

        driver.findElement(By.name("q")).sendKeys("hello");

        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        driver.context("NATIVE_APP");
    }


}
