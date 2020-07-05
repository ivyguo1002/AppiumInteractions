package webAppTests;

import org.testng.annotations.Test;
import util.BaseTestIOS1;

public class TestsSafari extends BaseTestIOS1 {
    @Test
    public void testGmail(){
        driver.get("http://gmail.com");

        driver.findElementByName("Email").sendKeys("rahul");

        driver.findElementByName("Passwd").sendKeys("rahul");

        driver.findElementByName("signIn").click();
    }

}
