package utils.listeners;

import com.aventstack.extentreports.Status;
import io.qameta.allure.Attachment;
import base.BaseSetup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Logs.Log;
import utils.PropertiesFile;
import utils.extentreport.ExtentTestManager;
import utils.extentreport.JiraCreateIssue;
import utils.extentreport.JiraServiceProvider;

import static utils.extentreport.ExtentManager.getExtentReports;

public class ReportListener implements ITestListener {
    AppiumDriver<MobileElement> appiumDriver;
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }
    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }
    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }
    //Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(AppiumDriver<MobileElement> driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        appiumDriver = BaseSetup.getDriver();
        Log.info("Start testing " + iTestContext.getName());
        iTestContext.setAttribute("AppDriver", appiumDriver);
        //Gọi hàm startRecord video trong CaptureHelpers class
//        try {
//            CaptureHelpers.startRecord(iTestContext.getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("End testing " + iTestContext.getName());
        //Kết thúc và thực thi Extents Report
        getExtentReports().flush();
        //Gọi hàm stopRecord video trong CaptureHelpers class
//        CaptureHelpers.stopRecord();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " test is starting...");
        ExtentTestManager.saveToReport(iTestResult.getName(), iTestResult.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " test is passed.");
        //ExtentReports log operation for passed tests.
        ExtentTestManager.logMessage(Status.PASS, getTestDescription(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        PropertiesFile.setPropertiesFile();
        appiumDriver = BaseSetup.getDriver();
        Log.error(getTestName(iTestResult) + " test is failed.");

        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));

        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");
        //Allure Screenshot custom
        Log.error("Screenshot captured for test case: " + getTestName(iTestResult));
        saveScreenshotPNG(appiumDriver);
        //Save a log on Allure report.
        saveTextLog(getTestName(iTestResult) + " failed and screenshot taken!");
        boolean islogIssue = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraCreateIssue.class).isCreateIssue();
//
        if (islogIssue) {
            JiraServiceProvider JiraServiceProvider = new JiraServiceProvider();
            String issueDescription = "Failure Reason from Automation Testing\n\n" + iTestResult.getThrowable().getMessage() + "\n";

            issueDescription.concat(ExceptionUtils.getFullStackTrace(iTestResult.getThrowable()));
            String issueSummary = iTestResult.getMethod().getConstructorOrMethod().getMethod().getName() + " Failed in Automation Testing";

            JiraServiceProvider.CreateIssue(PropertiesFile.getPropValue("USERNAME"), PropertiesFile.getPropValue("JIRA_API_TOKEN"),  PropertiesFile.getPropValue("JIRA_URL"),issueSummary,issueDescription);

        }
    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.warn(getTestName(iTestResult) + " test is skipped.");
        ExtentTestManager.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentTestManager.logMessage("Test failed but it is in defined success ratio " + getTestName(iTestResult));
    }
}
