package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.tools.ExcelDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends TestRunner {

    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void loginTest(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        HomePage homePage = myAccountPage.goToHomePage();
    }

    //Add to cart tests
    @Test(priority = 2)
    public void addProductWithoutOptionsToCartAlertCheck() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        CartPage cartPage = addToCartIPhoneSearchPage.goToShoppingCartFromAlert();
        softAssert.assertTrue(cartPage.getPageTitleText().contains("Shopping Cart"));
        softAssert.assertTrue(cartPage.getProductInCartContainerComponentsSize() == 1);

        softAssert.assertAll();
        Thread.sleep(2000);//Only for presentation
    }

    @Test(priority = 3)
    public void addProductWithOptionsToCartAlertCheck() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage cinemaSearchPage = getHomePage().goToSearchPageAfterSearchProduct("cinema");
        //Assert TODO -> SearchCriteriaComponent

        ProductPage productPage = (ProductPage) cinemaSearchPage.afterClickOnProductComponentAddToCartButtonByName("Apple Cinema 30");
        softAssert.assertEquals(productPage.getProductName(), "Apple Cinema 30");

        productPage.getAvailableOptionsComponent().setOptionsForAppleCinema("Radio", "Small",
                "Checkbox", "Checkbox 2",
                "Select", "Red (+$4.00)");
        ProductPage productPageAfterAddToCart = productPage.goToProductPageAfterAddToCart(3);
        softAssert.assertTrue(productPageAfterAddToCart.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added Apple Cinema 30 to your shopping cart!"));

        CartPage cartPage = productPageAfterAddToCart.goToShoppingCartFromAlert();
        softAssert.assertTrue(cartPage.getPageTitleText().contains("Shopping Cart"));
        softAssert.assertTrue(cartPage.getProductInCartContainerComponentsSize() == 1);

        softAssert.assertAll();
        Thread.sleep(2000);//Only for presentation
    }

    @Test(priority = 4)
    public void addTooManyProduct() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage cinemaSearchPage = getHomePage().goToSearchPageAfterSearchProduct("cinema");
        //Assert TODO -> SearchCriteriaComponent

        ProductPage productPage = (ProductPage) cinemaSearchPage.afterClickOnProductComponentAddToCartButtonByName("Apple Cinema 30");
        softAssert.assertEquals(productPage.getProductName(), "Apple Cinema 30");

        productPage.getAvailableOptionsComponent().setOptionsForAppleCinema("Radio", "Small",
                "Checkbox", "Checkbox 2",
                "Select", "Red (+$4.00)");
        ProductPage productPageAfterAddToCart = productPage.goToProductPageAfterAddToCart(10000);
        softAssert.assertTrue(productPageAfterAddToCart.getSearchPageAlertComponent().getAlertMessageText().contains("Unsuccess:"));

        CartPage cartPage = productPageAfterAddToCart.goToCartPageByLinkInHeader();
        softAssert.assertTrue(cartPage.getPageTitleText().contains("Shopping Cart"));
        softAssert.assertTrue(cartPage.checkIsTheProductInCartComponentByName("Apple Cinema 30"));

        softAssert.assertAll();
        Thread.sleep(2000);//Only for presentation
    }

    @Test(priority = 5)
    public void addToCartCheckCartButton() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        softAssert.assertTrue(addToCartIPhoneSearchPage.getProductInCartButtonContainerComponentsSize() == 1);
        softAssert.assertTrue(addToCartIPhoneSearchPage.checkIsTheProductInCartComponentByName("iPhone"));

        softAssert.assertAll();
        Thread.sleep(2000);//Only for presentation
    }

    //Total sum test
    @Test(priority = 6)
    public void checkTotalSumInCartPage() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        SearchPage cinemaSearchPage = addToCartIPhoneSearchPage.goToSearchPageAfterSearchProduct("cinema");
        //Assert TODO -> SearchCriteriaComponent

        ProductPage productPage = (ProductPage) cinemaSearchPage.afterClickOnProductComponentAddToCartButtonByName("Apple Cinema 30");
        softAssert.assertEquals(productPage.getProductName(), "Apple Cinema 30");

        productPage.getAvailableOptionsComponent().setOptionsForAppleCinema("Radio", "Small",
                "Checkbox", "Checkbox 2",
                "Select", "Red (+$4.00)");
        ProductPage productPageAfterAddToCart = productPage.goToProductPageAfterAddToCart(1);
        softAssert.assertTrue(productPageAfterAddToCart.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added Apple Cinema 30 to your shopping cart!"));

        CartPage cartPage = productPageAfterAddToCart.goToCartPageByLinkInHeader();
        softAssert.assertTrue(cartPage.getPageTitleText().contains("Shopping Cart"));
        softAssert.assertTrue(cartPage.checkTotalPrice());

        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void checkTotalSumInCartButton() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        SearchPage cinemaSearchPage = addToCartIPhoneSearchPage.goToSearchPageAfterSearchProduct("cinema");
        //Assert TODO -> SearchCriteriaComponent

        ProductPage productPage = (ProductPage) cinemaSearchPage.afterClickOnProductComponentAddToCartButtonByName("Apple Cinema 30");
        softAssert.assertEquals(productPage.getProductName(), "Apple Cinema 30");

        productPage.getAvailableOptionsComponent().setOptionsForAppleCinema("Radio", "Small",
                "Checkbox", "Checkbox 2",
                "Select", "Red (+$4.00)");
        ProductPage productPageAfterAddToCart = productPage.goToProductPageAfterAddToCart(3);
        Thread.sleep(2000);
        softAssert.assertTrue(productPageAfterAddToCart.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added Apple Cinema 30 to your shopping cart!"));

        softAssert.assertTrue(addToCartIPhoneSearchPage.checkTotalPrice());

        softAssert.assertAll();
    }

    //Check buttons in cart page
    @Test(priority = 8)
    public void checkCorrectFunctionOfRefreshButtonInCart() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        CartPage cartPage = addToCartIPhoneSearchPage.goToCartPageByLinkInHeader();
        cartPage.checkRefreshFunction("iPhone", 1);
        softAssert.assertTrue(cartPage.getSuccessModifyProductInCartAlert().getAlertMessageText().contains("Success: You have modified your shopping cart!"));

        softAssert.assertAll();
    }

    @Test(priority = 9)
    public void checkUncorrectFunctionOfRefreshButtonInCart() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage cinemaSearchPage = getHomePage().goToSearchPageAfterSearchProduct("cinema");
        //Assert TODO -> SearchCriteriaComponent

        ProductPage productPage = (ProductPage) cinemaSearchPage.afterClickOnProductComponentAddToCartButtonByName("Apple Cinema 30");
        softAssert.assertEquals(productPage.getProductName(), "Apple Cinema 30");

        productPage.getAvailableOptionsComponent().setOptionsForAppleCinema("Radio", "Small",
                "Checkbox", "Checkbox 2",
                "Select", "Red (+$4.00)");
        ProductPage productPageAfterAddToCart = productPage.goToProductPageAfterAddToCart(3);
        Assert.assertTrue(productPageAfterAddToCart.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added Apple Cinema 30 to your shopping cart!"));

        //TODO
        Thread.sleep(1000);
        CartPage cartPage = productPageAfterAddToCart.goToShoppingCartFromAlert();
        softAssert.assertTrue(cartPage.getPageTitleText().contains("Shopping Cart"));

        cartPage.checkRefreshFunction("Apple Cinema 30", 1);
        softAssert.assertTrue(cartPage.getSuccessModifyProductInCartAlert().getAlertMessageText().contains("Success: You have modified your shopping cart!"));
        softAssert.assertTrue(cartPage.getUnsuccessModifyProductInCartAlert().getAlertMessageText().contains("Minimum order amount for Apple Cinema 30 is 2!"));

        softAssert.assertAll();
        Thread.sleep(2000);//Only for presentation
    }

    @Test(priority = 10)
    public void checkUncorrectFunctionOfRefreshButtonInCart2() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        CartPage cartPage = addToCartIPhoneSearchPage.goToCartPageByLinkInHeader();
        softAssert.assertTrue(cartPage.getPageTitleText().contains("Shopping Cart"));

        cartPage.checkRefreshFunction("iPhone", 10000);
        softAssert.assertTrue(cartPage.getSuccessModifyProductInCartAlert().getAlertMessageText().contains("Success: You have modified your shopping cart!"));
        softAssert.assertTrue(cartPage.getUnsuccessModifyProductInCartAlert().getAlertMessageText().contains("Products marked with *** are not available in the desired quantity or not in stock!"));

        softAssert.assertAll();
    }

    //remove in cart page
    @Test(priority = 11)
    public void checkRemoveButtonInCart() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        SearchPage cinemaSearchPage = addToCartIPhoneSearchPage.goToSearchPageAfterSearchProduct("cinema");
        //Assert TODO -> SearchCriteriaComponent

        ProductPage productPage = (ProductPage) cinemaSearchPage.afterClickOnProductComponentAddToCartButtonByName("Apple Cinema 30");
        softAssert.assertEquals(productPage.getProductName(), "Apple Cinema 30");

        productPage.getAvailableOptionsComponent().setOptionsForAppleCinema("Radio", "Small",
                "Checkbox", "Checkbox 2",
                "Select", "Red (+$4.00)");
        ProductPage productPageAfterAddToCart = productPage.goToProductPageAfterAddToCart(1);
        softAssert.assertTrue(productPageAfterAddToCart.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added Apple Cinema 30 to your shopping cart!"));

        CartPage cartPage = productPageAfterAddToCart.goToCartPageByLinkInHeader();
        softAssert.assertTrue(cartPage.getPageTitleText().contains("Shopping Cart"));

        CartPage cartPageAfterDeleteIPhone = cartPage.goToCartPageAfterRemoveProductByName("iPhone");
        softAssert.assertTrue(cartPageAfterDeleteIPhone.checkIsTheProductInCartComponentByName("iPhone"));

        softAssert.assertAll();
    }

    @Test(priority = 12)
    public void checkRemoveProductByRefreshButtonInCart() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        CartPage cartPage = addToCartIPhoneSearchPage.goToCartPageByLinkInHeader();
        softAssert.assertTrue(cartPage.getPageTitleText().contains("Shopping Cart"));

        cartPage.checkRefreshFunction("iPhone", 0);
        softAssert.assertTrue(cartPage.getEmptyCartText().equals("Your shopping cart is empty!"));

        softAssert.assertAll();
    }

    //Check buttons in cart Button
    @Test(priority = 13)
    public void checkRemoveFunctionInCartButton() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        SearchPage ipSearchPage = getHomePage().goToSearchPageAfterSearchProduct("ip");
        //Assert TODO -> SearchCriteriaComponent

        SearchPage addToCartIPhoneSearchPage = (SearchPage) ipSearchPage.afterClickOnProductComponentAddToCartButtonByName("iPhone");
        softAssert.assertTrue(addToCartIPhoneSearchPage.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added iPhone to your shopping cart!"));

        SearchPage cinemaSearchPage = addToCartIPhoneSearchPage.goToSearchPageAfterSearchProduct("cinema");
        //Assert TODO -> SearchCriteriaComponent

        ProductPage productPage = (ProductPage) cinemaSearchPage.afterClickOnProductComponentAddToCartButtonByName("Apple Cinema 30");
        softAssert.assertEquals(productPage.getProductName(), "Apple Cinema 30");

        productPage.getAvailableOptionsComponent().setOptionsForAppleCinema("Radio", "Small",
                "Checkbox", "Checkbox 2",
                "Select", "Red (+$4.00)");
        ProductPage productPageAfterAddToCart = productPage.goToProductPageAfterAddToCart(3);
        softAssert.assertTrue(productPageAfterAddToCart.getSearchPageAlertComponent().getAlertMessageText().contains("Success: You have added Apple Cinema 30 to your shopping cart!"));

        productPageAfterAddToCart.removeViewProductComponentByName("iPhone");
        //softAssert.assertTrue(productPageAfterAddToCart.checkIsTheProductInCartComponentByName("iPhone"));

        productPageAfterAddToCart.removeViewProductComponentByName("Apple Cinema 30");
        //softAssert.assertTrue(productPageAfterAddToCart.getEmptyDropdownCartButtonText().contains("Your shopping cart is empty!"));

        softAssert.assertAll();
    }

    //Check text of button

}
