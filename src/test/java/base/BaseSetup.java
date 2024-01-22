package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseSetup {
    private static AppiumDriver<MobileElement> appiumDriver;

    public static AppiumDriver<MobileElement> getDriver() {
        return appiumDriver;
    }
    private void setAppiumDriver(String appURL) {
        appiumDriver = getAppiumDriver(appURL);
    }
    private static AppiumDriver<MobileElement> getAppiumDriver(String appURL){
       AppiumDriver<MobileElement> appiumDriver = null;

        DesiredCapabilities desiredCapabilities = getDesiredCapabilities();
        URL appiumServer;
        try {
            appiumServer = new URL(System.getenv("APPIUM_SERVER_URL"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        appiumDriver = new AppiumDriver<>(appiumServer,desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

        return appiumDriver;
    }
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
    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        appiumDriver.quit();
    }
    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
       
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "TC26");
        desiredCapabilities.setCapability("appPackage", "quocviet.com.vn.qrrm");
        desiredCapabilities.setCapability("appActivity", "quocviet.com.vn.qrrm.LoginActivity");
        return desiredCapabilities;
    }
}
