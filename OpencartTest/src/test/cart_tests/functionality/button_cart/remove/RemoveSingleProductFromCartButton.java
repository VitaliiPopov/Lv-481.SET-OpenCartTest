package test.cart_tests.functionality.button_cart.remove;

import com.opencart.pages.HomePage;
import com.opencart.tools.Driver;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RemoveSingleProductFromCartButton extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfIphone", "emptyCartPageText", "emptyCartButtonText"})
    public void removeSingleProductFromCartButton(String productNameOfIphone,
                                                  String emptyCartPageText,
                                                  String emptyCartButtonText) throws InterruptedException {
        HomePage homePage = getHomePage();

        addToCart();

        Thread.sleep(2000);

        homePage.removeViewProductComponentByName(productNameOfIphone);

        Thread.sleep(2000);

        Assert.assertEquals(homePage.getEmptyDropdownCartButtonText(), emptyCartPageText);
        Assert.assertEquals(homePage.getCartButtonText(), emptyCartButtonText);
    }

    private void addToCart(){
        if (Driver.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor)Driver.getDriver()).
                    executeScript("document.onclick=cart.add('40');");
        }
    }
}
