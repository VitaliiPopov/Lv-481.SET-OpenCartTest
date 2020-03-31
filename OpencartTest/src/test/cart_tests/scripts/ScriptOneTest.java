package test.cart_tests.scripts;

import com.opencart.pages.cart.CartPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ScriptOneTest extends AbstractCartTestRunner {

    JsonDataConfig jsonParser = new JsonDataConfig("TestData.json");

    @BeforeMethod
    @Parameters({"productNameOfIphone"})
    public void setUp(String productNameOfIphone) {
        getLoginPage()
                .login(jsonParser.getEmailFromJson(7), jsonParser.getPasswordFromJson(7))
                .goToHomePage()
                .clickProductComponentAddToCartButtonByName(productNameOfIphone);
        getLogoutPage();
    }

    @AfterMethod
    public void tearDown() {
        getCartPage()
                .removeAllProductsFromCartPage();
        getLogoutPage();
    }

    @Test
    @Parameters({"cinemaSearchRequest", "productNameOfCinema", "radioButtonOptionName",
            "checkBoxOptionName", "selectOptionsOptionName", "alertMessageCinema",
            "productNameOfIphone", "myAccountDropdownText"})
    public void scriptCheck(String cinemaSearchRequest, String productNameOfCinema,
                            String radioButtonOptionName, String checkBoxOptionName,
                            String  selectOptionsOptionName, String alertMessageCinema,
                            String productNameOfIphone, String myAccountDropdownText) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        ProductPage appleCinemaPage = ((ProductPage) getHomePage()
                .goToSearchPageAfterSearchProduct(cinemaSearchRequest)
                .afterClickOnProductComponentAddToCartButtonByName(productNameOfCinema))
                .addToCartProductWitOptions(radioButtonOptionName, checkBoxOptionName,
                        selectOptionsOptionName, 5);

        softAssert.assertTrue(appleCinemaPage
                .getAlertComponentWithWait()
                .getAlertMessageText()
                .contains(alertMessageCinema));

        CartPage cartPageGuestUser = appleCinemaPage
                .goToShoppingCartFromAlert()
                .checkRefreshFunction(productNameOfCinema, 3);

        softAssert.assertTrue(cartPageGuestUser
                .checkTotalPrice());

        CartPage cartPageLoginUser = cartPageGuestUser.goToLoginPage(myAccountDropdownText)
                .login(jsonParser.getEmailFromJson(7), jsonParser.getPasswordFromJson(7))
                .goToCartPageByLinkInHeader();

        softAssert.assertTrue(cartPageLoginUser
                .checkIsTheProductInCartComponentByName(productNameOfCinema));
        softAssert.assertTrue(cartPageLoginUser
                .checkIsTheProductInCartComponentByName(productNameOfIphone));
        softAssert.assertTrue(cartPageLoginUser.getProductInCartContainerComponentsSize() == 2);

        softAssert.assertAll();
    }
}
