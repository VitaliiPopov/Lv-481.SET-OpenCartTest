package test.register_test;

import com.opencart.pages.account.*;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.*;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class EditAccountTest extends TestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @BeforeMethod
    public void primaryRegistration() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        //getting valid user data
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
        Assert.assertTrue(myAccountPage.isSuccessAlertDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 1)
    public void editAccountWithEmptyFieldsTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.clearFields();
        editAccountPage.clickEditButton();
        Assert.assertTrue(editAccountPage.isAlertFirstnameDisplayed());
        Assert.assertTrue(editAccountPage.isAlertLastnameDisplayed());
        Assert.assertTrue(editAccountPage.isAlertEmailDisplayed());
        Assert.assertTrue(editAccountPage.isAlertTelephoneDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    public void editAccountWithEmptyFirstnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                "",
                jsonDataConfig.getLastNameFromJson(0),
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getTelephoneFromJson(0)
        );
        Assert.assertTrue(editAccountPage.isAlertFirstnameDisplayed());
    }

    //firstname longer than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 3)
    public void editAccountWithLongFirstnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                randomAlphabetic(33),
                jsonDataConfig.getLastNameFromJson(0),
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getTelephoneFromJson(0)
        );
        Assert.assertTrue(editAccountPage.isAlertFirstnameDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    public void editAccountWithEmptyLastnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                "",
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getTelephoneFromJson(0)
        );
        Assert.assertTrue(editAccountPage.isAlertLastnameDisplayed());
    }

    //lastname longer than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 5)
    public void editAccountWithLongLastnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                randomAlphabetic(33),
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getTelephoneFromJson(0)
        );
        Assert.assertTrue(editAccountPage.isAlertLastnameDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 6)
    public void editAccountWithEmptyEmailTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                jsonDataConfig.getLastNameFromJson(0),
                "",
                jsonDataConfig.getTelephoneFromJson(0)
        );
        Assert.assertTrue(editAccountPage.isAlertEmailDisplayed());
    }

    @Parameters({"loginText", "editAccountText"})
    @Test(priority = 7)
    public void editAccountEmailWithoutAtSymbolTest(String loginText, String editAccountText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                jsonDataConfig.getLastNameFromJson(0),
                randomAlphabetic(5),
                jsonDataConfig.getTelephoneFromJson(0)
        );
        Assert.assertTrue(editAccountPage.getTitleAccountInformationText().equals(editAccountText));
    }

    @Parameters({"loginText"})
    @Test(priority = 8)
    public void editAccountEmailWithoutDotSymbolTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                jsonDataConfig.getLastNameFromJson(0),
                randomAlphabetic(5)+"@"+randomAlphabetic(5),
                jsonDataConfig.getTelephoneFromJson(0)
        );
        Assert.assertTrue(editAccountPage.isAlertEmailDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 9)
    public void editAccountWithExistedEmailTest(String loginText) throws InterruptedException {
        String email = getExistedEmailFromAdmin();
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                jsonDataConfig.getLastNameFromJson(0),
                email,
                jsonDataConfig.getTelephoneFromJson(0)
        );
        Assert.assertTrue(editAccountPage.isWarningDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 10)
    public void editAccountWithEmptyTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                jsonDataConfig.getLastNameFromJson(0),
                jsonDataConfig.getEmailFromJson(0),
                ""
        );
        Assert.assertTrue(editAccountPage.isAlertTelephoneDisplayed());
    }

    //telephone shorter than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 11)
    public void editAccountWithShortTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                jsonDataConfig.getLastNameFromJson(0),
                jsonDataConfig.getEmailFromJson(0),
                randomAlphabetic(2)
        );
        Assert.assertTrue(editAccountPage.isAlertTelephoneDisplayed());
    }

    //telephone longer than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 12)
    public void editAccountWithLongTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(0),
                jsonDataConfig.getLastNameFromJson(0),
                jsonDataConfig.getEmailFromJson(0),
                randomAlphabetic(33)
        );
        Assert.assertTrue(editAccountPage.isAlertTelephoneDisplayed());
    }

    public MyAccountPage loginUser(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getPasswordFromJson(0));
        return myAccountPage;
    }

    public void logoutUser() throws InterruptedException {
        if (getHomePage().isExistMyAccountDropdownOption("My Account")) {
            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
            logoutPage.logout();
        } else getHomePage();
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

    @Parameters({"loginText"})
    @Test(priority = 0)
    public void complexTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        Assert.assertTrue(myAccountPage.getTitleMyAccountText().equals("My Account"));

        //getting valid user data for editing from json
        String newFirstName = jsonDataConfig.getFirstNameFromJson(1);
        String newLastName = jsonDataConfig.getLastNameFromJson(1);
        String newEmail = jsonDataConfig.getEmailFromJson(1);
        String newTelephone = jsonDataConfig.getTelephoneFromJson(1);
        String newPassword = jsonDataConfig.getPasswordFromJson(1);

        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        myAccountPage = editAccountPage.editAccountInformation(
                newFirstName,
                newLastName,
                newEmail,
                newTelephone
        );
        Assert.assertTrue(myAccountPage.isSuccessAlertDisplayed());

        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(newPassword, newPassword);
        Assert.assertTrue(myAccountPage.isSuccessAlertDisplayed());

        AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
        logoutPage.logout();

        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        myAccountPage = loginPage.login(newEmail, newPassword);
        Assert.assertTrue(myAccountPage.getTitleMyAccountText().equals("My Account"));

        editAccountPage = myAccountPage.clickEditMyAccountLink();
        Assert.assertTrue(editAccountPage.getFirstNameEditValue().equals(newFirstName));
        Assert.assertTrue(editAccountPage.getLastNameEditValue().equals(newLastName));
        Assert.assertTrue(editAccountPage.getEmailEditValue().equals(newEmail));
        Assert.assertTrue(editAccountPage.getTelephoneEditValue().equals(newTelephone));
        editAccountPage.editEmailField(jsonDataConfig.getEmailFromJson(0));
        editAccountPage.clickEditButton();
    }
}