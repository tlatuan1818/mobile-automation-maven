package testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import base.BaseSetup;
import pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseSetup {

     private AppiumDriver<MobileElement> appiumDriver;
     public LoginPage loginPage;

     @BeforeClass
     public void setUp() {
          appiumDriver = getDriver();
     }

     @Test()
     public void Login() throws Exception {
          loginPage = new LoginPage(appiumDriver);
          loginPage.Login("tramleanh.tuan","1234");
          Thread.sleep(1000);
          //System.out.print(loginPage.getErrorMessage());
     }
}
