package com.opencart.tools;

import com.opencart.pages.HomePage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.wishlist.WishListPage;
import org.testng.ITestResult;
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

//    @AfterMethod(alwaysRun = true)
//    public void afterMethod() {
//        Driver.ClearCookies();
//    }

//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            //Utility.getScreenshot(Driver.getDriver());
//            Driver.getDriver().get("https://137.116.222.54/index.php?route=account/logout");
//        }
//    }

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