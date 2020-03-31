package test.cart_tests.functionality.add_to_cart.positive.alert_check;

import com.opencart.pages.product.ProductPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"productNameOfCinema", "radioButtonOptionName",
            "checkBoxOptionName", "selectOptionsOptionName", "alertMessageCinema"})
    public void checkAddProductWithOptionsToCart(String productNameOfCinema, String radioButtonOptionName,
                                                                 String checkBoxOptionName, String selectOptionsOptionName,
                                                                 String alertMessageCinema) {
        ProductPage cinemaPage = ((ProductPage) getHomePage()
                .clickProductComponentAddToCartButtonByName(productNameOfCinema))
                .addToCartProductWitOptions(radioButtonOptionName, checkBoxOptionName,
                        selectOptionsOptionName, 2);

        Assert.assertTrue(cinemaPage.getAlertComponentWithWait().getAlertMessageText().contains(alertMessageCinema));
    }
}
