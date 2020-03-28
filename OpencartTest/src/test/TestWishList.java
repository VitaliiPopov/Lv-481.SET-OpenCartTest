package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.product.ProductPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.pages.wishlist.WishListEmptyPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.TestWishListRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestWishList extends TestWishListRunner {

    @Test(priority = 1,
            description = "check the possibility of adding product from SearchPage to WishList")
    @Parameters({"fromSearchPageProductName"})
    public void addProductToWishListFromSearchPage(String fromSearchPageProductName) {
        // Get current number of products in WishList
        int numberProductsInWishList = getHomePage().getWishListNumberOfProducts();

        // Steps
        SearchPage searchPage = getHomePage().searchProduct(fromSearchPageProductName);

        searchPage.clickProductComponentAddToWishList(fromSearchPageProductName);

        searchPage.goToHomePage();
        //Actual Result
        int actualNumberOfProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList, numberProductsInWishList + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListPage wishListPage = getHomePage().goToWishList();
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();

    }

    @Test(priority = 2,
            description = "check the possibility of adding product from HomePage to WishList")
    @Parameters({"fromHomePageProductName"})
    public void addProductToWishListFromHomePage(String fromHomePageProductName) {
        // Get current number of products in WishList
        int numberProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Steps
        HomePage homePage = getHomePage().clickProductComponentAddToWishListButtonByName(fromHomePageProductName);
        homePage.goToWishList();

        int actualNumberOfProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList, numberProductsInWishList + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListPage wishListPage = getHomePage().goToWishList();
        WishListEmptyPage wishListEmptyPage = wishListPage.removeAllProductsFromWishList();
        // Returning to the starter view -> and then must be logOut
        wishListEmptyPage.goToHomePage();

    }

    @Test(priority = 3,
            description = "check the possibility of adding product from ProductPage to WishList")
    @Parameters({"fromProductPageProductName"})
    public void addProductToWishListFromProductPage(String fromProductPageProductName) {
        // Get current number of products in WishList
        int numberProductsInWishList = getHomePage().getWishListNumberOfProducts();

        // Steps
        HomePage homePage = getHomePage();
        ProductPage productPage = homePage.clickOnProductNameLabel(fromProductPageProductName);
        WishListPage wishListPage = productPage.addProductToWishList();

        int actualNumberOfProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking addToWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList, numberProductsInWishList + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage = getHomePage().goToWishList().removeAllProductsFromWishList();
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
        int numberOfProductsInCart = getHomePage().getNumberOfProductsInCartButton();

        //Steps
        // Go to WishListPage
        WishListPage wishListPage = getHomePage().goToWishList();
        // Add product to cart from Wish List
        wishListPage.putFromWishListToCartProductByPartialName(secondProductName);

        int actual_numberOfProductsInCart = getHomePage().getNumberOfProductsInCartButton();

        //Check the quantity of products in CartButon after adding product to it from Wish List
        Assert.assertEquals(actual_numberOfProductsInCart, numberOfProductsInCart + 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage = getHomePage().goToWishList().removeAllProductsFromWishList();
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
        int numberProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Steps
        // Go to WishListPage
        WishListPage wishListPage = getHomePage().goToWishList();
        // Remove product from WishListPage by Product Name
        wishListPage.removeFromWishListProductByPartialName(firstProductName);
        HomePage homePage = getHomePage();
        //Actual Result
        int actualNumberOfProductsInWishList = getHomePage().getWishListNumberOfProducts();

        //Check the quantity of products in WishList after clicking removeProductFromWishListButton
        Assert.assertEquals(actualNumberOfProductsInWishList, numberProductsInWishList - 1);
        // POST CONDITION
        //  remove all products from WishList
        WishListEmptyPage wishListEmptyPage = getHomePage().goToWishList().removeAllProductsFromWishList();
        homePage = wishListEmptyPage.goToHomePage();
        // Returning to the starter view -> then must be logOut
    }

    @Test(priority = 6,
            description = "check message that wishList is empty on WishListEmptyPage")
    @Parameters({"expectedMessage"})
    public void verifyWishListEmptyPageMessage(String expectedMessage) {

        String message = getHomePage().goToWishListEmpty().getLabelText();

        Assert.assertEquals(message, expectedMessage);

    }
}