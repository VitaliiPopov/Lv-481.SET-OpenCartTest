package test;

import com.opencart.pages.account.*;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.*;
import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ChangePasswordCompareTest extends CompareTestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @BeforeMethod
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
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }
        try {
            logoutUser();
            deleteCustomerFromAdmin(jsonDataConfig.getEmailFromJson(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"loginText"})
    @Test(priority = 0)
    public void smokeChangePasswordTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        myAccountPage = changePasswordPage.changePassword(jsonDataConfig.getPasswordFromJson(1), jsonDataConfig.getPasswordFromJson(1));
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
        String password = Randomizer.generateRandomString(3);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(password, password);
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
    }

    //bug
    @Parameters({"loginText"})
    @Test(priority = 3)
    public void changePasswordToLongTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        String password = Randomizer.generateRandomString(25);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(password, password);
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed()); //bug
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    public void changePasswordWrongConfirmTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(Randomizer.generateRandomString(5), Randomizer.generateRandomString(5));
        Assert.assertTrue(changePasswordPage.isAlertConfirmDisplayed());
    }

    public MyAccountPage loginUser(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
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
