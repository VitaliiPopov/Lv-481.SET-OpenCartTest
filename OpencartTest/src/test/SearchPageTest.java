package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.search.ProductContainersComponent;
import com.opencart.pages.search.ProductDisplayCriteriaComponent;
import com.opencart.pages.search.SearchCriteriaComponent;
import com.opencart.pages.search.SearchPage;
import com.opencart.tools.ExcelDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SearchPageTest extends TestRunner {

    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");
    SearchPage searchPage;

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void Login(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        myAccountPage.goToHomePage();
    }



    @Parameters({"searchText", "lowerSearchText"})
    @Test(priority = 2)
    public void checkLowerCase( String searchText, String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().SearchProduct(searchText);
        ArrayList<String> upperCaseSearchText = searchPage.getProductComponentNamesList();
        searchPage = getHomePage().SearchProduct(lowerSearchText);
        ArrayList<String> lowerCaseSearchText = searchPage.getProductComponentNamesList();
        Assert.assertEquals(upperCaseSearchText, lowerCaseSearchText);
    }

    @Parameters({"lowerProductName"})
    @Test(priority = 3)
    public void checkLableContainsSearchText(String lowerProductName) throws InterruptedException {
        searchPage = getHomePage().SearchProduct(lowerProductName);

        boolean actual = searchPage.getSearchCriteriaComponent().searchLableContainSearchText(lowerProductName);
        Assert.assertTrue(actual);
    }

    @Test(priority = 4)
    public void checkEmptyResultMessage() throws InterruptedException {
        String searchText = SearchPage.Randomizer.generateRandomString(9);
        searchPage = getHomePage().SearchProduct(searchText);
        boolean actual = searchPage.isEmptyResult();
        Assert.assertTrue(actual);
        searchPage.SearchProduct("");
        actual = searchPage.isEmptyResult();
        Assert.assertTrue(actual);
    }

    @Parameters({"lowerSearchText", "categoryOption"})
    @Test(priority = 5)
    public void checkCategoriesCheckbox(String lowerSearchText, String categoryOption) throws InterruptedException {
        searchPage = getHomePage().SearchProduct(lowerSearchText);
        SearchCriteriaComponent searchCriteriaComponent = searchPage.getSearchCriteriaComponent();
        boolean actual = searchCriteriaComponent.isSubcategoriesCheckboxEnabled();
        Assert.assertFalse(actual);
        boolean selected = searchCriteriaComponent.isDropdownSelected(searchCriteriaComponent.GetCategoriesDropdown(), categoryOption);

        searchCriteriaComponent.clickCategoriesDropdown(categoryOption);
        actual = searchCriteriaComponent.isSubcategoriesCheckboxEnabled();
        Assert.assertTrue(actual);
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 6)
    public void checkProductCountFromLable(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().SearchProduct(lowerSearchText);
        int listSize = searchPage.getListSize();
        int productCountFromLable = searchPage.getProductDisplayCriteriaComponent().getProductCountFromLable();
        Assert.assertEquals(listSize, productCountFromLable);
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 7)
    public void checkListAndGridResult(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().SearchProduct(lowerSearchText);

        ArrayList<String> gridModeProdNames = searchPage.getProductComponentNamesList();
        searchPage.getProductDisplayCriteriaComponent().clickListButton();
        ArrayList<String> listModeProdNames = searchPage.getProductComponentNamesList();
        Assert.assertEquals(gridModeProdNames, listModeProdNames);
    }

}

