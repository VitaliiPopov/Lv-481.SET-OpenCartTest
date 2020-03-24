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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.Collections;

import java.util.*;
import java.util.stream.Collectors;

public class SearchPageTest extends TestRunner {

    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");
    private SearchPage searchPage;
    private ArrayList<String> defaultSorted;
    private ArrayList<String> a_z_byNameSorted;

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void Login(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        myAccountPage.goToHomePage();
    }


    @Parameters({"searchText", "lowerSearchText"})
    @Test(priority = 2)
    public void checkLowerCase(String searchText, String lowerSearchText) throws InterruptedException {
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
        String searchText = SearchPage.generateRandomString(9);
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

    @Parameters({"lowerSearchText"})
    @Test(priority = 8)
    public void checkSortByA_Z_Name(String lowerSearchText) throws InterruptedException {

        searchPage = getHomePage().SearchProduct(lowerSearchText);
        defaultSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(defaultSorted);
        Collections.sort(defaultSorted);
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Name (A - Z)");
        searchPage.InitializeProductContainers();

        a_z_byNameSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(a_z_byNameSorted);
        Assert.assertEquals(a_z_byNameSorted, defaultSorted);
    }
    @Parameters({"lowerSearchText"})
    @Test(priority = 9)
    public void checkSortByZ_A_Name(String lowerSearchText) throws InterruptedException {

        searchPage = getHomePage().SearchProduct(lowerSearchText);
        defaultSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(defaultSorted);
        Collections.sort(defaultSorted,Collections.reverseOrder());
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Name (Z - A)");
        searchPage.InitializeProductContainers();

        a_z_byNameSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(a_z_byNameSorted);
        Assert.assertEquals(a_z_byNameSorted, defaultSorted);
    }
    @Parameters({"lowerSearchText"})
    @Test(priority = 9)
    public void checkSortByPrice(String lowerSearchText) throws InterruptedException {

        searchPage = getHomePage().SearchProduct(lowerSearchText);
        defaultSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(defaultSorted);
        Collections.sort(defaultSorted,Collections.reverseOrder());
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Name (Z - A)");
        searchPage.InitializeProductContainers();

        a_z_byNameSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(a_z_byNameSorted);
        Assert.assertEquals(a_z_byNameSorted, defaultSorted);
    }

}

