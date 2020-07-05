package util;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTestIOS2 {
    public IOSDriver<IOSElement> driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        driver=capabilities();
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }

    private IOSDriver<IOSElement> capabilities() throws MalformedURLException {
        IOSDriver<IOSElement> driver;
        DesiredCapabilities dc = new DesiredCapabilities();
        // TODO Auto-generated method stub

        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);

        File app = new File("/Users/xcodeclub/Desktop/UICatalog.app.zip");

        dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return driver;

    }

}

