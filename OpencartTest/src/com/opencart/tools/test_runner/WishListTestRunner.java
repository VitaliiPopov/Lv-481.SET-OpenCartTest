package com.opencart.tools.test_runner;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.tools.Instance;
import com.opencart.tools.JsonDataConfig;
import org.testng.annotations.*;

public class WishListTestRunner {

    JsonDataConfig jsonParser = new JsonDataConfig("TestData.json");

    @BeforeClass
    public void beforeClass() {
        Instance.getDriver();
    }

    @AfterClass
    public void afterClass() {
        Instance.quit();

    }

    @BeforeMethod
    @Parameters({"myAccountDropdownText"})
    public void login(String myAccountDropdownText) {
        Instance.getURL();
        // Preconditions
        //      Login to system
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonParser.getEmailFromJson(7), jsonParser.getPasswordFromJson(7));
        myAccountPage.goToHomePage();


    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        Instance.clearCookies();
    }

    public HomePage getHomePage() {
        return new HomePage(Instance.getDriver());
    }
}
