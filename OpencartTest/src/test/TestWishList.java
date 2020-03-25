package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.*;
import com.opencart.pages.product_table.WishListEmptyPage;
import com.opencart.pages.product_table.WishListPage;
import com.opencart.pages.search.*;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestWishList extends TestRunner {

    JsonDataConfig jsonParser = new JsonDataConfig("TestData.json");


    @Parameters({"myAccountDropdownText","fromSearchPageProductName"})
    @Test(priority = 1)
    public void addProductToWishListFromSearchPage(String myAccountDropdownText, String fromSearchPageProductName) {
        // Preconditions
        //      Login to system
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(7),jsonParser.getPasswordFromJson(7));
        myAccountPage.goToHomePage();

        int numberProductsInWishList = getHomePage().getWishListNumberOfProducts();

        // Steps
        SearchPage searchPage = getHomePage().searchProduct(fromSearchPageProductName);

        searchPage.clickProductComponentAddToWishList(fromSearchPageProductName);

        searchPage.goToHomePage();
        //Actual Result
        int actualNumberOfProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList,numberProductsInWishList + 1);

        //  remove all products from WishList
        WishListPage wishListPage = getHomePage().goToWishList();
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();

    }


    @Parameters({"myAccountDropdownText","fromSearchPageProductName","fromHomePageProductName"})
    @Test(priority = 2)
    public void removeProductFromWishListByName(String myAccountDropdownText,String fromSearchPageProductName,
                                                String fromHomePageProductName){
        // Preconditions
        //      Login to system
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(7),jsonParser.getPasswordFromJson(7));
        myAccountPage.goToHomePage();
        //      Add some product to WishList
        SearchPage searchPage = getHomePage().searchProduct(fromSearchPageProductName);
        searchPage.clickProductComponentAddToWishList(fromSearchPageProductName);
        searchPage = searchPage.searchProduct(fromHomePageProductName);
        searchPage.clickProductComponentAddToWishList(fromHomePageProductName);

        int numberProductsInWishList = getHomePage().getWishListNumberOfProducts();
        System.out.println("before remove " + numberProductsInWishList);
        //Steps
        // Go to WishListPage
        WishListPage wishListPage = getHomePage().goToWishList();
        // Remove product from WishListPage by Product Name
        wishListPage.removeFromWishListProductByPartialName(fromSearchPageProductName);
        HomePage homePage = getHomePage();
        //Actual Result
        int actualNumberOfProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking removeProductFromWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList,numberProductsInWishList - 1);

        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage  = getHomePage().goToWishList().removeAllProductsFromWishList();
        homePage = wishListEmptyPage.goToHomePage();
        // Returning to the starter view -> then must be logOut
    }
}
