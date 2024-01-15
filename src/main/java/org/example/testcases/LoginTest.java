package org.example.testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.base.BaseSetup;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;

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
