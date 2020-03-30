package com.opencart.pages.comparison;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EmptyComparisonPage extends AbstractPageWithHeader {

    private final String ALERT_LAYOUT_LOCATOR = "//div[@class='alert alert-success alert-dismissible']"; // xpath
    private final String PAGE_CONTENT = "#content p"; //css

    //Components
    private ComparisonPageAlertComponent comparisonPageAlertComponent;

    public EmptyComparisonPage(WebDriver driver) {
        super(driver);
        verifyElements();
    }

    private void verifyElements() {
        WebElement temp = getPageContent();
    }

    //region GETTERS

    /**
     * This method initializing alert component.First, it waits visibility of element, then passing
     * this element to ComparisonPageAlertComponent constructor.
     *
     * @return field of ComparisonPageAlertComponent class type.
     */
    public ComparisonPageAlertComponent getComparisonPageAlertComponent() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        comparisonPageAlertComponent = new ComparisonPageAlertComponent(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ALERT_LAYOUT_LOCATOR))));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return comparisonPageAlertComponent;
    }

    private WebElement getPageContent() {
        return driver.findElement(By.cssSelector(PAGE_CONTENT));
    }

    //endregion

    //region ATOMIC_OPERATIONS

    public String getEmptyPageContentText() {
        return getPageContent().getText();
    }

    public boolean isAlertDisplayed() {
        return getComparisonPageAlertComponent().isAlertDisplayed();
    }

    //endregion
}