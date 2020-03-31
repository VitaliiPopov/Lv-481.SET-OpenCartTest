package test.cart_tests.functionality.button_cart.check_total_sum;

import com.opencart.data.products.ProductWithoutOptions;
import com.opencart.data.products.ProductsWithoutOptionsRepository;
import com.opencart.pages.HomePage;
import com.opencart.tools.Driver;
import com.opencart.tools.cart_test_ranner.AbstractCartTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CorrectTotalSumCheck extends AbstractCartTestRunner {

    @Test
    public void checkTotalSum() throws InterruptedException {

        HomePage homePage = getHomePage();

        initCart();

        Thread.sleep(1000);

        System.out.println(homePage.checkTotalPrice());

    }

    private void initCart() {
        List<ProductWithoutOptions> productWithoutOptions = ProductsWithoutOptionsRepository.productWithoutOptions();
        List<ProductWithoutOptions> temp = new ArrayList<>();
        temp.addAll(productWithoutOptions);
        int qty2 = (int) (Math.random() * temp.size());
        for (int i = 0; i < qty2; i++) {
            ProductWithoutOptions current = temp.get((int) (Math.random() * (qty2 - i)));
            temp.remove(current);
            ((JavascriptExecutor) Driver.getDriver()).executeScript("document.onclick=cart.add('" + current.getId() + "', '"+ Math.random() * 100 +"');");
        }
    }
}
