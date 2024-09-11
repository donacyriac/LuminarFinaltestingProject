package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class Baseclass {

 WebDriver driver;
 @BeforeClass
 public void url() {
    driver = new ChromeDriver();
    driver.get("https://www.sparshhospital.com/book-an-appointment/");
 }
}
