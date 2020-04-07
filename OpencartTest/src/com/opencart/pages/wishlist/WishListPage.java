package com.opencart.pages.wishlist;

import com.opencart.data.Currencies;
import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.tools.RegexUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishListPage extends AbstractPageWithHeader {

    //Components
    private WishListContainerComponent wishListContainerComponent;

    public WishListPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        wishListContainerComponent = new WishListContainerComponent(driver);
    }

    // PAGE OBJECT

    public WishListContainerComponent getWishListContainerComponent() {
        return wishListContainerComponent;
    }

    // change currency in Wish List
    public WishListPage chooseCurrencyInWishList(Currencies currency) {
        clickCurrencyByPartialName(currency.toString());
        return new WishListPage(driver);
    }

    // Getting product price in Wish
    public String getProductPriceTextInWishList(String partialProductName){
        return getWishListContainerComponent().getUnitPrice(partialProductName);
    }

    //parse to double by Regex
    public double getProductPriceInWishList(String partialProductName){
        return RegexUtils.extractFirstDouble(getProductPriceTextInWishList(partialProductName));
    }

    // BUSINESS LOGIC

    // add Product To Cart from Wish List
    public WishListPage putFromWishListToCartProductByPartialName(String partialProductName) {
        getWishListContainerComponent()
                .addToCartProductFromWishListByPartialName(partialProductName);
        return new WishListPage(driver);
    }

    // remove Product from Wish List
    public Object removeFromWishListProductByPartialName(String partialProductName) {
        int currentNumberInList = RegexUtils.extractFirstNumber(getWishListText());
        if (currentNumberInList == 1) {
            getWishListContainerComponent()
                    .removeProductFromWishListByPartialName(partialProductName);
            return new WishListEmptyPage(driver);
        } else {
            getWishListContainerComponent()
                    .removeProductFromWishListByPartialName(partialProductName);
            return new WishListPage(driver);
        }
    }

    // remove ALL Products from Wish List
    public WishListEmptyPage removeAllProductsFromWishList() {
        List<WebElement> removeButons = driver.findElements(By.cssSelector(".text-right a.btn.btn-danger"));
        while (!removeButons.isEmpty()) {
            removeButons.get(0).click();
            removeButons = driver.findElements(By.cssSelector(".text-right a.btn.btn-danger"));
        }
        return new WishListEmptyPage(driver);
    }
}

