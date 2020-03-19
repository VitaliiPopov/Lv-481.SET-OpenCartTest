package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.product_table.WishListPage;
import com.opencart.tools.TestRunner;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestWishList extends TestRunner {
    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void addToCartFromWishList(String myAccountDropdownText) throws InterruptedException {
        //
        //Precondition => product must be already in wishList
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login("yellow@yellow.com","yellowyellow");
        HomePage homePage = myAccountPage.goToHomePage();
        //
        //Steps
        WishListPage wishListPage = homePage.goToWishList();
        wishListPage = wishListPage.putFromWishListToCartProductByPartialName("MacBook");

    }
}
