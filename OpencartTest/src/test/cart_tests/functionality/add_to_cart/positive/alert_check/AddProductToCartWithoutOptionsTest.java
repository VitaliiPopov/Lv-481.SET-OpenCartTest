package test.cart_tests.functionality.add_to_cart.positive.alert_check;

import com.opencart.pages.search.SearchPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithoutOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"ipSearchRequest", "productNameOfIphone", "alertMessageIphone"})
    public void checkAddProductWithoutOptionsToCart(String ipSearchRequest,
                                                    String productNameOfIphone,
                                                    String alertMessageIphone) {
        SearchPage searchPage = (SearchPage) getHomePage()
                .goToSearchPageAfterSearchProduct(ipSearchRequest)
                .afterClickOnProductComponentAddToCartButtonByName(productNameOfIphone);

        Assert.assertTrue(searchPage.getAlertComponentWithWait().getAlertMessageText().contains(alertMessageIphone));
    }
}
