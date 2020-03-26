package test.login_test;

import com.opencart.pages.account.*;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.*;
import org.junit.Assert;
import org.testng.annotations.*;

import static org.apache.commons.lang3.RandomStringUtils.*;

public class ChangePasswordTest extends TestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");


    @BeforeMethod
    public void primaryRegistration() throws InterruptedException {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        SuccessRegisterPage success = registerPage.register(jsonDataConfig.getUserFromJson(0));
        success.goToAccountAfterRegistration();
        AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
        logoutPage.logout();
    }

    @AfterMethod
    public void tearDown() {
        try {
            logoutUser();
            deleteCustomerFromAdmin(jsonDataConfig.getEmailFromJson(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"loginText"})
    @Test(priority = 0)
    public void smokeChangePasswordTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        Assert.assertTrue(myAccountPage.isSuccessAlertDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 1)
    public void changePasswordToEmptyTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.clickChangePasswordButton();
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    public void changePasswordToShortTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        String password = randomAlphabetic(3);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(password, password);
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
    }

    //password longer than 20 characters
    @Parameters({"loginText"})
    @Test(priority = 3)
    public void changePasswordToLongTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        String password = randomAlphabetic(25);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(password, password);
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed()); //bug
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    public void changePasswordWrongConfirmTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(randomAlphabetic(5), randomAlphabetic(5));
        Assert.assertTrue(changePasswordPage.isAlertConfirmDisplayed());
    }

    public MyAccountPage loginUser(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getPasswordFromJson(0));
        return myAccountPage;
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
