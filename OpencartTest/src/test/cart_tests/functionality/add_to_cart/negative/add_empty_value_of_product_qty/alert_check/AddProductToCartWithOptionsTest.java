package test.cart_tests.functionality.add_to_cart.negative.add_empty_value_of_product_qty.alert_check;

import com.opencart.pages.product.ProductPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfCinema", "radioButtonOptionName",
            "checkBoxOptionName", "selectOptionsOptionName", "alertUnsuccessMessage"})
    public void checkAddProductWithOptionsToCartWithEmptyQty(String productNameOfCinema, String radioButtonOptionName,
                                            String checkBoxOptionName, String selectOptionsOptionName,
                                            String alertUnsuccessMessage) {
        ProductPage cinemaPage = ((ProductPage) getHomePage()
                .clickProductComponentAddToCartButtonByName(productNameOfCinema))
                .addToCartEmptyProductWitOptions(radioButtonOptionName,
                        checkBoxOptionName, selectOptionsOptionName);

        Assert.assertTrue(cinemaPage.getAlertComponentWithWait().getAlertMessageText().contains(alertUnsuccessMessage));
    }

}
