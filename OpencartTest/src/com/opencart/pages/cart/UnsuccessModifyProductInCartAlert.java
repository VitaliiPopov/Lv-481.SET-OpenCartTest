package com.opencart.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UnsuccessModifyProductInCartAlert {

    private WebElement unsuccessModifyProductInCartAlertLayout;
    //
    private WebElement alertMessage;

    public UnsuccessModifyProductInCartAlert(WebElement unsuccessModifyProductInCartAlertLayout) {
        this.unsuccessModifyProductInCartAlertLayout = unsuccessModifyProductInCartAlertLayout;
    }

    // Page Object

    //alertMessage
    public WebElement getAlertMessage() {
        alertMessage = unsuccessModifyProductInCartAlertLayout.findElement(By.xpath("."));
        return alertMessage;
    }

    public String getAlertMessageText() {
        return getAlertMessage().getText();
    }

}