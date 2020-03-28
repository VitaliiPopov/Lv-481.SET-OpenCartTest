package test.login_test;

import com.opencart.pages.account.*;
import com.opencart.tools.*;
import org.junit.Assert;
import org.testng.annotations.*;
import static org.apache.commons.lang3.RandomStringUtils.*;


@Listeners(com.opencart.tools.AccountListener.class)
public class ChangePasswordTest extends AccountTestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @Parameters({"loginText"})
    @Test(priority = 1)
    public void changePasswordToEmptyTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.clickChangePasswordButton();
        Assert.assertEquals(true, changePasswordPage.isPasswordAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    public void changePasswordToShortTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        String password = randomAlphabetic(3);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(password, password);
        Assert.assertEquals(true, changePasswordPage.isPasswordAlertPresent());
    }

    //password longer than 20 characters
    @Parameters({"loginText"})
    @Test(priority = 3)
    public void changePasswordToLongTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        String password = randomAlphabetic(25);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(password, password);
        Assert.assertEquals(true, changePasswordPage.isPasswordAlertPresent());//bug
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    public void changePasswordWrongConfirmTest(String loginText) throws InterruptedException {
        MyAccountPage myAccountPage = loginUser(loginText);
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword(randomAlphabetic(5), randomAlphabetic(5));
        Assert.assertEquals(true, changePasswordPage.isConfirmAlertPresent());
    }

    public MyAccountPage loginUser(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login(
                jsonDataConfig.getEmailFromJson(0),
                jsonDataConfig.getPasswordFromJson(0));
        return myAccountPage;
    }

}
