package com.opencart.tools;

import com.opencart.pages.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestRunner {

    @BeforeClass
    public void beforeClass() {
        Driver.getDriver();
    }

    @AfterClass
    public void afterClass() {
        Driver.Quit();
    }

    public HomePage getHomePage() {
        return new HomePage(Driver.getDriver());
    }

    /*public SearchPage getSearchPage(){
        return new SearchPage(Driver.getDriver());
    }*/
}
