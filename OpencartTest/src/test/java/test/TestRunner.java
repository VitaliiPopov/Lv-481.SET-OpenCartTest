package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class TestRunner {
    public static final String URL = "http://40.68.16.59/";

    protected WebDriver driver;

    @BeforeClass
    public void beforeClass() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }
    @AfterClass
    public void afterClass() throws Exception {
        if(driver != null) driver.quit();
    }

    public HomePage getHomePage(){
        return new HomePage(driver);
    }

    public LoginPage getloginPage(){
        return new LoginPage(driver);
    }


}
