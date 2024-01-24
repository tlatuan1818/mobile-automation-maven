package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.Logs.Log;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseSetup {
    private static AppiumDriver<MobileElement> appiumDriver;
    @Parameters({"appURL"})
    @BeforeClass
    public void initializeTestBaseSetup(String appURL) {
        try {
            // Khởi tạo driver
            setAppiumDriver(appURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }
    public static AppiumDriver<MobileElement> getDriver() {
        return appiumDriver;
    }
    private void setAppiumDriver(String appURL) {
        appiumDriver = getAppiumDriver(appURL);
    }
    private static AppiumDriver<MobileElement> getAppiumDriver(String appURL){
       AppiumDriver<MobileElement> appiumDriver = null;

        DesiredCapabilities desiredCapabilities = getDesiredCapabilities();
        Log.error("Appium Server URL: " + appURL);
        Log.error("Desired Capabilities: " + desiredCapabilities.toString());
        URL appiumServer;
        try {
            appiumServer = new URL(appURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        appiumDriver = new AppiumDriver<>(appiumServer,desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

        return appiumDriver;
    }
   
   
    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        appiumDriver.quit();
    }
    private static DesiredCapabilities getDesiredCapabilities() {
        final String dir = System.getProperty("user.dir");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Log.error(dir + "/QrRM.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, dir + "/QrRM.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        desiredCapabilities.setCapability("appPackage", "quocviet.com.vn.qrrm");
        desiredCapabilities.setCapability("appActivity", "quocviet.com.vn.qrrm.LoginActivity");
        return desiredCapabilities;
    }
}
