package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Logs.Log;
import java.io.*;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class LoginPage {
    private AppiumDriver<MobileElement> appiumDriver;

    private By txt_username = By.id("quocviet.com.vn.qrrm:id/txt_login_username");
    private By txt_password = By.id("quocviet.com.vn.qrrm:id/txt_login_password");
    private By btn_login = By.id("quocviet.com.vn.qrrm:id/btn_login");
    private By tv_message_notify_description = By.id("quocviet.com.vn.qrrm:id/tv_message_notify_description");
    private By btn_message_notify_submit = By.id("quocviet.com.vn.qrrm:id/btn_message_notify_submit");
    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;

    }
    // Sau khi thực hiện click Submit thì khởi tạo trang DashboardPage
    public void Login(String username, String password) throws Exception {

        enter_txt_username(username);
        enter_txt_password(password);
        clickLogin();
    }

    public void enter_txt_username(String txt_username_string) {
        MobileElement txt_username_element = appiumDriver.findElement(txt_username);
        if (txt_username_element.isDisplayed())
            txt_username_element.sendKeys(txt_username_string);
    }
    public void enter_txt_password(String txt_password_string) {
        MobileElement txt_password_element = appiumDriver.findElement(txt_password);
        if (txt_password_element.isDisplayed())
            txt_password_element.sendKeys(txt_password_string);
    }
    public void clickLogin(){
        MobileElement btn_login_element = appiumDriver.findElement(btn_login);
        if (btn_login_element.isDisplayed())
            btn_login_element.click();
    }
    public void  click_btn_message_notify_submit (){
        MobileElement btn_message_notify_submit_element = appiumDriver.findElement(btn_message_notify_submit);
        if (btn_message_notify_submit_element.isDisplayed())
            btn_message_notify_submit_element.click();
    }

    public String getErrorMessage() {
        String strErrorMsg = null;
        MobileElement errorMsg = appiumDriver.findElement(tv_message_notify_description);
        if (errorMsg.isDisplayed() && errorMsg.isEnabled())
            strErrorMsg = errorMsg.getText();
        return strErrorMsg;
    }
    //public void Login() throws IOException, InterruptedException {


        //Create an object of File class to open xls file
        //File file = new File("C:\\Users\\Tuan\\Desktop\\AUTOTEST_LOGINPAGE.xlsx");

        //Create an object of FileInputStream class to read excel file
       // FileInputStream inputStream = new FileInputStream(file);

        //creating workbook instance that refers to .xls file
       // XSSFWorkbook wb = new XSSFWorkbook(inputStream);

        //creating a Sheet object
        //XSSFSheet sheet = wb.getSheet("LOGIN_PAGE");

        //get all rows in the sheet
      //  int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

