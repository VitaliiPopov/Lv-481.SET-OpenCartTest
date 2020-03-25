package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.search.SearchCriteriaComponent;
import com.opencart.pages.search.SearchPage;
import com.opencart.tools.ExcelDataConfig;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.Randomizer;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.Collections;

import java.util.*;

public class SearchPageTest extends TestRunner {

    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");
    private SearchPage searchPage;
    private ArrayList<String> defaultSorted;
    private ArrayList<String> a_z_byNameSorted;

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void login(String myAccountDropdownText) {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        HomePage homePage = myAccountPage.goToHomePage();
    }


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
        searchPage = getHomePage().searchProduct(searchText);
        ArrayList<String> upperCaseSearchText = searchPage.getProductComponentNamesList();
        searchPage = getHomePage().searchProduct(lowerSearchText);
        ArrayList<String> lowerCaseSearchText = searchPage.getProductComponentNamesList();
        Assert.assertEquals(upperCaseSearchText, lowerCaseSearchText);
    }

    @Parameters({"lowerProductName"})
    @Test(priority = 3)
    public void checkLableContainsSearchText(String lowerProductName) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerProductName);

        boolean actual = searchPage.getSearchCriteriaComponent().searchLableContainSearchText(lowerProductName);
        Assert.assertTrue(actual);
    }

    @Test(priority = 4)
    public void checkEmptyResultMessage() throws InterruptedException {
        String searchText = Randomizer.generateRandomString(9);
        searchPage = getHomePage().searchProduct(searchText);
        searchPage.searchProduct("");
        Assert.assertTrue(searchPage.getemptyResultMessageText().contains("no product"));
    }

    @Parameters({"lowerSearchText", "categoryOption"})
    @Test(priority = 5)
    public void checkCategoriesCheckbox(String lowerSearchText, String categoryOption) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);
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
        searchPage = getHomePage().searchProduct(lowerSearchText);
        int listSize = searchPage.getProductContainerComponentsSize();
        int productCountFromLable = searchPage.getProductDisplayCriteriaComponent().getProductCountFromLable();
        Assert.assertEquals(listSize, productCountFromLable);
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 7)
    public void checkListAndGridResult(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);

        ArrayList<String> gridModeProdNames = searchPage.getProductComponentNamesList();
        searchPage.getProductDisplayCriteriaComponent().clickListButton();
        ArrayList<String> listModeProdNames = searchPage.getProductComponentNamesList();
        Assert.assertEquals(gridModeProdNames, listModeProdNames);
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 8)
    public void checkSortByA_Z_Name(String lowerSearchText) throws InterruptedException {

        searchPage = getHomePage().searchProduct(lowerSearchText);
        defaultSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(defaultSorted);
        Collections.sort(defaultSorted);
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Name (A - Z)");
        searchPage.getProductContainersComponents();

        a_z_byNameSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(a_z_byNameSorted);
        Assert.assertEquals(a_z_byNameSorted, defaultSorted);
    }
    @Parameters({"lowerSearchText"})
    @Test(priority = 9)
    public void checkSortByZ_A_Name(String lowerSearchText) throws InterruptedException {

        searchPage = getHomePage().searchProduct(lowerSearchText);
        defaultSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(defaultSorted);
        Collections.sort(defaultSorted,Collections.reverseOrder());
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Name (Z - A)");
        searchPage.getProductContainersComponents();

        a_z_byNameSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(a_z_byNameSorted);
        Assert.assertEquals(a_z_byNameSorted, defaultSorted);
    }
    @Parameters({"lowerSearchText"})
    @Test(priority = 9)
    public void checkSortByPrice(String lowerSearchText) throws InterruptedException {

        searchPage = getHomePage().searchProduct(lowerSearchText);
        defaultSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(defaultSorted);
        Collections.sort(defaultSorted,Collections.reverseOrder());
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Name (Z - A)");
        searchPage.getProductContainersComponents();

        a_z_byNameSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(a_z_byNameSorted);
        Assert.assertEquals(a_z_byNameSorted, defaultSorted);
    }

}

