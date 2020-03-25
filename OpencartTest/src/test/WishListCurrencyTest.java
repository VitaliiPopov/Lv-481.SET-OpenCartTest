package test;

import com.opencart.data.Currencies;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WishListCurrencyTest extends TestRunner {

    JsonDataConfig jsonParser = new JsonDataConfig("TestData.json");

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void Login(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(7),jsonParser.getPasswordFromJson(7));
        myAccountPage.goToHomePage();
    }

    @DataProvider
    public Object[][] Products(){
        return new Object[][]{
                { "MacBook" },
                //{ "iPhone" }
        };
    }


    @Test(priority = 2,dataProvider = "Products")
    public void addProductToWishList(String productPartialName) throws InterruptedException {


        SearchPage searchPage = getHomePage().searchProduct(productPartialName);
        Thread.sleep(1000);
        searchPage.clickProductComponentAddToWishList(productPartialName);
        Thread.sleep(1000);
        //Assert.assertTrue(searchPage.isAlertDisplayed());


    }

    @DataProvider
    public Object[][] currencyData(){
        return new Object[][]{
                {Currencies.POUND_STERLING,"£"},
                {Currencies.EURO,"€"},
                {Currencies.US_DOLLAR,"$"},
        };
    }

//    @DataProvider
//    public Object[][] currencyDataWithPrice(){
//        return new Object[][]{
//                {Currencies.POUND_STERLING,"£",306.25},
//                {Currencies.EURO,"€",392.30},
//                {Currencies.US_DOLLAR,"$",500.00},
//        };
//    }


    @Test(priority = 3,dataProvider = "currencyData")
    public void changeCurrencyOnWishListPage(Currencies currency,
                                             String expectedSymbolOfCurrency) throws InterruptedException {

        WishListPage wishList = getWishListPage().goToWishList().chooseCurrencyInWishList(currency);

        Assert.assertEquals(wishList.getCurrencyText(),expectedSymbolOfCurrency);

        //Assert.assertEquals(wishListPage.getProductPriceAmountByPartialName(getName()), product.getPrice(currency));
        Thread.sleep(3000);
    }


}
