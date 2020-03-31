package com.opencart.pages.wishlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductInWishListContainerComponent {

    private WebElement tableRowLayout;

    private WebElement productNameWishList;
    private WebElement unitPriceWishList;
    private WebElement addToCartFromWishList;
    private WebElement removeFromWishList;

    public ProductInWishListContainerComponent(WebElement tableRowLayout) {
        this.tableRowLayout = tableRowLayout;
        initElements();
    }

    public void initElements() {
        productNameWishList = tableRowLayout.findElement(By.cssSelector(".text-left a"));
        unitPriceWishList = tableRowLayout.findElement(By.cssSelector(".price"));
        addToCartFromWishList = tableRowLayout.findElement(By.cssSelector(".btn.btn-primary i.fa"));
        removeFromWishList = tableRowLayout.findElement(By.cssSelector(".text-right a.btn.btn-danger"));
    }

    // PAGE OBJECT

    // PRODUCT NAME (productNameWishList)
    public String getProductNameWishListText() {
        return productNameWishList.getText();
    }

    // UNIT PRICE (unitPriceWishList)
    public String getUnitPriceWishListText() {
        return unitPriceWishList.getText();
    }


    // ADD TO CART BUTTON (addToCartFromWishList)
    public void clickAddToCartFromWishList() {
        addToCartFromWishList.click();
    }

    // REMOVE FROM WISH LIST BUTTON (removeFromWishList)
    public void clickRemoveProductFromWishList() {
        removeFromWishList.click();
    }
}