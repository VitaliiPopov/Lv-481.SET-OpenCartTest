package test.register_test;

import com.opencart.pages.account.*;
import com.opencart.tools.*;
import com.opencart.tools.test_runner.AccountTestRunner;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.testng.annotations.*;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Listeners(com.opencart.tools.AccountListener.class)
public class EditAccountTest extends AccountTestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    AdminManager adminAccess = new AdminManager();

    @Parameters({"loginText"})
    @Test(priority = 1)
    @Description("Verify that user cant edit account with empty data")
    public void editAccountWithEmptyFieldsTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.clearFields();
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isFirstNameAlertPresent());
        Assert.assertEquals(true, editAccountPage.isLastNameAlertPresent());
        Assert.assertEquals(true, editAccountPage.isEmailAlertPresent());
        Assert.assertEquals(true, editAccountPage.isTelephoneAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    @Description("Verify that user cant edit account with empty firstname")
    public void editAccountWithEmptyFirstnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editFirstnameField("");
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isFirstNameAlertPresent());
    }

    //firstname longer than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 3)
    @Description("Verify that user cant edit account with long firstname (more than 32 symbols)")
    public void editAccountWithLongFirstnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editFirstnameField(randomAlphabetic(33));
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isFirstNameAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    @Description("Verify that user cant edit account with empty lastname")
    public void editAccountWithEmptyLastnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editLastnameField("");
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isLastNameAlertPresent());
    }

    //lastname longer than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 5)
    @Description("Verify that user cant edit account with long lastname (more than 32 symbols)")
    public void editAccountWithLongLastnameTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editLastnameField(randomAlphabetic(33));
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isLastNameAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 6)
    @Description("Verify that user cant edit account with empty email")
    public void editAccountWithEmptyEmailTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editEmailField("");
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isEmailAlertPresent());
    }

    @Parameters({"loginText", "editAccountText"})
    @Test(priority = 7)
    @Description("Verify that user cant edit account with email without @ symbol")
    public void editAccountEmailWithoutAtSymbolTest(String loginText, String editAccountText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editEmailField(randomAlphabetic(5));
        editAccountPage.clickEditButton();
        Assert.assertTrue(editAccountPage.getTitleAccountInformationText().equals(editAccountText));
    }

    @Parameters({"loginText"})
    @Test(priority = 8)
    @Description("Verify that user cant edit account with email without . symbol")
    public void editAccountEmailWithoutDotSymbolTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editEmailField(randomAlphabetic(5)+"@"+randomAlphabetic(5));
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isEmailAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 9)
    @Description("Verify that user cant edit account with existed email")
    public void editAccountWithExistedEmailTest(String loginText) throws InterruptedException {
        String email = adminAccess.getExistedEmailFromAdmin();
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editEmailField(email);
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isWarningPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 10)
    @Description("Verify that user cant edit account with empty telephone")
    public void editAccountWithEmptyTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editTelephoneField("");
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isTelephoneAlertPresent());
    }

    //telephone shorter than 3 characters
    @Parameters({"loginText"})
    @Test(priority = 11)
    @Description("Verify that user cant edit account with short telephone (less than 3 symbols)")
    public void editAccountWithShortTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editTelephoneField(randomAlphabetic(2));
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isTelephoneAlertPresent());
    }

    //telephone longer than 32 characters
    @Parameters({"loginText"})
    @Test(priority = 12)
    @Description("Verify that user cant edit account with long telephone (more than 32 symbols)")
    public void editAccountWithLongTelephoneTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        EditAccountPage editAccountPage = myAccountPage.clickEditMyAccountLink();
        editAccountPage.editTelephoneField(randomAlphabetic(33));
        editAccountPage.clickEditButton();
        Assert.assertEquals(true, editAccountPage.isTelephoneAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 13)
    @Description("Verify complex functionality of login, registration and editing user data")
    public void complexScenario(String loginText) throws InterruptedException {
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
        Assert.assertEquals(true, myAccountPage.isSuccessAlertPresent());

        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(newPassword, newPassword);
        Assert.assertEquals(true, myAccountPage.isSuccessAlertPresent());

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

    public MyAccountPage loginUser(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getPasswordFromJson(0));
        return myAccountPage;
    }
}