package utils.keywords;

import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.Logs.Log;
import utils.constants.ConstantGlobal;
import utils.extentreport.AllureManager;
import utils.extentreport.ExtentTestManager;
import utils.helper.PropertiesHelpers;
import utils.helper.SystemHelpers;
import utils.helper.CaptureHelpers;

import java.util.List;


public class AppUI {
    private final static long EXPLICIT_TIMEOUT = ConstantGlobal.EXPLICIT_TIMEOUT;
    private final static long STEP_TIME = ConstantGlobal.STEP_TIME;
    private final static long PAGE_LOAD_TIMEOUT = ConstantGlobal.PAGE_LOAD_TIMEOUT;

    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static MobileElement getMobileElement(By by) {
        return BaseSetup.getDriver().findElement(by);
    }

    public static List<MobileElement> getMobileElements(By by) {
        return BaseSetup.getDriver().findElements(by);
    }

    @Step("Verify Equals: {0} and {1}")
    public static void verifyEquals(Object actual, Object expected) {
//        waitForPageLoaded();
        sleep(STEP_TIME);
        Log.info("Verify equals: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "Verify equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, "Fail. Not match. '" + actual.toString() + "' != '" + expected.toString() + "'");
    }

    @Step("Verify Equals: {0} and {1}")
    public static void verifyEquals(Object actual, Object expected, String message) {
//        waitForPageLoaded();
        sleep(STEP_TIME);
        Log.info("Verify equals: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "Verify equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Check element existing {0}")
    public static Boolean checkElementExist(By by) {
//        waitForPageLoaded();
//        waitForElementVisible(by);
        List<MobileElement> listElement = getMobileElements(by);

        if (listElement.size() > 0) {
            Log.info("Check Element Exist: " + true + " --- " + by);
            return true;
        } else {
            Log.info("Check Element Exist: " + false + " --- " + by);
            return false;
        }
    }

    @Step("Open URL: {0}")
    public static void openURL(String url) {
        BaseSetup.getDriver().get(url);
        sleep(STEP_TIME);
        Log.info("Open URL: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);
        AllureManager.saveTextLog("Open URL: " + url);
        //waitForPageLoaded();
        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("openURL_" + SystemHelpers.makeSlug(url));
        }
    }

    @Step("Click element {0}")
    public static void clickElement(By by) {
        //waitForPageLoaded();
        //waitForElementVisible(by);
        sleep(STEP_TIME);
        getMobileElement(by).click();
        Log.info("Click element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("clickElement_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Click element {0} with timeout {1}")
    public static void clickElement(By by, int timeout) {
        //waitForPageLoaded();
        //waitForElementVisible(by, timeout);
        sleep(STEP_TIME);
        getMobileElement(by).click();
        Log.info("Click element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("clickElement_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Set text {1} on {0}")
    public static void setText(By by, String value) {
        //waitForPageLoaded();
        //waitForElementVisible(by);
        sleep(STEP_TIME);
        getMobileElement(by).sendKeys(value);
        Log.info("Set text: " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("setText_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Set text {1} on {0} and press key {2}")
    public static void setTextAndKey(By by, String value, Keys key) {
        //waitForPageLoaded();
        //waitForElementVisible(by);
        sleep(STEP_TIME);
        getMobileElement(by).sendKeys(value, key);
        Log.info("Set text: " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("setText_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Get text of element {0}")
    public static String getElementText(By by) {
        //waitForPageLoaded();
        //waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getMobileElement(by).getText();
        Log.info("Get text: " + text);
        ExtentTestManager.logMessage(Status.PASS, "Get text: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    //Wait for Element

//    public static void waitForElementVisible(By by) {
//        try {
//
//            WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(),Duration.ofSeconds(EXPLICIT_TIMEOUT),Duration.ofMillis(500));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        } catch (Throwable error) {
//            Log.error("Timeout waiting for the element Visible. " + by.toString());
//            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
//        }
//    }
//
//    public static void waitForElementVisible(By by, int timeOut) {
//        try {
//            WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        } catch (Throwable error) {
//            Log.error("Timeout waiting for the element Visible. " + by.toString());
//            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
//        }
//    }
//
//    public static void waitForElementPresent(By by) {
//        try {
//            WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds(EXPLICIT_TIMEOUT), Duration.ofMillis(500));
//            wait.until(ExpectedConditions.presenceOfElementLocated(by));
//        } catch (Throwable error) {
//            Log.error("Element not exist. " + by.toString());
//            Assert.fail("Element not exist. " + by.toString());
//        }
//    }
//
//    public static void waitForElementPresent(By by, int timeOut) {
//        try {
//            WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
//            wait.until(ExpectedConditions.presenceOfElementLocated(by));
//        } catch (Throwable error) {
//            Log.error("Element not exist. " + by.toString());
//            Assert.fail("Element not exist. " + by.toString());
//
//        }
//    }
//
//    public static void waitForElementClickable(By by) {
//        try {
//            WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds(EXPLICIT_TIMEOUT), Duration.ofMillis(500));
//            wait.until(ExpectedConditions.elementToBeClickable(getMobileElement(by)));
//        } catch (Throwable error) {
//            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
//            Log.info("Timeout waiting for the element ready to click. " + by.toString());
//        }
//    }
//
//    public static void waitForElementClickable(By by, int timeOut) {
//        try {
//            WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
//            wait.until(ExpectedConditions.elementToBeClickable(getMobileElement(by)));
//        } catch (Throwable error) {
//            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
//            Log.info("Timeout waiting for the element ready to click. " + by.toString());
//        }
//    }

    //Vài hàm bổ trợ nâng cao hơn

    @Step("Scroll to element {0}")
    public static void scrollToElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) BaseSetup.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getMobileElement(by));
    }

    @Step("Scroll to element {0}")
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) BaseSetup.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("scrollToElement_" + SystemHelpers.makeSlug(element.getText()));
        }
    }

    @Step("Scroll to element {0} with type {1}")
    public static void scrollToElement(WebElement element, String type) {
        JavascriptExecutor js = (JavascriptExecutor) BaseSetup.getDriver();
        js.executeScript("arguments[0].scrollIntoView(" + type + ");", element);
    }

    @Step("Scroll to position with X={0}, Y={1}")
    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) BaseSetup.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    @Step("Move to element {0}")
    public static boolean moveToElement(By by) {
        try {
            Actions action = new Actions(BaseSetup.getDriver());
            action.moveToElement(getMobileElement(by)).release(getMobileElement(by)).build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(BaseSetup.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    @Step("Highlight element {0}")
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (BaseSetup.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) BaseSetup.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getMobileElement(by));
            sleep(1);
        }
        return getMobileElement(by);
    }

    @Step("Hover on element {0}")
    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(BaseSetup.getDriver());
            action.moveToElement(getMobileElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Mouse hover on element {0}")
    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(BaseSetup.getDriver());
            action.moveToElement(getMobileElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Drag element {0} to element {1}")
    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(BaseSetup.getDriver());
            action.dragAndDrop(getMobileElement(fromElement), getMobileElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(BaseSetup.getDriver());
            action.clickAndHold(getMobileElement(fromElement)).moveToElement(getMobileElement(toElement)).release(getMobileElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(BaseSetup.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getMobileElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

//    @Step("Press ENTER on keyboard")
//    public static boolean pressENTER() {
//        try {
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Step("Press ESC on keyboard")
//    public static boolean pressESC() {
//        try {
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ESCAPE);
//            robot.keyRelease(KeyEvent.VK_ESCAPE);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Step("Press F11 on keyboard")
//    public static boolean pressF11() {
//        try {
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_F11);
//            robot.keyRelease(KeyEvent.VK_F11);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    /**
     * Wait for Page
     * Chờ đợi trang tải xong (Javascript) với thời gian thiết lập sẵn
     */
//    public static void waitForPageLoaded() {
//        WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds((PAGE_LOAD_TIMEOUT), Duration.ofMillis(500)));
//        JavascriptExecutor js = (JavascriptExecutor) BaseSetup.getDriver();
//
//        // wait for Javascript to loaded
//        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) BaseSetup.getDriver()).executeScript("return document.readyState").toString().equals("complete");
//
//        //Get JS is Ready
//        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
//
//        //Wait Javascript until it is Ready!
//        if (!jsReady) {
//            Log.info("Javascript in NOT Ready!");
//            //Wait for Javascript to load
//            try {
//                wait.until(jsLoad);
//            } catch (Throwable error) {
//                error.printStackTrace();
//                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
//            }
//        }
//    }

    /**
     * Chờ đợi JQuery tải xong với thời gian thiết lập sẵn
     */
//    public static void waitForJQueryLoad() {
//        WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
//        JavascriptExecutor js = (JavascriptExecutor) BaseSetup.getDriver();
//
//        //Wait for jQuery to load
//        ExpectedCondition<Boolean> jQueryLoad = driver -> {
//            assert driver != null;
//            return ((Long) ((JavascriptExecutor) BaseSetup.getDriver()).executeScript("return jQuery.active") == 0);
//        };
//
//        //Get JQuery is Ready
//        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");
//
//        //Wait JQuery until it is Ready!
//        if (!jqueryReady) {
//            Log.info("JQuery is NOT Ready!");
//            try {
//                //Wait for jQuery to load
//                wait.until(jQueryLoad);
//            } catch (Throwable error) {
//                Assert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
//            }
//        }
//    }

    //Wait for Angular Load

    /**
     * Chờ đợi Angular tải xong với thời gian thiết lập sẵn
     */
//    public static void waitForAngularLoad() {
//        WebDriverWait wait = new WebDriverWait(BaseSetup.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
//        JavascriptExecutor js = (JavascriptExecutor) BaseSetup.getDriver();
//        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
//
//        //Wait for ANGULAR to load
//        ExpectedCondition<Boolean> angularLoad = driver -> {
//            assert driver != null;
//            return Boolean.valueOf(((JavascriptExecutor) BaseSetup.getDriver()).executeScript(angularReadyScript).toString());
//        };
//
//        //Get Angular is Ready
//        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());
//
//        //Wait ANGULAR until it is Ready!
//        if (!angularReady) {
//            Log.info("Angular is NOT Ready!");
//            //Wait for Angular to load
//            try {
//                //Wait for jQuery to load
//                wait.until(angularLoad);
//            } catch (Throwable error) {
//                Assert.fail("Timeout waiting for Angular load. (" + PAGE_LOAD_TIMEOUT + "s)");
//            }
//        }
//
//    }
}