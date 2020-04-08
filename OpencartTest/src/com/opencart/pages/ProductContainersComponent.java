package com.opencart.pages;

import com.opencart.tools.RegexUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductContainersComponent {

    //Selectors
    private static final String PRODUCT_NAME_SELECTOR = "h4 a"; // css
    private static final String ADD_TO_CART_BUTTON_SELECTOR = ".//i[@class='fa fa-shopping-cart']/.."; //xpath
    private static final String COMPARE_BUTTON_SELECTOR = ".//i[@class='fa fa-exchange']/.."; //xpath
    private static final String PICTURE_SELECTOR = ".image a"; // css
    private static final String ADD_TO_WISHLIST_BUTTON_SELECTOR = ".//i[contains(@class,'fa-heart')]/.."; //xpath
    private static final String PRICE_SELECTOR = "//p[contains(@class,'price') and not(child::span[@class='price-new'])]";

    //Layout
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

    public void clickOnNameLabel() {
        getName().click();
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

    //Picture
    public WebElement getPicture() {
        return productContainerLayout.findElement(By.cssSelector(PICTURE_SELECTOR));
    }

    public void clickPicture() {
        getPicture().click();
    }

    //AddToWishListButton
    public WebElement getAddToWishListButton() {
        return productContainerLayout.findElement(By.xpath(ADD_TO_WISHLIST_BUTTON_SELECTOR));
    }

    public void clickAddToWishListButton() {
        getAddToWishListButton().click();
    }

    //Price
    public WebElement getPrice() {
        return productContainerLayout.findElement(By.xpath(PRICE_SELECTOR));
    }

    public double getPriceAmount() {
        return RegexUtils.extractFirstDouble(getPrice().getText());
    }
}