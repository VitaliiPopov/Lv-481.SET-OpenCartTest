package test;

import com.opencart.data.ConstantVariables;
import com.opencart.pages.HomePage;
import com.opencart.pages.account.*;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginChangePasswordTest extends TestRunner {
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
            deleteCustomerFromAdmin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"loginText"})
    @Test(priority = 1)
    public void loginNotExistedUserTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login("www@gmail.com", "jkjk");
        Assert.assertTrue(loginPage.isAlertDisplayed());
        logoutUser();
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    public void loginWithEmptyFieldsTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isAlertDisplayed());
        logoutUser();
    }

    @Parameters({"loginext"})
    @Test(priority = 3)
    public void loginCaseSensitiveFieldsTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login(jsonDataConfig.getEmailFromJson(1).toUpperCase(), jsonDataConfig.getPasswordFromJson(1).toUpperCase());
        Assert.assertTrue(loginPage.isAlertDisplayed());
        logoutUser();
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    public void smokeLoginExistedUserTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        Assert.assertTrue(myAccountPage.getTitleMyAccountText().equals("My Account"));
        logoutUser();
    }

    @Parameters({"loginText"})
    @Test(priority = 5)
    public void changePasswordToEmptyTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.clickChangePasswordButton();
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
        logoutUser();
    }

    @Parameters({"loginText"})
    @Test(priority = 6)
    public void changePasswordToShortTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("ttt", "ttt");
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
        logoutUser();
    }

    //bug
    @Parameters({"loginText"})
    @Test(priority = 7)
    public void changePasswordToLongTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("tttttttttttttttttttttttt", "tttttttttttttttttttttttt");
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed()); //bug
        logoutUser();
    }


    @Parameters({"loginText"})
    @Test(priority = 8)
    public void changePasswordWrongConfirmTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("test", "testtest");
        Assert.assertTrue(changePasswordPage.isAlertConfirmDisplayed());
        logoutUser();
    }

    @Parameters({"loginText"})
    @Test(priority = 9)
    public void changePasswordTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        myAccountPage = changePasswordPage.changePassword("aaaa", "aaaa");
        Assert.assertTrue(myAccountPage.isSuccessAlertDisplayed());
        logoutUser();
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

    public void deleteCustomerFromAdmin() throws InterruptedException {
        AdminLoginPage adminLoginPage = new AdminLoginPage(Driver.getAdminDriver());
        AdminHomePage adminHomePage = adminLoginPage.adminLogin("root", "root");
        adminHomePage.clickOnCustomerDropdown();
        AdminCustomerPage adminCustomerPage = adminHomePage.clickOnCustomerTab();
        adminCustomerPage.findCustomerByEmail(jsonDataConfig.getEmailFromJson(1));
        adminCustomerPage.clickDeleteCustomerButton();
        adminCustomerPage.confirmAction();
    }
}