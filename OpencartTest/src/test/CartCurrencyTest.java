//package test;
//
//import com.opencart.data.Currencies;
//import com.opencart.pages.HomePage;
//import com.opencart.pages.account.LoginPage;
//import com.opencart.pages.account.MyAccountPage;
//import com.opencart.pages.product_table.CartPage;
//import com.opencart.pages.product_table.WishListPage;
//import com.opencart.pages.search.SearchPage;
//import com.opencart.tools.TestRunner;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//public class CartCurrencyTest extends TestRunner {
//
//    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");
//
//    @Parameters({"myAccountDropdownText"})
//    @Test(priority = 1)
//    public void Login(String myAccountDropdownText) throws InterruptedException {
//        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
//        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
//        HomePage homepahe = myAccountPage.goToHomePage();
//    }
//
//    @DataProvider
//    public Object[][] Products(){
//        return new Object[][]{
//                { "MacBook" },
//                // { "iPhone" }
//        };
//    }
//    @Test(priority = 2,dataProvider = "Products")
//    public void addProductToCart(String productPartialName) throws InterruptedException {
//
//
//        SearchPage searchPage = getHomePage().searchProduct(productPartialName);
//        Thread.sleep(1000);
//        searchPage.clickProductComponentAddToCartButtonByName(productPartialName);
//        Thread.sleep(2000);
//        CartPage cartPage = searchPage.goToCart();
//        Assert.assertTrue(searchPage.isAlertDisplayed());
//
//    }
//
//    @DataProvider
//    public Object[][] currencyData(){
//        return new Object[][]{
//                {Currencies.POUND_STERLING,"£"},
//                {Currencies.EURO,"€"},
//                {Currencies.US_DOLLAR,"$"},
//        };
//    }
//
//
//    @Test(priority = 3,dataProvider = "currencyData")
//    public void changeCurrencyOnCartPage(Currencies currency,String
//            ExpectedSymbolOfCurrency) throws InterruptedException {
//
//        CartPage cartPage = getCartPage().goToCart();
//        cartPage.chooseCurrencyInCart(currency);
//        Assert.assertEquals(cartPage.getCurrencyText(),ExpectedSymbolOfCurrency);
//    }
//}
