package test.register_test;

import com.opencart.pages.account.*;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.Driver;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.apache.commons.lang.RandomStringUtils.*;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;

public class RegisterTest extends TestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @AfterMethod
    public void finishLogout() {
        try {
            logoutUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            deleteCustomerFromAdmin(jsonDataConfig.getEmailFromJson(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"myAccountText"})
    @Test(priority = 0)
    public void smokeRegisterNewUserTest(String myAccountText) throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage()
                .goToRegisterPage()
                .register(jsonDataConfig.getUserFromJson(0))
                .goToAccountAfterRegistration();
        Assert.assertEquals(myAccountPage.getTitleMyAccountText(), myAccountText);
    }

    @Parameters({"registerText"})
    @Test(priority = 1)
    public void registerWithEmptyFieldsTest(String registerText) {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getTitleRegisterBlockText().contains(registerText));
        Assert.assertTrue(registerPage.isFirstNameAlertDisplayed());
        Assert.assertTrue(registerPage.isLastNameAlertDisplayed());
        Assert.assertTrue(registerPage.isEmailAlertDisplayed());
        Assert.assertTrue(registerPage.isTelephoneAlertDisplayed());
        Assert.assertTrue(registerPage.isPasswordAlertDisplayed());
        Assert.assertTrue(registerPage.getWarningText().contains("Privacy Policy"));
    }

    @Test(priority = 2)
    public void registerWithEmptyFirstNameTest() {
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        String password = randomAlphabetic(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                "",
                randomAlphabetic(5),
                email,
                randomAlphabetic(5),
                password,
                password);
        Assert.assertTrue(registerPage.isFirstNameAlertDisplayed());
    }

    @Test(priority = 3)
    public void registerWithEmptyLastNameTest() {
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        String password = randomAlphabetic(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(5),
                "",
                email,
                randomAlphabetic(5),
                password,
                password);
        Assert.assertTrue(registerPage.isLastNameAlertDisplayed());
    }

    //first last names longer than 32 characters
    @Test(priority = 4)
    public void registerWithLongFirstLastNamesTest() {
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        String password = randomAlphabetic(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(35),
                randomAlphabetic(35),
                email,
                randomAlphabetic(5),
                password,
                password);
        Assert.assertTrue(registerPage.isFirstNameAlertDisplayed());
        Assert.assertTrue(registerPage.isLastNameAlertDisplayed());
    }

    @Test(priority = 5)
    public void registerWithEmptyEmailTest() {
        String password = randomAlphabetic(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(5),
                randomAlphabetic(5),
                "",
                randomAlphabetic(5),
                password,
                password);
        Assert.assertTrue(registerPage.isEmailAlertDisplayed());
    }

    @Test(priority = 6)
    public void registerWithExistedEmailTest() throws InterruptedException {
        String email = getExistedEmailFromAdmin();
        String password = randomAscii(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                 randomAlphabetic(10),
                 randomAlphabetic(10),
                 email,
                 randomAlphabetic(5),
                 password,
                 password);
        Assert.assertTrue(registerPage.getWarningText().contains("E-Mail"));
    }

    @Parameters({"loginText", "registerText"})
    @Test(priority = 7)
    public void registerEmailWithoutAtSymbolTest(String loginText, String registerText) throws InterruptedException {
        String password = randomAscii(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(10),
                randomAlphabetic(10),
                randomAlphabetic(5),
                randomAlphabetic(5),
                password,
                password);
        Assert.assertTrue(registerPage.getTitleRegisterBlockText().contains(registerText));
    }

    @Parameters({"loginText"})
    @Test(priority = 8)
    public void registerEmailWithoutDotSymbolTest(String loginText) throws InterruptedException {
        String password = randomAscii(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(10),
                randomAlphabetic(10),
                randomAlphabetic(5)+"@"+randomAlphabetic(3),
                randomAlphabetic(5),
                password,
                password);
        Assert.assertTrue(registerPage.isEmailAlertDisplayed());
    }


    @Test(priority = 9)
    public void registerWithEmptyTelephoneTest() {
        String password = randomAlphabetic(5);
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(5),
                randomAlphabetic(5),
                email,
                "",
                password,
                password);
        Assert.assertTrue(registerPage.isTelephoneAlertDisplayed());
    }

    //telephone shorter than 3 characters
    @Test(priority = 10)
    public void registerWithShortTelephoneTest() {
        String password = randomAscii(5);
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(5),
                randomAlphabetic(5),
                email,
                randomAlphabetic(2),
                password,
                password);
        Assert.assertTrue(registerPage.isTelephoneAlertDisplayed());
    }

    //telephone longer than 32 characters
    @Test(priority = 11)
    public void registerWithLongTelephoneTest() throws InterruptedException {
        String password = randomAscii(5);
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(5),
                randomAlphabetic(5),
                email,
                randomAlphabetic(35),
                password,
                password);
        Assert.assertTrue(registerPage.isTelephoneAlertDisplayed());
    }

    @Test(priority = 12)
    public void registerWithEmptyPasswordTest() {
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(5),
                randomAlphabetic(5),
                email,
                randomAlphabetic(10),
                "",
                randomAlphabetic(5));
        Assert.assertTrue(registerPage.isPasswordAlertDisplayed());
        Assert.assertTrue(registerPage.isConfirmPasswordAlertDisplayed());
    }

    //password shorter than 4 characters
    @Test(priority = 13)
    public void registerWithShortPasswordTest() {
        String password = randomAlphabetic(3);
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(5),
                randomAlphabetic(5),
                email,
                randomAlphabetic(5),
                password,
                password);
        Assert.assertTrue(registerPage.isPasswordAlertDisplayed());
    }

    //password longer than 20 characters
    @Test(priority = 14)
    public void registerWithLongPasswordTest() throws InterruptedException {
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        String password = randomAscii(25);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                randomAlphabetic(5),
                randomAlphabetic(5),
                email,
                randomAlphabetic(5),
                password,
                password);
        Assert.assertTrue(registerPage.isPasswordAlertDisplayed());//bug
    }

    @Test(priority = 15)
    public void registerWithBadConfirmTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        String email = randomAlphabetic(5)+"@"+randomAlphabetic(3)+"."+randomAlphabetic(3);
        registerPage.register(
                randomAlphabetic(5),
                randomAlphabetic(5),
                email,
                randomAlphabetic(5),
                randomAlphabetic(5),
                randomAlphabetic(5));
        Assert.assertTrue(registerPage.isConfirmPasswordAlertDisplayed());
    }

    @Test(priority = 16)
    public void registerWithoutPrivacyPolicyAgreementTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.fillInputFirstname(jsonDataConfig.getFirstNameFromJson(0));
        registerPage.fillInputLastname(jsonDataConfig.getLastNameFromJson(0));
        registerPage.fillInputEmail(jsonDataConfig.getEmailFromJson(0));
        registerPage.fillInputTelephone(jsonDataConfig.getTelephoneFromJson(0));
        registerPage.fillInputPassword(jsonDataConfig.getPasswordFromJson(0));
        registerPage.fillInputConfirmPassword(jsonDataConfig.getPasswordFromJson(0));
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getWarningText().contains("Privacy Policy"));
    }

    public void logoutUser() throws InterruptedException {
        if (getHomePage().isExistMyAccountDropdownOption("My Account")){
            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
            logoutPage.logout();
        }
        else getHomePage();
    }

    public String getExistedEmailFromAdmin() throws InterruptedException {
        AdminLoginPage adminLoginPage = new AdminLoginPage(Driver.getAdminDriver());
        AdminHomePage adminHomePage = adminLoginPage.adminLogin(
                jsonDataConfig.getEmailFromJson(2),
                jsonDataConfig.getPasswordFromJson(2));
        adminHomePage.clickOnCustomerDropdown();
        AdminCustomerPage adminCustomerPage = adminHomePage.clickOnCustomerTab();
        String mail = adminCustomerPage.getExistedEmail();
        return mail;
    }

    public void deleteCustomerFromAdmin(String email) throws InterruptedException {
        AdminLoginPage adminLoginPage = new AdminLoginPage(Driver.getAdminDriver());
        AdminHomePage adminHomePage = adminLoginPage.adminLogin(
                jsonDataConfig.getEmailFromJson(2),
                jsonDataConfig.getPasswordFromJson(2));
        adminHomePage.clickOnCustomerDropdown();
        AdminCustomerPage adminCustomerPage = adminHomePage.clickOnCustomerTab();
        adminCustomerPage.findCustomerByEmail(email);
        adminCustomerPage.clickDeleteCustomerButton();
        adminCustomerPage.confirmAction();
    }
}
