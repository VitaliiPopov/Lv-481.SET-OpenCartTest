package test.cart_tests.functionality.cart.remove;

import com.opencart.pages.cart.CartPage;
import com.opencart.tools.Driver;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RemoveSingleProductFromCartPage extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfIphone", "emptyCartPageText", "successModifiedCartText"})
    public void removeSingleProductFromCart(String productNameOfIphone, String emptyCartPageText,
                                            String successModifiedCartText) throws InterruptedException {
        getCartPage();

        addToCart();

        Thread.sleep(2000);// Only for presentation

        CartPage cartPage2 = getCartPage();

        cartPage2.removeProductByName(productNameOfIphone);

        Assert.assertEquals(cartPage2.getEmptyCartText(), emptyCartPageText);

        Assert.assertEquals(cartPage2.getSuccessModifyProductInCartAlert().getAlertMessageText(), successModifiedCartText);
    }

    private void addToCart(){
        if (Driver.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor)Driver.getDriver()).
                    executeScript("document.onclick=cart.add('40');");
        }
    }
}
