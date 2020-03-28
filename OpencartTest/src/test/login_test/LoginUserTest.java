package test.login_test;

import com.opencart.pages.account.*;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.tools.*;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.testng.annotations.*;

import static org.apache.commons.lang.RandomStringUtils.*;

public class LoginUserTest extends TestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    AdminManager adminAccess = new AdminManager();

    @BeforeTest
    public void primaryRegistration() {
        RegisterPage registerPage = getHomePage().goToRegisterPage();
        SuccessRegisterPage success = registerPage.register(jsonDataConfig.getUserFromJson(0));
        success.goToAccountAfterRegistration();
        AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
        logoutPage.logout();
    }

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
            adminAccess.deleteCustomerFromAdmin(jsonDataConfig.getEmailFromJson(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"loginText"})
    @Test(priority = 1)
    @Description("Verify that not existed user cant login")
    public void loginNotExistedUserTest(String loginText) throws InterruptedException {
        String email = randomAlphabetic(5) + "@" + randomAlphabetic(3) + "." + randomAlphabetic(3);
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login(email, randomAlphabetic(10));
        Assert.assertEquals(true, loginPage.isAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 2)
    @Description("Verify that user cant login with empty fields")
    public void loginWithEmptyFieldsTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login("", "");
        Assert.assertEquals(true, loginPage.isAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 3)
    @Description("Verify that user cant login with empty email")
    public void loginWithEmptyEmailTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login("", randomAlphabetic(3));
        Assert.assertEquals(true, loginPage.isAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 4)
    @Description("Verify that user cant login with empty password")
    public void loginWithEmptyPasswordTest(String loginText) throws InterruptedException {
        String email = randomAlphabetic(5) + "@" + randomAlphabetic(3) + "." + randomAlphabetic(3);
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login(email, "");
        Assert.assertEquals(true, loginPage.isAlertPresent());
    }

    @Parameters({"loginText"})
    @Test(priority = 5)
    @Description("Verify that user cant login with upper case data")
    public void loginCaseSensitiveFieldsTest(String loginText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginText);
        loginPage.login(
                jsonDataConfig.getEmailFromJson(0).toUpperCase(),
                jsonDataConfig.getPasswordFromJson(0).toUpperCase());
        Assert.assertEquals(true, loginPage.isAlertPresent());
    }

    public void logoutUser() throws InterruptedException {
        if (getHomePage().isExistMyAccountDropdownOption("My Account")){
            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage();
            logoutPage.logout();
        }
        else getHomePage();
    }
}