package test.login_test;

import com.opencart.pages.account.*;
import com.opencart.tools.*;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SmokeAccountTest extends TestRunner {

    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    AdminManager adminAccess = new AdminManager();

    @AfterMethod
    public void logoutUser() throws InterruptedException {
        if (getHomePage().isExistMyAccountDropdownOption("My Account")){
            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
            logoutPage.logout();
        }
        else getHomePage();
    }

    @AfterClass
    public void tearDown() {
        try {
            adminAccess.deleteCustomerFromAdmin(jsonDataConfig.getEmailFromJson(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Parameters({"myAccountText"})
    @Test(priority = 0)
    @Description("Verify that user can register with valid data")
    public void smokeRegisterNewUserTest(String myAccountText) throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage()
                .goToRegisterPage()
                .register(jsonDataConfig.getUserFromJson(0))
                .goToAccountAfterRegistration();
        org.testng.Assert.assertEquals(myAccountPage.getTitleMyAccountText(), myAccountText);
        //adminAccess.deleteCustomerFromAdmin(jsonDataConfig.getEmailFromJson(0));
    }

    @Parameters({"loginText"})
    @Test(priority = 1)
    @Description("Verify that existed user can login with valid data")
    public void smokeLoginExistedUserTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getPasswordFromJson(0));
        Assert.assertTrue(myAccountPage.getTitleMyAccountText().equals("My Account"));
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    @Description("Verify that user can change password with valid data")
    public void smokeChangePasswordTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(
                jsonDataConfig.getPasswordFromJson(0),
                jsonDataConfig.getPasswordFromJson(0));
        Assert.assertEquals(true, myAccountPage.isSuccessAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 3)
    @Description("Verify that user can edit account with valid data")
    public void smokeEditAccountTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        //getting valid user data for editing account
        myAccountPage = editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getTelephoneFromJson(1)
        );
        Assert.assertEquals(true, myAccountPage.isSuccessAlertPresent());
    }

    public MyAccountPage loginUser(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getPasswordFromJson(0));
        return myAccountPage;
    }
}
