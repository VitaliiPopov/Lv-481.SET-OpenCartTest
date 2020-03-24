package com.opencart.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SuccessModifyProductInCartAlert {

    private WebElement modifyProductInCartAlertLayout;
    //
    private WebElement alertMessage;

    public SuccessModifyProductInCartAlert(WebElement modifyProductInCartAlertLayout) {
        this.modifyProductInCartAlertLayout = modifyProductInCartAlertLayout;
    }

    // Page Object

    //alertMessage
    public WebElement getAlertMessage() {
        alertMessage = modifyProductInCartAlertLayout.findElement(By.xpath("."));
        return alertMessage;
    }

    public String getAlertMessageText() {
        return getAlertMessage().getText();
    }

}