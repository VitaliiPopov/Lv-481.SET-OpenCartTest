package test.cart_tests.functionality.cart.refresh.positive;

import com.opencart.pages.cart.CartPage;
import com.opencart.tools.Driver;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckCorrectFunctionOfRefreshButtonInCart extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfIphone", "successModifiedCartText"})
    public void checkCorrectFunctionOfRefreshButtonInCart(String productNameOfIphone,
                                                          String successModifiedCartText) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        getCartPage();

        addToCart();

        Thread.sleep(2000);

        CartPage cartPage = getCartPage();

        Thread.sleep(2000);

        cartPage.checkRefreshFunction(productNameOfIphone, 10);

        softAssert.assertTrue(cartPage.getSuccessModifyProductInCartAlert().getAlertMessageText().contains(successModifiedCartText));
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
