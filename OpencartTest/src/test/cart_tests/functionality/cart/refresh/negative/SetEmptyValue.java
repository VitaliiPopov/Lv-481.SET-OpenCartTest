package test.cart_tests.functionality.cart.refresh.negative;

import com.opencart.pages.cart.CartPage;
import com.opencart.tools.Driver;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SetEmptyValue extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfIphone", "alertUnsuccessMessage"})
    public void checkCorrectFunctionOfRefreshButtonInCart(String productNameOfIphone,
                                                          String alertUnsuccessMessage) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        getCartPage();

        addToCart();

        Thread.sleep(2000);

        CartPage cartPage = getCartPage();

        Thread.sleep(2000);

        cartPage.checkRefreshFunctionWithEmptyQty(productNameOfIphone);

        softAssert.assertTrue(cartPage.getUnsuccessModifyProductInCartAlert().getAlertMessageText().contains(alertUnsuccessMessage));
        softAssert.assertTrue(cartPage.checkPriceOfProduct(productNameOfIphone));

        softAssert.assertAll();
    }

    private void addToCart(){
        if (Driver.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor)Driver.getDriver()).
                    executeScript("document.onclick=cart.add('40');");
        }
    }
}
