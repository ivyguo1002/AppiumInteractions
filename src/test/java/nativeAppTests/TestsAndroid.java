package nativeAppTests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import util.BaseTest1;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class TestsAndroid extends BaseTest1 {
    @Test
    public void testConfig(){

    }

    @Test
    public void testXpath(){
        driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
        driver.findElementByXPath("//android.widget.TextView[@index='2']").click();
        driver.findElementById("android:id/checkbox").click();
        driver.findElementsByClassName("android.widget.RelativeLayout").get(1).click();
        driver.findElementByClassName("android.widget.EditText").sendKeys("hello");
        driver.findElementById("android:id/button1").click();
    }

    @Test
    public void testUIAutomator(){
        // driver.findElementByAndroidUIAutomator("attribute("value")")

        //driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
        //driver.findElementByXPath("//android.widget.TextView[@text='App']").click();
        driver.findElementByAndroidUIAutomator("text(\"App\")").click();

        //  Validate clickable feature for all options
        //  driver.findElementsByAndroidUIAutomator("new UiSelector().property(value)");
        System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());
    }

    @Test
    public void testTap(){

        TouchAction t =new TouchAction(driver);
        //move, scroll
        driver.findElementByAndroidUIAutomator
                ("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Views\"));");

        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        //Tap
        AndroidElement expandList=	driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
        t.tap(tapOptions().withElement(element(expandList))).perform();
        driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
        AndroidElement pn=	driver.findElementByXPath("//android.widget.TextView[@text='People Names']");

        //Long Press
        t.longPress(longPressOptions().withElement(element(pn)).withDuration(ofSeconds(2))).release().perform();
        //Thread.sleep(2000);
        System.out.println(driver.findElementById("android:id/alertTitle").isDisplayed());
    }

    @Test
    public void testSwipe(){
        //scroll to Views
        driver.findElementByAndroidUIAutomator
                ("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Views\"));");
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();

        //Click
        driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();
        driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
        driver.findElementByXPath("//*[@content-desc='9']").click();

        //Swipe
        TouchAction t=new TouchAction(driver);
        //long press //on element// 2 sec// move to another element and you release
        AndroidElement first=driver.findElementByXPath("//*[@content-desc='15']");
        AndroidElement second=driver.findElementByXPath("//*[@content-desc='45']");

        t.longPress(longPressOptions().withElement(element(first)).withDuration(ofSeconds(2))).moveTo(element(second)).release().perform();
    }

    @Test
    public void testDragandDrop(){
        //scroll to Views
        driver.findElementByAndroidUIAutomator
                ("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Views\"));");
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();

        //click drag and drop
        driver.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();

        //drag and drop
        AndroidElement source = driver.findElementsByClassName("android.view.View").get(0);
        AndroidElement destination = driver.findElementsByClassName("android.view.View").get(1);

        TouchAction t = new TouchAction(driver);
        t.longPress(element(source)).moveTo(element(destination)).release().perform();
    }
}