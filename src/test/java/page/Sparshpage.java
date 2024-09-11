package page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sparshpage {

     WebDriver driver;
     
     By firstname=By.xpath("//*[@id=\"book_full_name\"]");
     By phoneno=By.xpath("//*[@id=\"book_phone\"]");
     By email=By.xpath("//*[@id=\"book_email\"]");
     By hospital=By.xpath("//*[@id=\"book_hospital\"]");
     By speciality=By.xpath("//*[@id=\"book_speciality\"]");
     By subspeciality=By.xpath("//*[@id=\"book_subspeciality\"]");
     By doctor=By.xpath("//*[@id=\"book_doctor\"]");
     By date=By .xpath("//*[@id=\"book_preferred_date\"]");
     By file=By.xpath("//*[@id=\"book_reports\"]");
     By message=By.xpath("//*[@id=\"book_message\"]");
     By submit=By.xpath("//*[@id=\"book_submit\"]");
    public Sparshpage(WebDriver driver) {

    	this.driver=driver;
   
    }
    public void entername(String name1)
    {
    	driver.findElement(firstname).sendKeys(name1);
    }
    public void enterphoneno(String phoneno1)
    {
    	driver.findElement(phoneno).sendKeys(phoneno1);
    }
    public void enteremail(String email1)
    {
    	driver.findElement(email).sendKeys(email1);
    }
    public void selecthospital(String hospital1)
    {
    	WebElement hospitalElement = driver.findElement(hospital);
        Select hospital = new Select(hospitalElement);
        hospital.selectByVisibleText(hospital1);   
     }
    public void selectspeciality(String speciality1) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement specialityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(speciality));
        
        Select specialityele = new Select(specialityElement);
        
        
        specialityele.selectByVisibleText(speciality1);
    }


    public void selectsubspeciality(String subspeciality1) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Wait until the speciality is selected and the subspeciality dropdown is visible and populated
        wait.until(ExpectedConditions.visibilityOfElementLocated(subspeciality));
        
        WebElement subspecialityelement = driver.findElement(subspeciality);
        
        // Wait for the subspeciality options to be more than one (assuming "Select Subspeciality" is the default option)
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='book_subspeciality']/option"), 1));

        Select subspecialityele = new Select(subspecialityelement);
        try {
            subspecialityele.selectByVisibleText(subspeciality1);
        } catch (NoSuchElementException e) {
            System.out.println("The subspeciality option: " + subspeciality1 + " was not found.");
        }
    }


    public void selectdoctor(String doctor1)
    {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
         
         // Wait until the speciality is selected and the subspeciality dropdown is visible and populated
         wait.until(ExpectedConditions.visibilityOfElementLocated(doctor));
         
         WebElement doctorelement = driver.findElement(doctor);
         
         // Wait for the subspeciality options to be more than one (assuming "Select Subspeciality" is the default option)
         wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='book_doctor']/option"), 1));

         Select doctorele = new Select(doctorelement);
         try {
        	 doctorele.selectByVisibleText(doctor1);
         } catch (NoSuchElementException e) {
             System.out.println("The subspeciality option: " + doctor1 + " was not found.");
         }
     }
    
    public void selectdate() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(date));
        dateElement.click();
        String targetMonth = "August 2024"; // Adjust this to match the format in your month and year
        String targetDate = "20";

        boolean dateSelected = false;
        
        while (!dateSelected) {
            try {
                // Get the selected month and year from the dropdowns
                WebElement monthDropdown = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]/option[1]"));
                WebElement yearDropdown = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]/option[1]"));

                // Use Select class to interact with dropdowns
                Select monthSelect = new Select(monthDropdown);
                Select yearSelect = new Select(yearDropdown);

                String month1 = monthSelect.getFirstSelectedOption().getText();
                String year = yearSelect.getFirstSelectedOption().getText();
                String act = month1 + " " + year; // Add a space between month and year for comparison

                // Debugging information
                System.out.println("Current month and year: " + act);

                // Compare the concatenated string with the target month and year
                if (act.equals(targetMonth)) {
                    System.out.println("Target month and year matched.");
                    dateSelected = true;
                } else {
                    // Click the next button to navigate to the next month
                    WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]"));

                    // Wait for the element to be clickable
                    WebDriverWait waitForClickable = new WebDriverWait(driver, Duration.ofSeconds(10));
                    waitForClickable.until(ExpectedConditions.elementToBeClickable(nextButton));

                    // Scroll into view and click
                   

                    Thread.sleep(2000); // Wait for the month to change
                }
            } catch (NoSuchElementException e) {
                System.err.println("Element not found: " + e.getMessage());
                break;
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                break;
            }
        }
        
        // Select the date after the correct month and year are visible
        WebElement dateToSelect = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[7]/a"));
        WebDriverWait waitForDate = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForDate.until(ExpectedConditions.elementToBeClickable(dateToSelect)).click();
    }
  

    public void fileupload(String path) throws InterruptedException, AWTException
    {
    	
    	WebElement fileInput = driver.findElement(file);
		Actions act=new Actions(driver);
        act.click(fileInput);
        act.perform();
    	StringSelection stringSelection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // Use Robot class to simulate keyboard actions
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        
    }
    public void entermessage(String message1)
    {
    	driver.findElement(message).sendKeys(message1);
    }
    public void sentrequest()
    {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement request = wait.until(ExpectedConditions.visibilityOfElementLocated(submit));
    Actions act=new Actions(driver);
    act.click(request);
    act.perform();
    driver.navigate().back();

    }

}
