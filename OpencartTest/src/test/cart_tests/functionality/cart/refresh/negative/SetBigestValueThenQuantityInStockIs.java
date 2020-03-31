package test.cart_tests.functionality.cart.refresh.negative;

import com.opencart.pages.cart.CartPage;
import com.opencart.tools.Driver;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SetBigestValueThenQuantityInStockIs extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfIphone", "successModifiedCartText", "unsuccessModifiedCartText"})
    public void checkCorrectFunctionOfRefreshButtonInCart(String productNameOfIphone,
                                                          String successModifiedCartText,
                                                          String unsuccessModifiedCartText) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        getCartPage();

        addToCart();

        Thread.sleep(2000);

        CartPage cartPage = getCartPage();

        Thread.sleep(2000);

        cartPage.checkRefreshFunction(productNameOfIphone, Integer.MAX_VALUE);

        softAssert.assertTrue(cartPage
                .getSuccessModifyProductInCartAlert()
                .getAlertMessageText()
                .contains(successModifiedCartText));
        softAssert.assertEquals(cartPage
                .getUnsuccessModifyProductInCartAlert()
                .getAlertMessageText(),
                unsuccessModifiedCartText);
        softAssert.assertEquals(cartPage
                .getProductInCartComponentByName(productNameOfIphone)
                .getQuantityInputFildText(),
                String.valueOf(Integer.MAX_VALUE));
        softAssert.assertTrue(cartPage
                .checkPriceOfProduct(productNameOfIphone));

        softAssert.assertAll();
    }

    private void addToCart(){
        if (Driver.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor)Driver.getDriver()).
                    executeScript("document.onclick=cart.add('40');");
        }
    }
}

