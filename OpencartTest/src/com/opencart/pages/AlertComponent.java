package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertComponent {

    private WebElement addProductToCartAlertComponentLayout;
    //
    private WebElement alertMessage;
    private WebElement productLink;
    private WebElement cartLink;

    public AlertComponent(WebElement addProductToCartAlertComponentLayout) {
        this.addProductToCartAlertComponentLayout = addProductToCartAlertComponentLayout;
    }

    // Page Object

    //alertMessage
    public WebElement getAlertMessage() {
        alertMessage = addProductToCartAlertComponentLayout.findElement(By.xpath("."));
        return alertMessage;
    }

    public String getAlertMessageText() {
        return getAlertMessage().getText();
    }

    //productLink
    public WebElement getProductLink() {
        productLink = addProductToCartAlertComponentLayout.findElement(By.xpath("./a[contains(@href,'?route=product/product')]"));
        return productLink;
    }

    public void clickOnProductLink() {
        getProductLink().click();
    }

    //cartLink
    public WebElement getCartLink() {
        cartLink = addProductToCartAlertComponentLayout.findElement(By.xpath("./a[text() = 'shopping cart']"));
        return cartLink;
    }

    public void clickOnCartLink() {
        getCartLink().click();
    }

}