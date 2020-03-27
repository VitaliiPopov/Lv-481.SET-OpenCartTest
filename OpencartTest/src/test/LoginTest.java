//package test;
//
//import com.opencart.pages.account.LoginPage;
//import com.opencart.pages.account.MyAccountPage;
//import com.opencart.pages.search.SearchPage;
//import com.opencart.tools.TestRunner;
//import org.testng.Assert;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//public class LoginTest extends TestRunner {
//
//    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");
//
//    @Parameters({"myAccountDropdownText"})
//    @Test(priority = 1)
//    public void Login(String myAccountDropdownText) throws InterruptedException {
//        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
//        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
//        myAccountPage.goToHomePage();
//    }
//
//    @Parameters({"search", "productName"})
//    @Test(priority = 2)
//    public void AddProductToCompareFromSearch(String search, String productName) throws InterruptedException {
//        SearchPage searchPage = getHomePage().searchProduct(search);
//        Thread.sleep(1000);
//        searchPage.clickProductComponentCompareButtonByName(productName);
//        Thread.sleep(1000);
//        Assert.assertTrue(searchPage.isAlertDisplayed());
//    }
//
//    @Test(priority = 3)
//    public void CheckComparisonTable() {
//
//    }
//
//
//}
