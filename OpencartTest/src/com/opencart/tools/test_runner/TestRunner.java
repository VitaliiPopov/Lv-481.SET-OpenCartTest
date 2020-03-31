package com.opencart.tools.test_runner;

import com.opencart.pages.HomePage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class TestRunner {

    @BeforeClass
    public void beforeClass() {

        Driver.getDriver();
    }

    @AfterClass
    public void afterClass() {
        Driver.quit();
    }

    public HomePage getHomePage() {
        return new HomePage(Driver.getDriver());
    }

    public CartPage getCartPage() {
        return new CartPage(Driver.getDriver());
    }

    public WishListPage getWishListPage() {
        return new WishListPage(Driver.getDriver());
    }
}