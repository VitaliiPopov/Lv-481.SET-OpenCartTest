package com.opencart.tools.test_runner;


import com.opencart.pages.HomePage;
import com.opencart.pages.account.AddressBookPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.Instance;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;

public class AddressBookTestRunner {

    @BeforeClass
    public void beforeClass() {
        Instance.getDriver();
    }

    @AfterClass
    public void afterClass() {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage.deleteAll();
        Instance.quit();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
      /*  if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }*/
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