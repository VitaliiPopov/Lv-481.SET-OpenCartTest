package com.opencart.pages.wishlist;

import com.opencart.data.Currencies;
import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.tools.RegexUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishListPage extends AbstractPageWithHeader {

    //Components
    private WishListContainerComponent wishListContainerComponent;

    private WebElement continueButton;

    public WishListPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {

        wishListContainerComponent = new WishListContainerComponent(driver);
        //continueButton = driver.findElement(By.xpath("//div[@class='pull-right']/a"));
    }

    // PAGE OBJECT

    // continue button
    public void clickContinueButton() {
        continueButton.click();
    }



    public WishListContainerComponent getWishListContainerComponent() {
        return wishListContainerComponent;
    }

    // change currency in Wish List
    public WishListPage chooseCurrencyInWishList(Currencies currency) {
        clickCurrencyByPartialName(currency.toString());
        return new WishListPage(driver);
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
        while (removeButons.size() > 0) {
            removeButons.get(0).click();
            removeButons = driver.findElements(By.cssSelector(".text-right a.btn.btn-danger"));
        }
        return new WishListEmptyPage(driver);
    }

    public MyAccountPage goToMyAccountPage() {
        clickContinueButton();
        return new MyAccountPage(driver);
    }

}

