package util;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest3 {
    public AndroidDriver<AndroidElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        Properties props = System.getProperties();
        try {
            props.load(new FileInputStream(new File("resources/test.properties")));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        String device = props.getProperty("device");
        driver = capabilities(device);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public static AndroidDriver<AndroidElement> capabilities(String device) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // TODO Auto-generated method stub
        if (device.equals("emulator")) {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "demo2");
        } else {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        }
        File appDir = new File("resources");
        File app = new File(appDir, "Store.apk");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }
}

