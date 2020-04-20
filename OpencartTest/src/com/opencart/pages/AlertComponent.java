package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertComponent {

    private WebElement alertComponentLayout;
    //
    private static final String ALERT_LAYOUT_SELECTOR = "."; //xpath
    private final String PRODUCT_LINK_SELECTOR = "./a[contains(@href,'?route=product/product')]"; //xpath
    private final String CART_LINK_SELECTOR = "./a[text() = 'shopping cart']"; //xpath
    private final String COMPARE_LINK_SELECTOR = "./a[.='product comparison']"; //xpath

    public AlertComponent(WebElement alertComponentLayout) {
        this.alertComponentLayout = alertComponentLayout;
    }

    // Page Object

    //alertMessage
    public WebElement getAlertMessage() {
        return alertComponentLayout.findElement(By.xpath(ALERT_LAYOUT_SELECTOR));
    }

    public String getAlertMessageText() {
        return getAlertMessage().getText();
    }

    //productLink
    public WebElement getProductLink() {
        return alertComponentLayout.findElement(By.xpath(PRODUCT_LINK_SELECTOR));
    }

    public void clickOnProductLink() {
        getProductLink().click();
    }

    //cartLink
    public WebElement getCartLink() {
        return alertComponentLayout.findElement(By.xpath(CART_LINK_SELECTOR));
    }

    public void clickOnCartLink() {
        getCartLink().click();
    }

    //compareLink
    public WebElement getCompareLink() {
        return alertComponentLayout.findElement(By.xpath(COMPARE_LINK_SELECTOR));
    }

    public void clickOnCompareLink() {
        getCompareLink().click();
    }
}