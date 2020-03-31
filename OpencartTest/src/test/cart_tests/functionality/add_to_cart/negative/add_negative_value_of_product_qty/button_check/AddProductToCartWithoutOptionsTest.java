package test.cart_tests.functionality.add_to_cart.negative.add_negative_value_of_product_qty.button_check;

import com.opencart.pages.product.ProductPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithoutOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfIphone", "emptyCartButtonText"})
    public void checkAddProductWithOptionsToCartWithMinIntValueQty(String productNameOfIphone,
                                                                   String emptyCartButtonText){
        ProductPage productPage = getHomePage()
                .clickOnPictureOfProductComponentByName(productNameOfIphone)
                .addToCartProductWithoutOptions((int) (Math.random() * (0 - Integer.MIN_VALUE)));

        Assert.assertEquals(productPage.getCartButtonText(), emptyCartButtonText);
    }
}
