package test;

import com.opencart.pages.account.*;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.CompareTestRunner;
import com.opencart.tools.Driver;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.Randomizer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegisterCompareTest extends CompareTestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @AfterMethod
    public void tearDown() {
        try {
            logoutUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"myAccountText"})
    @Test(priority = 0)
    public void smokeRegisterNewUserTest(String myAccountText) throws InterruptedException {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        SuccessRegisterPage success = registerPage.register(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1),
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        MyAccountPage myAccountPage = success.goToAccountAfterRegistration();
        Assert.assertEquals(myAccountPage.getTitleMyAccountText(), myAccountText);
        deleteCustomerFromAdmin(jsonDataConfig.getEmailFromJson(1));
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
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                "",
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1),
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        Assert.assertTrue(registerPage.isFirstNameAlertDisplayed());
    }

    @Test(priority = 3)
    public void registerWithEmptyLastNameTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                jsonDataConfig.getFirstNameFromJson(1),
                "",
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1),
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        Assert.assertTrue(registerPage.isLastNameAlertDisplayed());
    }

    //first last names longer than 32 characters
    @Test(priority = 4)
    public void registerWithLongFirstLastNamesTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(35),
                Randomizer.generateRandomString(35),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1),
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        Assert.assertTrue(registerPage.isFirstNameAlertDisplayed());
        Assert.assertTrue(registerPage.isLastNameAlertDisplayed());
    }

    @Test(priority = 5)
    public void registerWithEmptyEmailTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                "",
                jsonDataConfig.getTelephoneFromJson(1),
                jsonDataConfig.getPasswordFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        Assert.assertTrue(registerPage.isEmailAlertDisplayed());
    }

    @Test(priority = 6)
    public void registerWithExistedEmailTest() {
        String email = null;
        try {
            email = getExistedEmailFromAdmin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String password = Randomizer.generateRandomString(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(10),
                Randomizer.generateRandomString(10),
                email,
                Randomizer.generateRandomString(5),
                password,
                password);
        Assert.assertTrue(registerPage.getWarningText().contains("E-Mail"));
    }

    @Parameters({"loginText", "registerText"})
    @Test(priority = 7)
    public void registerEmailWithoutAtSymbolTest(String loginText, String registerText) throws InterruptedException {
        String password = Randomizer.generateRandomString(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(10),
                Randomizer.generateRandomString(10),
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                password,
                password);
        Assert.assertTrue(registerPage.getTitleRegisterBlockText().equals(registerText));
    }

    @Parameters({"loginText"})
    @Test(priority = 8)
    public void registerEmailWithoutDotSymbolTest(String loginText) throws InterruptedException {
        String password = Randomizer.generateRandomString(5);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(10),
                Randomizer.generateRandomString(10),
                Randomizer.generateRandomString(5) + "@" + Randomizer.generateRandomString(3),
                Randomizer.generateRandomString(5),
                password,
                password);
        Assert.assertTrue(registerPage.isEmailAlertDisplayed());
    }


    @Test(priority = 9)
    public void registerWithEmptyTelephoneTest() {
        String password = Randomizer.generateRandomString(5);
        String email = Randomizer.generateRandomString(5) + "@" + Randomizer.generateRandomString(3) + "." + Randomizer.generateRandomString(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                email,
                "",
                password,
                password);
        Assert.assertTrue(registerPage.isTelephoneAlertDisplayed());
    }

    //telephone shorter than 3 characters
    @Test(priority = 10)
    public void registerWithShortTelephoneTest() {
        String password = Randomizer.generateRandomString(5);
        String email = Randomizer.generateRandomString(5) + "@" + Randomizer.generateRandomString(3) + "." + Randomizer.generateRandomString(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                email,
                Randomizer.generateRandomString(2),
                password,
                password);
        Assert.assertTrue(registerPage.isTelephoneAlertDisplayed());
    }

    //telephone longer than 32 characters
    @Test(priority = 11)
    public void registerWithLongTelephoneTest() throws InterruptedException {
        String password = Randomizer.generateRandomString(5);
        String email = Randomizer.generateRandomString(5) + "@" + Randomizer.generateRandomString(3) + "." + Randomizer.generateRandomString(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                email,
                Randomizer.generateRandomString(35),
                password,
                password);
        Assert.assertTrue(registerPage.isTelephoneAlertDisplayed());
    }

    @Test(priority = 12)
    public void registerWithEmptyPasswordTest() {
        String email = Randomizer.generateRandomString(5) + "@" + Randomizer.generateRandomString(3) + "." + Randomizer.generateRandomString(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                email,
                Randomizer.generateRandomString(10),
                "",
                Randomizer.generateRandomString(5));
        Assert.assertTrue(registerPage.isPasswordAlertDisplayed());
        Assert.assertTrue(registerPage.isConfirmPasswordAlertDisplayed());
    }

    //password shorter than 4 characters
    @Test(priority = 13)
    public void registerWithShortPasswordTest() {
        String password = Randomizer.generateRandomString(3);
        String email = Randomizer.generateRandomString(5) + "@" + Randomizer.generateRandomString(3) + "." + Randomizer.generateRandomString(3);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                email,
                Randomizer.generateRandomString(5),
                password,
                password);
        Assert.assertTrue(registerPage.isPasswordAlertDisplayed());
    }

    //password longer than 20 characters
    @Test(priority = 14)
    public void registerWithLongPasswordTest() throws InterruptedException {
        String email = Randomizer.generateRandomString(5) + "@" + Randomizer.generateRandomString(3) + "." + Randomizer.generateRandomString(3);
        String password = Randomizer.generateRandomString(25);
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        registerPage.register(
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                email,
                Randomizer.generateRandomString(5),
                password,
                password);
        Assert.assertTrue(registerPage.isPasswordAlertDisplayed());//bug
    }

    @Test(priority = 15)
    public void registerWithBadConfirmTest() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        String email = Randomizer.generateRandomString(5) + "@" + Randomizer.generateRandomString(3) + "." + Randomizer.generateRandomString(3);
        registerPage.register(
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                email,
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5),
                Randomizer.generateRandomString(5));
        Assert.assertTrue(registerPage.isConfirmPasswordAlertDisplayed());
    }

    @Test(priority = 16)
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

    public void logoutUser() throws InterruptedException {
        if (getHomePage().isExistMyAccountDropdownOption("My Account")) {
            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
            logoutPage.logout();
        } else getHomePage();
    }

    public String getExistedEmailFromAdmin() throws InterruptedException {
        AdminLoginPage adminLoginPage = new AdminLoginPage(Driver.getAdminDriver());
        AdminHomePage adminHomePage = adminLoginPage.adminLogin(jsonDataConfig.getEmailFromJson(2), jsonDataConfig.getPasswordFromJson(2));
        adminHomePage.clickOnCustomerDropdown();
        AdminCustomerPage adminCustomerPage = adminHomePage.clickOnCustomerTab();
        String mail = adminCustomerPage.getExistedEmail();
        return mail;
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
