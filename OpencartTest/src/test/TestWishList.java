package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.pages.wishlist.WishListEmptyPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestWishList extends TestRunner {

    JsonDataConfig jsonParser = new JsonDataConfig("TestData.json");



    @Test(priority = 1)
    @Parameters({"myAccountDropdownText","fromSearchPageProductName"})
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
        // POST CONDITION
        //  remove all products from WishList
        WishListPage wishListPage = getHomePage().goToWishList();
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();

    }

    @Test
    @Parameters({"myAccountDropdownText","fromSearchPageProductName","fromHomePageProductName"})
    public void addProductToWishListFromHomePage(String myAccountDropdownText,String fromSearchPageProductName,
                                                 String fromHomePageProductName){
        // Preconditions
        //      Login to system
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(7),jsonParser.getPasswordFromJson(7));
        myAccountPage.goToHomePage();

        int numberProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Steps
        HomePage homePage = getHomePage().clickProductComponentAddToWishListButtonByName(fromHomePageProductName);
        homePage.goToWishList();

        int actualNumberOfProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList,numberProductsInWishList + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListPage wishListPage = getHomePage().goToWishList();
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();

    }

    @Test
    @Parameters({"myAccountDropdownText","fromSearchPageProductName","fromHomePageProductName"})
    public void addProductToWishListFromProductPage(String myAccountDropdownText,String fromSearchPageProductName,
                                                 String fromHomePageProductName){
        // Preconditions
        //      Login to system
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(7),jsonParser.getPasswordFromJson(7));
        myAccountPage.goToHomePage();

        int numberProductsInWishList = getHomePage().getWishListNumberOfProducts();

        // Steps
        HomePage homePage = getHomePage();
        ProductPage productPage = homePage.clickOnProductNameLabel(fromHomePageProductName);
        WishListPage wishListPage = productPage.addProductToWishList();

        int actualNumberOfProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList,numberProductsInWishList + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage = getHomePage().goToWishList().removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();


    }

    @Test(priority = 2)
    @Parameters({"myAccountDropdownText","fromSearchPageProductName","fromHomePageProductName"})
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
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage  = getHomePage().goToWishList().removeAllProductsFromWishList();
        homePage = wishListEmptyPage.goToHomePage();
        // Returning to the starter view -> then must be logOut
    }


    @Test
    @Parameters({"myAccountDropdownText","fromSearchPageProductName","fromHomePageProductName"})
    public void addProductToCartFromWishList(String myAccountDropdownText,String fromSearchPageProductName,
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

        int numberOfProductsInCart = getHomePage().getNumberOfProductsInCartButton();

        //Steps
        // Go to WishListPage
        WishListPage wishListPage = getHomePage().goToWishList();
        // Add product to cart from Wish List
        wishListPage.putFromWishListToCartProductByPartialName(fromHomePageProductName);

        int actual_numberOfProductsInCart = getHomePage().getNumberOfProductsInCartButton();

        //Check the quantity of products in CartButon after adding product to it from Wish List
        Assert.assertEquals(actual_numberOfProductsInCart,numberOfProductsInCart+1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage  = getHomePage().goToWishList().removeAllProductsFromWishList();
        wishListEmptyPage.goToHomePage();
        // Returning to the starter view -> then must be logOut

    }

    @Test
    @Parameters({"myAccountDropdownText","fromSearchPageProductName","fromHomePageProductName"})
    public void verifyWishListEmptyPageMessage(String myAccountDropdownText,String fromSearchPageProductName,
                                               String fromHomePageProductName){
        // Preconditions
        //      Login to system
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(7),jsonParser.getPasswordFromJson(7));
        myAccountPage.goToHomePage();

        String message = getHomePage().goToWishListEmpty().getLabelText();

        Assert.assertEquals(message,"Your wish list is empty.");

    }
}
