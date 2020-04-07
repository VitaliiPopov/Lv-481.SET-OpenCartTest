
package test;

import com.opencart.data.Currencies;

import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.wishlist.WishListEmptyPage;
import com.opencart.tools.test_runner.CurrencyTestRunner;
import com.opencart.tools.JsonDataConfig;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WishListCurrencyTest extends CurrencyTestRunner {

    JsonDataConfig jsonParser = new JsonDataConfig("TestData.json");

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void login(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(8), jsonParser.getPasswordFromJson(8));
        myAccountPage.goToHomePage();
    }

    @DataProvider
    public Object[][] currencyData() {
        return new Object[][]{
                {"MacBook", Currencies.POUND_STERLING, 306.25},
                {"iPhone", Currencies.EURO, 79.24},
                {"Palm Treo Pro", Currencies.US_DOLLAR, 279.99},
        };
    }

    @Test(priority = 3, dataProvider = "currencyData")
    public void changeCurrencyOnWishListPage(String productPartialName, Currencies currency,
                                             double price) throws InterruptedException {
        SearchPage searchPage = getHomePage().searchProduct(productPartialName);
        Thread.sleep(1000);// ONLY FOR PRESENTATION
        searchPage.clickProductComponentAddToWishList(productPartialName);

        WishListPage wishList = getWishListPage().goToWishList().chooseCurrencyInWishList(currency);
        Assert.assertEquals(wishList.getProductPriceInWishList(productPartialName), price);

        System.out.println(wishList.getProductPriceInWishList(productPartialName));

        wishList.removeFromWishListProductByPartialName(productPartialName);
        Thread.sleep(2000);// ONLY FOR PRESENTATION

        String emptyMessage = getHomePage().goToWishListEmpty().getLabelText();
        Assert.assertEquals(emptyMessage, WishListEmptyPage.EMPTY_WISH_LIST_MESSAGE);
    }
    
}
