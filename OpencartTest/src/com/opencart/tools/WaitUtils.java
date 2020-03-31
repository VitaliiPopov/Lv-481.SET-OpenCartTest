package com.opencart.tools;

import com.opencart.data.products.ProductRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitUtils {

    private final String ALERT_MESSAGE_CSS = ".alert.alert-success";
    private final String LOADING_CART_BUTTON_CSS = ".btn.btn-inverse.btn-block.btn-lg.dropdown-toggle.disabled";
    private WebDriverWait wait;
    private WebDriver driver;
    private final String WISH_LISTS_SELECTOR = "#wishlist-total > span";

    public WaitUtils(WebDriver driver, int seconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, seconds);
    }

    public boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
            }
        };
        return wait.until(jQueryLoad);
    }

    public void waitForAlertVisibility() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        waitForJSandJQueryToLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ALERT_MESSAGE_CSS)));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void waitForElementClickability(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        waitForJSandJQueryToLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void waitForPresenceOfElementLocatedBy(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        waitForJSandJQueryToLoad();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void waitForViewCartButtonLoading() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(LOADING_CART_BUTTON_CSS)));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}