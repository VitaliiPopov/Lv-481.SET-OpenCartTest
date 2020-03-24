package com.opencart.pages.product_table;

import com.opencart.data.Currencies;
import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.search.SearchPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends AbstractPageWithHeader {

    //Components
    private List<ProductInWishListContainerComponent> productInWishListContainerComponents;

    public WishListPage(WebDriver driver) {
        super(driver);
        initElements();
    }
    private SearchPage searchPage;
    private void initElements() {
        productInWishListContainerComponents = new ArrayList<>();
    }

    //PAGE OBJECT

    public WishListPage chooseCurrencyInWishList(Currencies currency){
        clickCurrencyByPartialName(currency.toString());
        return new WishListPage(driver);
    }

    public SearchPage getProductsListComponent() {
        return searchPage;
    }
    public double getProductPriceAmountByPartialName(String partialProductName) {
        return getProductsListComponent()
                .getProductComponentByName(partialProductName)
                .getPriceAmount();
    }
}
