package test.cart_tests.functionality.add_to_cart.negative.add_zero_value_of_product_qty.alert_check;

import com.opencart.pages.product.ProductPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfCinema", "radioButtonOptionName",
            "checkBoxOptionName", "selectOptionsOptionName", "alertUnsuccessMessage"})
    public void checkAddProductWithOptionsToCartWithZeroValueQty(String productNameOfCinema, String radioButtonOptionName,
                                                                   String checkBoxOptionName, String selectOptionsOptionName,
                                                                   String alertUnsuccessMessage) {
        ProductPage cinemaPage = ((ProductPage) getHomePage()
                .clickProductComponentAddToCartButtonByName(productNameOfCinema))
                .addToCartProductWitOptions(radioButtonOptionName, checkBoxOptionName,
                        selectOptionsOptionName, 0);

        Assert.assertTrue(cinemaPage.getAlertComponentWithWait().getAlertMessageText().contains(alertUnsuccessMessage));
    }

}
