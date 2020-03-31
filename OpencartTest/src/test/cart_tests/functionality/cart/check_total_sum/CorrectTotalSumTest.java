package test.cart_tests.functionality.cart.check_total_sum;

import com.opencart.data.products.ProductWithoutOptions;
import com.opencart.data.products.ProductsWithoutOptionsRepository;
import com.opencart.pages.cart.CartPage;
import com.opencart.tools.Driver;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CorrectTotalSumTest extends AbstractCartTestRunner {

    @Test
    public void checkCorrectTotalSumCheck() throws InterruptedException {
        getCartPage();

        initCart();

        Thread.sleep(2000);

        CartPage cartPage = getCartPage();

        Assert.assertTrue(cartPage.checkTotalPrice());
    }

    private void initCart() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        List<ProductWithoutOptions> productWithoutOptions = ProductsWithoutOptionsRepository.productWithoutOptions();
        List<ProductWithoutOptions> temp = new ArrayList<>();
        temp.addAll(productWithoutOptions);
        int qty2 = (int) (Math.random() * temp.size());
        for (int i = 0; i < qty2; i++) {
            ProductWithoutOptions current = temp.get((int) (Math.random() * (qty2 - i)));
            temp.remove(current);
            js.executeScript("document.onclick=cart.add('" + current.getId() + "', '"+ Math.random() * 10 +"');");
        }
    }
}
