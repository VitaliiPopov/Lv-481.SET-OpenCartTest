package opencart.tools;

import opencart.data.ConstantVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static WebDriver driver = null;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            if (ConstantVariables.BROWSER_NAME.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "./target/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            } else if (ConstantVariables.BROWSER_NAME.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "./target/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }
}
