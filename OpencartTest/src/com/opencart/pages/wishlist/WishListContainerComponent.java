package com.opencart.pages.wishlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WishListContainerComponent {
    private WebElement productTableLayout;

    private List<ProductInWishListContainerComponent> productInWishListContainerComponents;

    public WishListContainerComponent(WebDriver driver) {
        initElements(driver);
    }

    private void initElements(WebDriver driver) {
        productInWishListContainerComponents = new ArrayList<ProductInWishListContainerComponent>();

        for (WebElement current : driver.findElements(By.cssSelector(".table-responsive tbody tr")))
            productInWishListContainerComponents.add(new ProductInWishListContainerComponent(current));
    }

    // PAGE OBJECT

    public ProductInWishListContainerComponent getProductInWishListContainerComponentByPartialName(String partialProductName) {
        ProductInWishListContainerComponent result = null;
        for (ProductInWishListContainerComponent current : productInWishListContainerComponents) {
            if (current.getProductNameWishListText().toLowerCase()
                    .contains(partialProductName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }

    // BUSINESS LOGIC

    // add to cart product from wish list
    public void addToCartProductFromWishListByPartialName(String partialProductName) {
        getProductInWishListContainerComponentByPartialName(partialProductName)
                .clickAddToCartFromWishList();
    }

    // remove product from wish list
    public void removeProductFromWishListByPartialName(String partialProductName) {
        getProductInWishListContainerComponentByPartialName(partialProductName)
                .clickRemoveProductFromWishList();
    }
}