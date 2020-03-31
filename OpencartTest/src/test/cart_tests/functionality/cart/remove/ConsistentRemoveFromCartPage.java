package test.cart_tests.functionality.cart.remove;

import com.opencart.data.products.ProductWithoutOptions;
import com.opencart.data.products.ProductsWithoutOptionsRepository;
import com.opencart.pages.cart.CartPage;
import com.opencart.tools.Driver;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsistentRemoveFromCartPage extends AbstractCartTestRunner {

    @Test
    @Parameters({"emptyCartPageText"})
    public void consistentRemoveFromCartPage(String emptyCartPageText) throws InterruptedException {
        getCartPage();

        initCart();

        Thread.sleep(2000);

        CartPage cartPage = getCartPage();

        cartPage.removeAllProductsFromCartPage();

        Assert.assertEquals(cartPage.getEmptyCartText(), emptyCartPageText);
    }

    private void initCart() {
        List<ProductWithoutOptions> productWithoutOptions = ProductsWithoutOptionsRepository.productWithoutOptions();
        List<ProductWithoutOptions> temp = new ArrayList<>();
        temp.addAll(productWithoutOptions);
        int qty2 = (int) (Math.random() * temp.size());
        for (int i = 0; i < qty2; i++) {
            ProductWithoutOptions current = temp.get((int) (Math.random() * (qty2 - i)));
            temp.remove(current);
            ((JavascriptExecutor) Driver.getDriver()).executeScript("document.onclick=cart.add('" + current.getId() + "', '"+ Math.random() * 10 +"');");
        }
    }
}
