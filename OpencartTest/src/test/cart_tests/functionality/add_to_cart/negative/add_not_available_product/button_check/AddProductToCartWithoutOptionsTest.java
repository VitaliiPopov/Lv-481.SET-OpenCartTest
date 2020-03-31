package test.cart_tests.functionality.add_to_cart.negative.add_not_available_product.button_check;

import com.opencart.pages.search.SearchPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithoutOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"samsungSearchRequest", "productNameOfSamsung", "emptyCartButtonText"})
    public void checkAddNotAvailableProductToCart(String samsungSearchRequest,
                                                                   String productNameOfSamsung,
                                                                   String emptyCartButtonText){
        SearchPage searchPage = (SearchPage) getHomePage()
                .goToSearchPageAfterSearchProduct(samsungSearchRequest)
                .afterClickOnProductComponentAddToCartButtonByName(productNameOfSamsung);

        Assert.assertEquals(searchPage.getCartButtonText(), emptyCartButtonText);
    }
}
