package com.opencart.pages.comparison;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyComparisonPage extends AbstractPageWithHeader { //TODO ALERT

    private WebElement alertMessage() {
        return driver.findElement(By.cssSelector(".alert-success"));
    }

    private WebElement emptyPageText() {
        return driver.findElement(By.cssSelector("#content p"));
    }

    public EmptyComparisonPage(WebDriver driver) {
        super(driver);
        VerifyElements();
    }

    private void VerifyElements() {
        WebElement temp = alertMessage();
    }

    //region AtomicOperations
    public boolean isAlertDisplayed() {
        return alertMessage().isDisplayed();
    }

    public String getEmptyPageText() {
        return emptyPageText().getText();
    }
}