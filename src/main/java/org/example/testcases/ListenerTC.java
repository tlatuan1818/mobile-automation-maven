package org.example.testcases;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.example.base.BaseSetup;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Logs.Log;
import utils.extentreport.ExtentTestManager;

import java.sql.DriverManager;

import static java.lang.Thread.sleep;

@Epic("Login test Qrcode")
@Feature("ListenerTC")
public class ListenerTC extends BaseSetup {
    private AppiumDriver<MobileElement> appiumDriver;
    public LoginPage loginPage;

    @BeforeClass
    @Description("Khoi tao Driver")
    public void setUp() {
        appiumDriver = getDriver();
    }


    @Test(priority = 1 , description = "Sign in page to Qrcode system")
    @Step("Sign in page to Qrcode system")
    public void Login() throws Exception {
        loginPage = new LoginPage(appiumDriver);

        loginPage.Login("tramleanh.tuan","1234");
        sleep(1000);
        //System.out.print(loginPage.getErrorMessage());
    }
    @Test(priority = 2, description = "CheckTitle")
    @Step("Check Title")
    public void checkTitle() {
        String expectedTitle = "Anh Tuan Tester";
        String originalTitle = "Anh Tuan";
        Assert.assertEquals(originalTitle, expectedTitle, "Title of the website do not match");
    }
    @Test(priority = 3)  //Skip Test
    public void skipTest() {
        throw new SkipException("Skipping The Test Method ");
    }

}
