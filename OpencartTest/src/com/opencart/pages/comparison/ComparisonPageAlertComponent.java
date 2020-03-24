package com.opencart.pages.comparison;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ComparisonPageAlertComponent {

    private WebElement comparisonPageAlertLayout;
    //
    private WebElement addToCartAlertMessage;
    private WebElement removeAlertMessage;
    private WebElement productLink; // TODO go to product from alert
    private WebElement productCartLink;

    public ComparisonPageAlertComponent(WebElement comparisonPageAlertLayout) {
        this.comparisonPageAlertLayout = comparisonPageAlertLayout;
    }

    public WebElement getAddToCartAlertMessage() {
        addToCartAlertMessage = comparisonPageAlertLayout.findElement(By.xpath("self::div"));
        return addToCartAlertMessage;
    }

    public WebElement getRemoveAlertMessage() {
        removeAlertMessage = comparisonPageAlertLayout.findElement(By.xpath("self::div"));
        return removeAlertMessage;
    }

    public WebElement getProductCartLink() {
        productCartLink = comparisonPageAlertLayout.findElement(By.xpath("./button[@class='close']/preceding-sibling::a[last()]"));
        return productCartLink;
    }

    public boolean isAddToCartAlertDisplayed() {
        return getAddToCartAlertMessage().isDisplayed();
    }

    public boolean isRemoveAlertDisplayed() {
        return getRemoveAlertMessage().isDisplayed();
    }
}