package test;

import com.opencart.data.Currencies;
import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.tools.test_runner.CurrencyTestRunner;
import com.opencart.tools.JsonDataConfig;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartCurrencyTest extends CurrencyTestRunner {

    JsonDataConfig jsonParser = new JsonDataConfig("TestData.json");

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void login(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(8), jsonParser.getPasswordFromJson(8));
        HomePage homepage = myAccountPage.goToHomePage();
    }


    @Test(priority = 2)
    @Parameters({"productName"})
    public void addProductToCart(String productPartialName) throws InterruptedException {
        SearchPage searchPage = getHomePage().searchProduct(productPartialName);
        Thread.sleep(1000);// ONLY FOR PRESENTATION
        searchPage.clickProductComponentAddToCartButtonByName(productPartialName);
    }

    @DataProvider
    public Object[][] currencyData() {
        return new Object[][]{
                {Currencies.POUND_STERLING, "£"},
                {Currencies.EURO, "€"},
                {Currencies.US_DOLLAR, "$"},
        };
    }

    @Test(priority = 3, dataProvider = "currencyData")
    public void changeCurrencyOnCartPage(Currencies currency, String
            ExpectedSymbolOfCurrency) throws InterruptedException {
        CartPage cartPage = getCartPage().goToCart();
        cartPage.chooseCurrencyInCart(currency);
        Assert.assertEquals(cartPage.getCurrencyText(), ExpectedSymbolOfCurrency);
    }
}
