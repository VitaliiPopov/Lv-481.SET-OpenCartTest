package com.opencart.pages.product_table;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends AbstractPageWithHeader {

    //Components
    private WishListContainerComponent wishListContainerComponent;

    public WishListPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        wishListContainerComponent = new  WishListContainerComponent(driver);
    }

    // PAGE OBJECT

    public WishListContainerComponent getWishListContainerComponent(){
        return wishListContainerComponent;
    }

    // BUSINESS LOGIC

    // add Product To Cart from Wish List
    public WishListPage putFromWishListToCartProductByPartialName(String partialProductName){
        getWishListContainerComponent()
                .addToCartProductFromWishListByPartialName(partialProductName);
        return new WishListPage(driver);
    }

    // remove Product from Wish List
    public WishListPage removeFromWishListProductByPartialName(String partialProductName)
    {
        getWishListContainerComponent()
                .removeProductFromWishListByPartialName(partialProductName);
        return new WishListPage(driver);
    }

}