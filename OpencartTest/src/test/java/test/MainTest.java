package test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SuccessLoginPage;
import tools.ExcelDataConfig;
import tools.Utility;

public class MainTest extends TestRunner {


    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(driver);
        }
    }

    @Parameters({"myAccountDropdownText", "expectedResultGoToLoginPageTest"})
    @Test(priority = 1)
    public void goToLoginPageTest(String myAccountDropdownText, String expectedResultGoToLoginPageTest) {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        Assert.assertEquals(loginPage.getTitleLoginBlockText(), expectedResultGoToLoginPageTest);
    }

    @Parameters("expectedResultLoginTest")
    @Test(priority = 2)
    public void loginTest(String expectedResultLoginTest) {
        SuccessLoginPage successLoginPage = getloginPage().login(excelDataConfig.getData(0, 1, 0), excelDataConfig.getData(0, 1, 1));
        Assert.assertEquals(successLoginPage.getSuccessLoginPageTitleText(), expectedResultLoginTest);
    }

}
