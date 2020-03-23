package test;

import com.opencart.pages.account.AccountLogoutPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.account.RegisterPage;
import com.opencart.pages.account.SuccessRegisterPage;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.Driver;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import com.opencart.tools.Utility;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegisterEditAccountTest  extends TestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            Utility.getScreenshot(Driver.getDriver());
//        }
//        try {
//            deleteCustomerFromAdmin();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    @Parameters({"myAccountDropdownText"})
    @Test(priority = 0)
    public void smokeRegisterNewUserTest(String myAccountDropdownText) {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        SuccessRegisterPage success = registerPage.register(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1),
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        MyAccountPage myAccountPage = success.goToAccountAfterRegistration();
        Assert.assertEquals(myAccountPage.getTitleMyAccountText(), myAccountDropdownText );
    }

    @Parameters({"registerText"})
    @Test(priority = 1)
    public void registerWithEmptyFieldsTest(String registerText) {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getTitleRegisterBlockText().contains(registerText));
    }

    @Test(priority = 2)
    public void registerWithLongFirstLastNamesTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1),
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        Assert.assertTrue(registerPage.isFirstNameAlertDisplayed());
        Assert.assertTrue(registerPage.isLastNameAlertDisplayed());
    }

    @Test(priority = 3)
    public void registerWithSameEmailTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                "uuu",
                "uuu",
                jsonDataConfig.getEmailFromJson(1),
                "123",
                "uuuu",
                "uuuu");
        Assert.assertTrue(registerPage.getWarningText().contains("E-Mail"));
    }

    @Test(priority = 4)
    public void registerWithoutPrivacyPolicyAgreementTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.fillInputFirstname(jsonDataConfig.getFirstNameFromJson(1));
        registerPage.fillInputLastname(jsonDataConfig.getLastNameFromJson(1));
        registerPage.fillInputEmail(jsonDataConfig.getEmailFromJson(1));
        registerPage.fillInputTelephone(jsonDataConfig.getTelephoneFromJson(1));
        registerPage.fillInputPassword(jsonDataConfig.getPasswordFromJson(1));
        registerPage.fillInputConfirmPassword(jsonDataConfig.getPasswordFromJson(1));
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getWarningText().contains("Privacy Policy"));
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
