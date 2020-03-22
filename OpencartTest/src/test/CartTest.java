package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.tools.ExcelDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartTest extends TestRunner {

    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");



    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void loginTest(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        HomePage homePage = myAccountPage.goToHomePage();

       // System.out.println(homePage.getCartDropdownComponent().checkTotalPrice());
        homePage.deleteProductInCartButton();
       // homePage.c

//        CartPage cartPage = homePage.goToCartPage();
//        System.out.println(cartPage.getProductInCartContainerComponentsSize());
//        System.out.println(cartPage.checkTotalPrice());

//        //cartPage.goToCartPageAfterRemoveProductByName("iPhone");
//        cartPage.goToCartPageAfterRemoveAllProducts();
//        Assert.assertEquals(cartPage.getEmptyCartText(), "Your shopping cart is empty!");

//        cartPage.goToCartPageAfterRefreshProductQuantityByName("iPhone", 5);
//        //Assert.assertTrue(cartPage.checkTotalPrice());
//        Assert.assertTrue(cartPage.getTotalPrice().equals(BigDecimal.valueOf(505.0)));

//        SearchPage searchPage = homePage.goToSearchPageAfterSearchProduct("ip");
//        searchPage.afterClickProductComponentAddToCartButtonByName("iPhone");
//        CartPage cartPage = searchPage.goToCartPage();

//        SearchPage searchPage = homePage.goToSearchPageAfterSearchProduct("cinema");
//        ProductPage productPage = (ProductPage) searchPage.afterClickProductComponentAddToCartButtonByName("Apple Cinema 30");
//        productPage.getAvailableOptionsComponent().chooseRadioButtonOptionByPartialName("Radio", "Small");
//        productPage.getAvailableOptionsComponent().chooseCheckBoxOptionByPartialName("Checkbox", "Checkbox 2");
////        productPage.getAvailableOptionsComponent().chooseSelectsOptionByName("Select", "Red\n" +
////                "                                (+$4.00)\n" +
////                "                 ");
//        productPage.getAvailableOptionsComponent().chooseSelectsOptionByIndex("Select", 1);
//        ProductPage productPageAfterAddToCart = productPage.goToProductPageAfterAddToCart(3);
//        CartPage cartPage = productPageAfterAddToCart.goToCartPage();
        Thread.sleep(2000);
    }


}
