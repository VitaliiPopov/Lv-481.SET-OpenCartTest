package test.cart_tests.functionality.add_to_cart.negative.add_not_available_product.alert_check;

import com.opencart.pages.search.SearchPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithoutOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"samsungSearchRequest", "productNameOfSamsung", "alertUnsuccessMessage"})
    public void checkAddNotAvailableProductToCart(String samsungSearchRequest,
                                                                   String productNameOfSamsung,
                                                                   String alertUnsuccessMessage){
        SearchPage searchPage = (SearchPage) getHomePage()
                .goToSearchPageAfterSearchProduct(samsungSearchRequest)
                .afterClickOnProductComponentAddToCartButtonByName(productNameOfSamsung);

        Assert.assertTrue(searchPage.getAlertComponentWithWait().getAlertMessageText().contains(alertUnsuccessMessage));
    }
}
