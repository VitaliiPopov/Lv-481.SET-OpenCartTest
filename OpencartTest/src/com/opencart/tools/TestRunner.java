package com.opencart.tools;

import com.opencart.pages.HomePage;
import org.testng.annotations.*;

public class TestRunner {

    @BeforeClass
    public void beforeClass() {
        Driver.getDriver();
    }

    @AfterClass
    public void afterClass() {
        Driver.Quit();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        Driver.ClearCookies();
    }

    public HomePage getHomePage() {
        return new HomePage(Driver.getDriver());
    }
}