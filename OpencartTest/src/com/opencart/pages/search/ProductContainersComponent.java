package com.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductContainersComponent {

    //
    private WebElement name;
    private WebElement compareButton;
    private WebElement addToCartButton;
    private WebElement addToWishListButton;

    public ProductContainersComponent(WebElement product) {
        InitializeElements(product);
    }

    private void InitializeElements(WebElement product) {
        name = product.findElement(By.cssSelector("h4 a"));
        compareButton = product.findElement(By.xpath("//div[@class='button-group']/button/i[@class='fa fa-exchange']/.."));
        addToCartButton = product.findElement(By.xpath("//div[@class='button-group']/button/i[@class='fa fa-shopping-cart']/.."));
        addToWishListButton = product.findElement(By.xpath("//div[@class='button-group']/button/i[contains(@class,'fa-heart')]"));
    }

    //Name
    public String getNameText() {
        return name.getText();
    }

    //CompareButton
    public void clickCompareButton() {
        compareButton.click();
    }

    //AddToCartButton
    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    //AddToWishList
    public void clickAddToWishListButton() {
        addToWishListButton.click();
    }
}
