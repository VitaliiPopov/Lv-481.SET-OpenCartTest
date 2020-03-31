package test.cart_tests.functionality.add_to_cart.negative.add_more_than_max_value_of_product_qty.alert_check;

import com.opencart.pages.product.ProductPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithoutOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfIphone", "alertUnsuccessMessage"})
    public void checkAddProductWithOptionsToCartWithMaxIntValueQty(String productNameOfIphone,
                                                                String alertUnsuccessMessage){
        ProductPage productPage = getHomePage()
                .clickOnPictureOfProductComponentByName(productNameOfIphone)
                .addToCartProductWithoutOptions(Integer.MAX_VALUE);

        Assert.assertTrue(productPage.getAlertComponentWithWait().getAlertMessageText().contains(alertUnsuccessMessage));

    }
}
