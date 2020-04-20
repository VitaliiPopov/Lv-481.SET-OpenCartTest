package test.login_test;

import com.opencart.pages.account.*;
import com.opencart.tools.*;
import com.opencart.tools.test_runner.AccountTestRunner;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.testng.annotations.*;

import static org.apache.commons.lang3.RandomStringUtils.*;

public class ChangePasswordTest extends AccountTestRunner {

    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @Parameters({"loginText"})
    @Test(priority = 1)
    @Description("Verify that user cant change password to empty")
    public void changePasswordToEmptyTest(String loginText) {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.clickChangePasswordButton();
        Assert.assertTrue(changePasswordPage.isPasswordAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    @Description("Verify that user cant change password with wrong confirm")
    public void changePasswordWrongConfirmTest(String loginText) {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(randomAlphabetic(5), randomAlphabetic(5));
        Assert.assertEquals(true, changePasswordPage.isConfirmAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 3)
    @Description("Verify that user cant change password to short")
    public void changePasswordToShortTest(String loginText) {
        MyAccountPage myAccountPage = loginUser(loginText);
        String password = randomAlphabetic(3);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(password, password);
        Assert.assertEquals(true, changePasswordPage.isPasswordAlertPresent());
    }

    public MyAccountPage loginUser(String loginDropdownText) {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getPasswordFromJson(0));
        return myAccountPage;
    }

}
