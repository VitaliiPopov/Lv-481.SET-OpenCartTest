package com.opencart.tools;

import com.opencart.data.ConstantVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Instance {

    protected static WebDriver driver = null;

    private Instance() {
        throw new IllegalStateException("Utility class");
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            if (System.getProperty("BrowserName").equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                options.addArguments("--start-maximized");
                try {
                    driver = new RemoteWebDriver(new URL(ConstantVariables.HUB_URL), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (System.getProperty("BrowserName").equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability(CapabilityType.BROWSER_NAME, "firefox");
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                try {
                    driver = new RemoteWebDriver(new URL(ConstantVariables.HUB_URL), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        return driver;
    }

    public static void getURL() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConstantVariables.URL);
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getAdminDriver() { // TODO
        driver.get(ConstantVariables.ADMIN_URL);
        return driver;
    }

    public static void clearCookies() {
        driver.manage().deleteAllCookies();
    }
    
}