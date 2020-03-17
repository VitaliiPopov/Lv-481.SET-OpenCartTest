package test;

import opencart.pages.HomePage;
import opencart.pages.account.LoginPage;
import opencart.pages.account.MyAccountPage;
import opencart.tools.Driver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import opencart.tools.ExcelDataConfig;
import opencart.tools.Utility;

public class MainTest extends TestRunner {

    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }
    }

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void loginTest(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        HomePage homePage = myAccountPage.goToHomePage();
        Thread.sleep(2000);
    }


}
