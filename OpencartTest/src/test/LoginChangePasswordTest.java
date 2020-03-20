package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.AccountLogoutPage;
import com.opencart.pages.account.ChangePasswordPage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.tools.Driver;
import com.opencart.tools.ExcelDataConfig;
import com.opencart.tools.TestRunner;
import com.opencart.tools.Utility;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginChangePasswordTest extends TestRunner {
    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (getHomePage().isExistMyAccountDropdownOption("My Account")){
            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage("Logout");
            logoutPage.logout();
        }
        else getHomePage();

        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 1)
    public void loginNotExistedUserTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        loginPage.login("www@gmail.com", "jkjk");
        Assert.assertTrue(loginPage.isAlertDisplayed());

    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 2)
    public void loginWithEmptyFieldsTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isAlertDisplayed());
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 3)
    public void loginExistedUserTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        Assert.assertTrue(myAccountPage.getTitleMyAccountText().equals("My Account"));
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 4)
    public void changePasswordToEmptyTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("", "");
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 5)
    public void changePasswordToShortTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("ttt", "ttt");
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
    }

    //bug
    @Parameters({"loginDropdownText"})
    @Test(priority = 6)
    public void changePasswordToLongTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("tttttttttttttttttttttttt", "tttttttttttttttttttttttt");
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed()); //bug
    }

    //bug
    @Parameters({"loginDropdownText"})
    @Test(priority = 7)
    public void changePasswordWrongConfirmTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("test", "testtest");
        Assert.assertTrue(changePasswordPage.isAlertConfirmDisplayed()); //bug
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 8)
    public void changePasswordTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        myAccountPage = changePasswordPage.changePassword("test", "test");
        Assert.assertTrue(myAccountPage.isSuccessAlertDisplayed());
    }
}