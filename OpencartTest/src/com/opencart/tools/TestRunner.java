package com.opencart.tools;

import com.opencart.pages.HomePage;
import com.opencart.pages.product_table.CartPage;
import com.opencart.pages.product_table.WishListPage;
import org.testng.annotations.*;

public class TestRunner {

    @BeforeClass
    public void beforeClass(){
        Driver.getDriver();
    }
    @AfterClass
    public void afterClass(){
        Driver.Quit();
    }



    /*public SearchPage getSearchPage(){
        return new SearchPage(Driver.getDriver());
    }*/
    public HomePage getHomePage(){
        return new HomePage(Driver.getDriver());
    }
    public CartPage getCartPage(){
        return new CartPage(Driver.getDriver());
    }
    public WishListPage getWishListPage(){
        return new WishListPage(Driver.getDriver());
    }

}
