package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import page.Sparshpage;
import utils.ExcelUtils;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;

public class Sparshtest extends Baseclass {

    @DataProvider(name = "bookingData")
    public Object[][] getBookingData() throws IOException {
        String filePath = "C:\\Users\\HP\\OneDrive\\Desktop\\IntroToUnitTesting//excel.xlsx"; // Update with your file path
        String sheetName = "Sheet1"; // Update with your sheet name
        return ExcelUtils.readExcelData(filePath, sheetName);
    }

    @Test(dataProvider = "bookingData")
    public void test(String name, String email, String phone, String hospital, String speciality, String subspeciality, String doctor, String filePath, String message) throws InterruptedException, AWTException {
        Sparshpage obj = new Sparshpage(driver);
        String fileupload = "C:\\Users\\HP\\OneDrive\\Desktop\\IntroToUnitTesting\\Book1.xlsx";

        // Entering basic information
        obj.entername(name);
        obj.enteremail(email);
        obj.enterphoneno(phone);

        // Selecting the hospital
        obj.selecthospital(hospital);
        Thread.sleep(2000);

        // Selecting the speciality
        obj.selectspeciality(speciality);

        // Wait to ensure that the subspeciality dropdown is fully populated
        Thread.sleep(2000);

        // Selecting the subspeciality
        obj.selectsubspeciality(subspeciality);
        Thread.sleep(2000);

        // Selecting the doctor
        obj.selectdoctor(doctor);
        Thread.sleep(2000);

        // Selecting the date
        obj.selectdate();

        // Uploading a file
        obj.fileupload(fileupload);

        // Entering a message
        obj.entermessage(message);
        Thread.sleep(2000);

        // Submitting the request
        obj.sentrequest();
        WebElement fullname=driver.findElement(By.xpath("//*[@id=\"book_full_name\"]"));
        // Navigate back and verify that the "Full Name" field is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement fullNameElement = wait.until(ExpectedConditions.visibilityOf(fullname));
        System.out.println("Full Name field is visible: " + fullNameElement.isDisplayed());
    }
}
