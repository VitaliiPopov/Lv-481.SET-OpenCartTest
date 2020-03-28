package com.opencart.tools;

import com.opencart.data.ConstantVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver = null;

    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            if (ConstantVariables.BROWSER_NAME.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "./target/drivers/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                driver = new ChromeDriver(options);
                tdriver.set(driver);
            } else if (ConstantVariables.BROWSER_NAME.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "./target/drivers/geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                driver = new FirefoxDriver(options);
                tdriver.set(driver);
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(System.getenv("URL"));
        return driver;
    }

    public static void quit() {
        driver.quit();
        driver = null;
    }

    public static WebDriver getAdminDriver() {
        driver = getDriver();
        driver.get(System.getenv("ADMIN_URL"));
        return driver;
    }

    public static void clearCookies() {
        driver.manage().deleteAllCookies();
    }
}