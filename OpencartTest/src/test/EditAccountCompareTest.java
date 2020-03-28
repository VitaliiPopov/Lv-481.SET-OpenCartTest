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

public class EditAccountCompareTest extends CompareTestRunner {
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
    public void smokeEditAccountTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        myAccountPage = editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1)
        );
        Assert.assertTrue(myAccountPage.isSuccessAlertDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 1)
    public void editAccountWithEmptyFieldsTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.clearFirstnameEditField();
        editAccountPage.clearLastnameEditField();
        editAccountPage.clearEmailEditField();
        editAccountPage.clearTelephoneEditField();
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
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1)
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
                Randomizer.generateRandomString(33),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1)
        );
        Assert.assertTrue(editAccountPage.isAlertFirstnameDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    public void editAccountWithEmptyLastnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                "",
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1)
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
                jsonDataConfig.getFirstNameFromJson(1),
                Randomizer.generateRandomString(33),
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getTelephoneFromJson(1)
        );
        Assert.assertTrue(editAccountPage.isAlertLastnameDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 6)
    public void editAccountWithEmptyEmailTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                "",
                jsonDataConfig.getTelephoneFromJson(1)
        );
        Assert.assertTrue(editAccountPage.isAlertEmailDisplayed());
    }

    @Parameters({"loginText", "editAccountText"})
    @Test(priority = 7)
    public void editAccountEmailWithoutAtSymbolTest(String loginText, String editAccountText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                Randomizer.generateRandomString(5),
                jsonDataConfig.getTelephoneFromJson(1)
        );
        Assert.assertTrue(editAccountPage.getTitleAccountInformationText().equals(editAccountText));
    }

    @Parameters({"loginText"})
    @Test(priority = 8)
    public void editAccountEmailWithoutDotSymbolTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                Randomizer.generateRandomString(5)+"@"+Randomizer.generateRandomString(5),
                jsonDataConfig.getTelephoneFromJson(1)
        );
        Assert.assertTrue(editAccountPage.isAlertEmailDisplayed());
    }

    @Parameters({"loginText"})
    @Test(priority = 9)
    public void editAccountWithEmptyTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                ""
        );
        Assert.assertTrue(editAccountPage.isAlertTelephoneDisplayed());
    }

    //telephone shorter than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 10)
    public void editAccountWithShortTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                Randomizer.generateRandomString(2)
        );
        Assert.assertTrue(editAccountPage.isAlertTelephoneDisplayed());
    }

    //telephone longer than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 11)
    public void editAccountWithLongTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editAccountInformation(
                jsonDataConfig.getFirstNameFromJson(1),
                jsonDataConfig.getLastNameFromJson(1),
                jsonDataConfig.getEmailFromJson(1),
                Randomizer.generateRandomString(33)
        );
        Assert.assertTrue(editAccountPage.isAlertTelephoneDisplayed());
    }

    public MyAccountPage loginUser(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(1),
                jsonDataConfig.getPasswordFromJson(1));
        return myAccountPage;
    }

    public void logoutUser() throws InterruptedException {
        if (getHomePage().isExistMyAccountDropdownOption("My Account")) {
            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
            logoutPage.logout();
        } else getHomePage();
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