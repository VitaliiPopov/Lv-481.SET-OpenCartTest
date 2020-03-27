package com.opencart.tools;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import org.testng.annotations.*;

public class TestWishListRunner {

    JsonDataConfig jsonParser = new JsonDataConfig("TestData.json");

    @BeforeClass
    public void beforeClass() {
        Driver.getDriver();
    }

    @AfterClass
    public void afterClass() {
        Driver.quit();

    }

    @BeforeMethod
    @Parameters({"myAccountDropdownText"})
    public void login(String myAccountDropdownText) {
        // Preconditions
        //      Login to system
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(7), jsonParser.getPasswordFromJson(7));
        myAccountPage.goToHomePage();
        jsonParser.getUserFromJson(1).getEmail();

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        Driver.clearCookies();
    }

    public HomePage getHomePage() {
        return new HomePage(Driver.getDriver());
    }


}
