package com.opencart.pages.comparison;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ComparisonPageAlertComponent {

    private WebElement comparisonPageAlertLayout;
    //
    private final String ALERT_LOCATOR = "./self::div"; //xpath
    private final String PRODUCT_CART_LINK_LOCATOR = "./button[@class='close']/preceding-sibling::a[last()]"; //xpath
    private final String ADD_TO_CART_LINK_LOCATOR = "./a[last()]"; //xpath

    public ComparisonPageAlertComponent(WebElement comparisonPageAlertLayout) {
        this.comparisonPageAlertLayout = comparisonPageAlertLayout;
    }

    //region GETTERS

    public WebElement getAddToCartAlertMessage() {
        return comparisonPageAlertLayout.findElement(By.xpath(ALERT_LOCATOR));
    }

    public WebElement getRemoveAlertMessage() {
        return comparisonPageAlertLayout.findElement(By.xpath(ALERT_LOCATOR));
    }

    public WebElement getProductCartLink() {
        return comparisonPageAlertLayout.findElement(By.xpath(PRODUCT_CART_LINK_LOCATOR));
    }

    public WebElement getAddToCartLink() {
        return comparisonPageAlertLayout.findElement(By.xpath(ADD_TO_CART_LINK_LOCATOR));
    }

    //endregion

    //region ATOMIC_OPERATIONS

    public boolean isAlertDisplayed() {
        return getAddToCartAlertMessage().isDisplayed();
    }

    //endregion
}