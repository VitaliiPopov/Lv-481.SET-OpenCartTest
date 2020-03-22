package com.opencart.tools;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.AddressBookPage;
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

    public HomePage getHomePage(){
        return new HomePage(Driver.getDriver());
    }
    public AddressBookPage getAdresbokpage() {
        return new AddressBookPage(Driver.getDriver());
    }
    /*public SearchPage getSearchPage(){
        return new SearchPage(Driver.getDriver());
    }*/

}
