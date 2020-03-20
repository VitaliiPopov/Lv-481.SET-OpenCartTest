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
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }

        getHomePage().goToHomePage();
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 1)
    public void loginNotExistedUserTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        loginPage.login("www@gmail.com", "jkjk");
        Assert.assertTrue(loginPage.isAlertDisplayed());
        Thread.sleep(2000);

    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 2)
    public void loginWithEmptyFieldsTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isAlertDisplayed());
        Thread.sleep(2000);
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 3)
    public void loginExistedUserTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        Assert.assertTrue(myAccountPage.getTitleMyAccountText().equals("My Account"));
        Thread.sleep(2000);
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 4)
    public void changePasswordToEmptyTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("", "");
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
        Thread.sleep(2000);
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 5)
    public void changePasswordToShortTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        changePasswordPage.changePassword("ttt", "ttt");
        Assert.assertTrue(changePasswordPage.isAlertPasswordDisplayed());
        Thread.sleep(2000);
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
        Thread.sleep(2000);
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
        Thread.sleep(2000);
    }

    @Parameters({"loginDropdownText"})
    @Test(priority = 8)
    public void changePasswordTest(String loginDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(loginDropdownText);
        MyAccountPage myAccountPage = loginPage.login("test481@gmail.com", "test");
        ChangePasswordPage changePasswordPage = myAccountPage.clickChangePasswordLink();
        myAccountPage = changePasswordPage.changePassword("test", "test");
        Assert.assertTrue(myAccountPage.isSuccessAlertDisplayed());
        Thread.sleep(2000);
    }

    @Test(priority = 9)
    public void logoutUserSideBarTest() throws InterruptedException {
        ChangePasswordPage accountPage = new ChangePasswordPage(Driver.getDriver());
        AccountLogoutPage logoutPage = accountPage.getAccountSidebarComponent().clickLogoutSideBar();
        HomePage homePage = logoutPage.logout();
        Thread.sleep(5000);
    }


    @Parameters({"logoutAccountDropdownText"})
    @Test(priority = 10)
    public void logoutUserHeaderTest(String logoutAccountDropdownText) throws InterruptedException {
        AccountLogoutPage logoutPage = getHomePage().goToLogoutPage(logoutAccountDropdownText);
        Thread.sleep(5000);

//        if (getHomePage().getMyAccountDropdown().isExistDropdownOptionByPartialName("My Account")) {
//            System.out.println("YES!");
//            AccountLogoutPage logoutPage = getHomePage().goToLogoutPage(mySecondAccountDropdownText);
//            Thread.sleep(5000);
//        }
    }
}
