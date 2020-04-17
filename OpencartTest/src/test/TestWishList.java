package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.product.ProductPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.pages.wishlist.WishListEmptyPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.test_runner.WishListTestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestWishList extends WishListTestRunner {

    @Test(priority = 1,
            description = "check the possibility of adding product from SearchPage to WishList")
    @Parameters({"fromSearchPageProductName"})
    public void addProductToWishListFromSearchPage(String fromSearchPageProductName) {
        // Steps
        SearchPage searchPage = getHomePage().searchProduct(fromSearchPageProductName);

        // Get current number of products in WishList
        int numberProductsInWishList = searchPage.getWishListNumberOfProducts();

        searchPage.clickProductComponentAddToWishList(fromSearchPageProductName);

        HomePage homePage = searchPage.goToHomePage();
        //Actual Result
        int actualNumberOfProductsInWishList = homePage.getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList, numberProductsInWishList + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListPage wishListPage = homePage.goToWishList();
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();

    }

    @Test(priority = 2,
            description = "check the possibility of adding product from HomePage to WishList")
    @Parameters({"fromHomePageProductName"})
    public void addProductToWishListFromHomePage(String fromHomePageProductName) {
        //Steps
        HomePage homePage = getHomePage().goToHomePage();

        // Get current number of products in WishList
        int numberProductsInWishList = homePage.getWishListNumberOfProducts();

        homePage.clickProductComponentAddToWishListButtonByName(fromHomePageProductName);

        int actualNumberOfProductsInWishList = homePage.getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList, numberProductsInWishList + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage = homePage.goToWishList().removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();

    }

    @Test(priority = 3,
            description = "check the possibility of adding product from ProductPage to WishList")
    @Parameters({"fromProductPageProductName"})
    public void addProductToWishListFromProductPage(String fromProductPageProductName) {


        // Steps
        HomePage homePage = getHomePage().goToHomePage();

        // Get current number of products in WishList
        int numberProductsInWishList = homePage.getWishListNumberOfProducts();

        ProductPage productPage = homePage.clickOnProductNameLabel(fromProductPageProductName);
        productPage.addProductToWishList();

        WishListPage wishListPage = productPage.goToWishList();

        int actualNumberOfProductsInWishList = wishListPage.getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList, numberProductsInWishList + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();
    }

    @Test(priority = 4,
            description = "check the possibility of adding product to Cart from WishList")
    @Parameters({"firstProductName", "secondProductName"})
    public void addProductToCartFromWishList(String firstProductName, String secondProductName) {
        //      Add some product to WishList
        SearchPage searchPage = getHomePage().searchProduct(firstProductName);
        searchPage.clickProductComponentAddToWishList(firstProductName);
        searchPage = searchPage.searchProduct(secondProductName);
        searchPage.clickProductComponentAddToWishList(secondProductName);

        // Get current number of products in Cart
        int numberOfProductsInCart = searchPage.getNumberOfProductsInCartButton();

        //Steps
        // Go to WishListPage
        WishListPage wishListPage = searchPage.goToWishList();
        // Add product to cart from Wish List
        wishListPage.putFromWishListToCartProductByPartialName(secondProductName);

        int actual_numberOfProductsInCart = wishListPage.getNumberOfProductsInCartButton();

        //Check the quantity of products in CartButon after adding product to it from Wish List
        Assert.assertEquals(actual_numberOfProductsInCart, numberOfProductsInCart + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        wishListEmptyPage.goToHomePage();
        // Returning to the starter view -> then must be logOut

    }

    @Test(priority = 5,
            description = "check the possibility of removing product from WishList")
    @Parameters({"firstProductName", "secondProductName"})
    public void removeProductFromWishListByName(String firstProductName, String secondProductName) {
        //      Add some products to WishList
        SearchPage searchPage = getHomePage().searchProduct(firstProductName);
        searchPage.clickProductComponentAddToWishList(firstProductName);
        searchPage = searchPage.searchProduct(secondProductName);
        searchPage.clickProductComponentAddToWishList(secondProductName);

        // Get current number of products in WishList
        int numberProductsInWishList = searchPage.getWishListNumberOfProducts();

        //Steps
        // Go to WishListPage
        WishListPage wishListPage = searchPage.goToWishList();
        // Remove product from WishListPage by Product Name
        wishListPage.removeFromWishListProductByPartialName(firstProductName);

        //Actual Result
        int actualNumberOfProductsInWishList = wishListPage.getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking removeProductFromWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList, numberProductsInWishList - 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        wishListEmptyPage.goToHomePage();
        // Returning to the starter view -> then must be logOut
    }

    @Test(priority = 6,
            description = "check message that wishList is empty on WishListEmptyPage")
    @Parameters({"expectedMessage"})
    public void verifyWishListEmptyPageMessage(String expectedMessage) {

        HomePage homePage = getHomePage().goToHomePage();
        WishListEmptyPage wishListEmptyPage = homePage.goToWishListEmpty();
        String message = wishListEmptyPage.getLabelText();

        Assert.assertEquals(message, expectedMessage);

    }
}