//        for (int i = 1; i <= rowCount; i++) {
//
//            String jsonString  = sheet.getRow(i).getCell(3).getStringCellValue();
//            sheet.getRow(i).setHeight((short) 1000);
//            JSONObject jsonObject = new JSONObject(jsonString);
//            MobileElement txt_username = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/txt_login_username");
//            txt_username.sendKeys((String)jsonObject.get("username"));
//            MobileElement txt_password = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/txt_login_password");
//            txt_password.sendKeys((String)jsonObject.get("password"));
//            MobileElement btn_login = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/btn_login");
//            btn_login.click();
//            Thread.sleep(5000);
//            MobileElement tv_message_notify_description = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/tv_message_notify_description");
//            String tv_message_notify_description_result = tv_message_notify_description.getText();
//            CellStyle cellStyle = wb.createCellStyle();
//            cellStyle.setAlignment(HorizontalAlignment.CENTER);
//            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//            XSSFCell cellActual = sheet.getRow(i).createCell(5);
//            cellActual.setCellValue(tv_message_notify_description_result);
//            cellActual.setCellStyle(cellStyle);
//            XSSFCell cellStatus = sheet.getRow(i).createCell(6);
//
//
//
//            cellStyle.setBorderBottom(BorderStyle.THIN);
//            cellStyle.setFillForegroundColor(IndexedColors.GREEN.index);
//            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            File formScreenBase64Data = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
//            String formScreenFilePath = System.getProperty("user.dir") + "/screenshot/" + sheet.getRow(i).getCell(1).getStringCellValue()+"_" + LocalDate.now() +".png";
//            FileUtils.copyFile(formScreenBase64Data, new File(formScreenFilePath));
//            XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
//            XSSFClientAnchor ironManAnchor = new XSSFClientAnchor();
//            updateCellWithImage(wb,i+1,7,drawing,ironManAnchor,System.getProperty("user.dir") + "/screenshot/" + sheet.getRow(i).getCell(1).getStringCellValue()+"_" + LocalDate.now() +".png");
//            MobileElement btn_message_notify_submit = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/btn_message_notify_submit");
//            btn_message_notify_submit.click();
//
//            Thread.sleep(2000);
//            if(sheet.getRow(i).getCell(4).getStringCellValue().equals("Máy scanner chưa được cấu hình kết nối tới Server!")){
//                MobileElement img_contact_server = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/img_contact_server");
//                img_contact_server.click();
//                Thread.sleep(2000);
//                MobileElement txt_login_server_name = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/txt_login_server_name");
//                MobileElement txt_login_db_name = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/txt_login_db_name");
//                MobileElement txt_login_server_user = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/txt_login_server_user");
//                MobileElement txt_login_server_password = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/txt_login_server_password");
//                MobileElement btn_login_server_test_connect = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/btn_login_server_test_connect");
//
//                txt_login_server_name.sendKeys("113.176.64.123:40038");
//                txt_login_db_name.sendKeys("DOP_QRCODE");
//                txt_login_server_user.sendKeys("Engineer");
//                txt_login_server_password.sendKeys("Dev@2023!rd");
//                btn_login_server_test_connect.click();
//                Thread.sleep(2000);
//                MobileElement btn_message_notify_submit1 = appiumDriver.findElementById("quocviet.com.vn.qrrm:id/btn_message_notify_submit");
//                btn_message_notify_submit1.click();
//            }
//            if(sheet.getRow(i).getCell(4).getStringCellValue().equals(tv_message_notify_description_result)){
//                //create a new cell in the row at index 6
//                //check if confirmation message is displayed
//                // if the message is displayed , write PASS in the excel sheet
//
//                cellStatus.setCellValue("PASS");
//
//
//            } else {
//
//                //if the message is not displayed , write FAIL in the excel sheet
//                cellStyle.setFillForegroundColor(IndexedColors.RED.index);
//                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                cellStatus.setCellValue("FAIL");
//            }
//
//
//            cellStatus.setCellStyle(cellStyle);
//            sheet.autoSizeColumn(7);
//            // Write the data back in the Excel file
//            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Tuan\\Desktop\\AUTOTEST_LOGINPAGE.xlsx");
//            wb.write(outputStream);
//            //wait for page to come back to registration page after close button is clicked
//            appiumDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
//        }
//        wb.close();
//        appiumDriver.quit();
    //}
//    private static void updateCellWithImage(Workbook workbook, int rowNum,int colNum, XSSFDrawing drawing, XSSFClientAnchor inputImageAnchor, String inputImageName) throws IOException {
//        InputStream inputImageStream = new FileInputStream(inputImageName);
//        byte[] inputImageBytes = IOUtils.toByteArray(inputImageStream);
//        int inputImagePictureID = workbook.addPicture(inputImageBytes, Workbook.PICTURE_TYPE_PNG);
//        inputImageStream.close();
//        inputImageAnchor.setCol1(7);
//        inputImageAnchor.setRow1(rowNum - 1);
//        inputImageAnchor.setCol2(8);
//        inputImageAnchor.setRow2(rowNum);
//        drawing.createPicture(inputImageAnchor, inputImagePictureID);
//    }
}
