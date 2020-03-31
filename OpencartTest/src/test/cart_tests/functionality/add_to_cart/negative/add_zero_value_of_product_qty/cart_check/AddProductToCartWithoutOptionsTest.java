package test.cart_tests.functionality.add_to_cart.negative.add_zero_value_of_product_qty.cart_check;

import com.opencart.pages.cart.CartPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithoutOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfIphone", "emptyCartPageText"})
    public void checkAddProductWithOptionsToCartWithZeroValueQty(String productNameOfIphone,
                                                                   String emptyCartPageText){
        CartPage cartPage = getHomePage()
                .clickOnPictureOfProductComponentByName(productNameOfIphone)
                .addToCartProductWithoutOptions(0)
                .goToCartPageByLinkInHeader();

        Assert.assertEquals(cartPage.getEmptyCartText(), emptyCartPageText);
    }
}
