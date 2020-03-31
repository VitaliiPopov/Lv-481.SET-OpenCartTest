package com.opencart.tools.cart_test_ranner;

import com.opencart.data.ConstantVariables;
import com.opencart.pages.HomePage;
import com.opencart.pages.account.AccountLogoutPage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.cart.CartPage;
import com.opencart.tools.Driver;
import org.testng.annotations.AfterClass;

public class AbstractCartTestRunner {

    @AfterClass
    public void afterClass() {
        Driver.quit();
    }

    public HomePage getHomePage() {
        Driver.getDriver().get(ConstantVariables.URL);
        return new HomePage(Driver.getDriver());
    }

    public CartPage getCartPage() {
        Driver.getDriver().get(ConstantVariables.CART_PAGE_URL);
        return new CartPage(Driver.getDriver());
    }

    public LoginPage getLoginPage() {
        Driver.getDriver().get("https://52.232.108.109:443/index.php?route=account/account");
        return new LoginPage(Driver.getDriver());
    }

    public AccountLogoutPage getLogoutPage() {
        Driver.getDriver().get("https://52.232.108.109:443/index.php?route=account/logout");
        return new AccountLogoutPage(Driver.getDriver());
    }

}