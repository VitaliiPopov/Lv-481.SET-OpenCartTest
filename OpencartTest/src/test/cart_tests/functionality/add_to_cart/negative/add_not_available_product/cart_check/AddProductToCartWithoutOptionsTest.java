package test.cart_tests.functionality.add_to_cart.negative.add_not_available_product.cart_check;

import com.opencart.pages.cart.CartPage;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddProductToCartWithoutOptionsTest extends AbstractCartTestRunner {

    @Test
    @Parameters({"samsungSearchRequest", "productNameOfSamsung", "emptyCartPageText"})
    public void checkAddNotAvailableProductToCart(String samsungSearchRequest,
                                                                   String productNameOfSamsung,
                                                                   String emptyCartPageText){
        CartPage cartPage = getHomePage()
                .goToSearchPageAfterSearchProduct(samsungSearchRequest)
                .afterClickOnProductComponentAddToCartButtonByName(productNameOfSamsung)
                .goToCartPageByLinkInHeader();

        Assert.assertEquals(cartPage.getEmptyCartText(), emptyCartPageText);
    }

}
