package com.opencart.pages.product;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends AbstractPageWithHeader {

    private WebElement productName;

    private AvailableOptionsComponent availableOptionsComponent;

    public ProductPage(WebDriver driver) {
        super(driver);
        initializeElements();
    }

    private void initializeElements() {
        availableOptionsComponent = new AvailableOptionsComponent(driver);
        productName = driver.findElement(By.cssSelector("#content h1"));
    }

    public String getProductName() {
        return this.productName.getText();
    }
}