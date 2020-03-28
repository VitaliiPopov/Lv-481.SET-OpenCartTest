package test;

import com.opencart.pages.account.*;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.*;
import org.junit.Assert;
import org.testng.annotations.*;

public class LoginUserCompareTest extends CompareTestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @BeforeTest
    public void primaryRegistration() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        SuccessRegisterPage success = registerPage.register(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1),
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        success.goToAccountAfterRegistration();
        AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
        logoutPage.logout();
    }

    @AfterMethod
    public void finishLogout() {
        try {
            logoutUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            deleteCustomerFromAdmin(jsonDataConfig.getEmailFromJson(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"loginText"})
    @Test(priority = 0)
    public void smokeLoginExistedUserTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        Assert.assertTrue(myAccountPage.getTitleMyAccountText().equals("My Account"));
    }

    @Parameters({"loginText"})
    @Test(priority = 1)
    public void loginNotExistedUserTest(String loginText) throws InterruptedException {
        String email = Randomizer.generateRandomString(5)+"@"+Randomizer.generateRandomString(3)+"."+Randomizer.generateRandomString(3);
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login(email, Randomizer.generateRandomString(10));
        Assert.assertTrue(loginPage.isAlertDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    public void loginWithEmptyFieldsTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isAlertDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 3)
    public void loginWithEmptyEmailTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login("", "Randomizer.generateRandomString(10)");
        Assert.assertTrue(loginPage.isAlertDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    public void loginWithEmptyPasswordTest(String loginText) throws InterruptedException {
        String email = Randomizer.generateRandomString(5)+"@"+Randomizer.generateRandomString(3)+"."+Randomizer.generateRandomString(3);
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login(email, "");
        Assert.assertTrue(loginPage.isAlertDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 5)
    public void loginCaseSensitiveFieldsTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login(jsonDataConfig.getEmailFromJson(1).toUpperCase(), jsonDataConfig.getPasswordFromJson(1).toUpperCase());
        Assert.assertTrue(loginPage.isAlertDisplayed());
    }

    public void logoutUser() throws InterruptedException {
        if (getHomePage().isExistMyAccountDropdownOption("My Account")){
            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
            logoutPage.logout();
        }
        else getHomePage();
    }

    public void deleteCustomerFromAdmin(String email) throws InterruptedException {
        AdminLoginPage adminLoginPage = new AdminLoginPage(Driver.getAdminDriver());
        AdminHomePage adminHomePage = adminLoginPage.adminLogin(jsonDataConfig.getEmailFromJson(2), jsonDataConfig.getPasswordFromJson(2));
        adminHomePage.clickOnCustomerDropdown();
        AdminCustomerPage adminCustomerPage = adminHomePage.clickOnCustomerTab();
        adminCustomerPage.findCustomerByEmail(email);
        adminCustomerPage.clickDeleteCustomerButton();
        adminCustomerPage.confirmAction();
    }
}