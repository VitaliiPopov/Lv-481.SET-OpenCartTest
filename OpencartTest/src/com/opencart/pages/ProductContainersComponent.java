package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductContainersComponent {

    //Selectors
    private final String PRODUCT_NAME_SELECTOR = "h4 a"; // css
    private final String ADD_TO_CART_BUTTON_SELECTOR = ".//i[@class='fa fa-shopping-cart']/.."; //xpath
    private final String COMPARE_BUTTON_SELECTOR = ".//i[@class='fa fa-exchange']/.."; //xpath
    //
    private WebElement productContainerLayout;

    public ProductContainersComponent(WebElement productContainerLayout) {
        this.productContainerLayout = productContainerLayout;
    }

    //Name
    public WebElement getName() {
        return productContainerLayout.findElement(By.cssSelector(PRODUCT_NAME_SELECTOR));

    }

    public String getNameText() {
        return getName().getText();
    }

    //AddToCartButton
    public WebElement getAddToCartButton() {
        return productContainerLayout.findElement(By.xpath(ADD_TO_CART_BUTTON_SELECTOR));
    }

    public void clickAddToCartButton() {
        getAddToCartButton().click();
    }

    //CompareButton
    public WebElement getCompareButton() {
        return productContainerLayout.findElement(By.xpath(COMPARE_BUTTON_SELECTOR));
    }

    public void clickCompareButton() {
        getCompareButton().click();
    }
}