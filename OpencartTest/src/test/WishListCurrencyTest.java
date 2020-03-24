package test;

import com.opencart.data.Currencies;
import com.opencart.data.Product;
import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.product_table.CartPage;
import com.opencart.pages.product_table.WishListPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.tools.ExcelDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WishListCurrencyTest extends TestRunner {

    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void Login(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
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
        Thread.sleep(2000);
        Assert.assertTrue(searchPage.isAlertDisplayed());
        Thread.sleep(2000);

    }

    @DataProvider
    public Object[][] currencyData(){
        return new Object[][]{
                {Currencies.POUND_STERLING,"£"},
                {Currencies.EURO,"€"},
                {Currencies.US_DOLLAR,"$"},
        };
    }

    @DataProvider
    public Object[][] currencyDataWithPrice(){
        return new Object[][]{
                {Currencies.POUND_STERLING,"£",306.25},
                {Currencies.EURO,"€",392.30},
                {Currencies.US_DOLLAR,"$",500.00},
        };
    }


    @Test(priority = 3,dataProvider = "currencyData")
    public void changeCurrencyOnWishListPage(Currencies currency,
                                             String expectedSymbolOfCurrency) throws InterruptedException {
//    public void changeCurrencyOnWishListPage(Currencies currency
//            ,double expectedProductPrice) throws InterruptedException {
        HomePage homePage = getHomePage();
        WishListPage wishListPage = homePage.goToWishList();
        wishListPage.chooseCurrencyInWishList(currency);
       
        Assert.assertEquals(wishListPage.getCurrencyText(),expectedSymbolOfCurrency);

        //Assert.assertEquals(wishListPage.getProductPriceAmountByPartialName(getName()), product.getPrice(currency));
        Thread.sleep(5000);
    }


}
