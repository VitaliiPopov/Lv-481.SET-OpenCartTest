package com.opencart.tools;


import com.opencart.pages.HomePage;
import com.opencart.pages.account.AddressBookPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.wishlist.WishListPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;

public class TestRunnerYura {

    @BeforeClass
    public void beforeClass() {
        Driver.getDriver();
    }

    @AfterClass
    public void afterClass() {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage.deleteAll();
        Driver.quit();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
      /*  if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }*/
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