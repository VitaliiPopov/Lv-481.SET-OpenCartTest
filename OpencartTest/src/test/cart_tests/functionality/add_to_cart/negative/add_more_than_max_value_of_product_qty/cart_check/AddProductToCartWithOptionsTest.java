package test.cart_tests.functionality.add_to_cart.negative.add_more_than_max_value_of_product_qty.cart_check;

import com.opencart.pages.cart.CartPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfCinema", "radioButtonOptionName",
            "checkBoxOptionName", "selectOptionsOptionName", "emptyCartPageText"})
    public void checkAddProductWithOptionsToCartWithMaxIntValueQty(String productNameOfCinema, String radioButtonOptionName,
                                                                   String checkBoxOptionName, String selectOptionsOptionName,
                                                                   String emptyCartPageText) {
        CartPage cartPage = ((ProductPage) getHomePage()
                .clickProductComponentAddToCartButtonByName(productNameOfCinema))
                .addToCartProductWitOptions(radioButtonOptionName, checkBoxOptionName,
                        selectOptionsOptionName, Integer.MAX_VALUE)
                .goToCartPageByLinkInHeader();

        Assert.assertEquals(cartPage.getEmptyCartText(), emptyCartPageText);
    }

}
