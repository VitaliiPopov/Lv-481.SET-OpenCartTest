package com.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductContainersComponent {

    //Selectors
    private final String PRODUCT_NAME_CSSSELECTOR = "h4 a";
    private final String ADD_TO_CART_BUTTON_XPATH_SELECTOR = "//div[@class='button-group']/button/i[@class='fa fa-shopping-cart']/..";
    //
    private WebElement productContainerLayout;
    //
    private WebElement name;
    private WebElement addToCartButton;

    public ProductContainersComponent(WebElement productContainerLayout) {
        this.productContainerLayout = productContainerLayout;
    }

    //Name
    public WebElement getName() {
        name = productContainerLayout.findElement(By.cssSelector(PRODUCT_NAME_CSSSELECTOR));
        return name;
    }

    public String getNameText() {
        return getName().getText();
    }

    //AddToCartButton
    public WebElement getAddToCartButton() {
        addToCartButton = productContainerLayout.findElement(By.xpath(ADD_TO_CART_BUTTON_XPATH_SELECTOR));
        return addToCartButton;
    }

    public void clickAddToCartButton() {
        getAddToCartButton().click();
    }

}