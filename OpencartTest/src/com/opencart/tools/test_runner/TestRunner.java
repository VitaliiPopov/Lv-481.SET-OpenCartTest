package com.opencart.tools.test_runner;

import com.opencart.pages.HomePage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.Instance;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestRunner {

    @BeforeClass
    public void beforeClass() {

        Instance.getDriver();
    }

    @AfterClass
    public void afterClass() {
        Instance.quit();
    }

    public HomePage getHomePage() {
        return new HomePage(Instance.getDriver());
    }

    public CartPage getCartPage() {
        return new CartPage(Instance.getDriver());
    }

    public WishListPage getWishListPage() {
        return new WishListPage(Instance.getDriver());
    }
}