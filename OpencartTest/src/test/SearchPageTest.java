package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.search.SearchCriteriaComponent;
import com.opencart.pages.search.SearchPage;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.Randomizer;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Collections;

import java.util.*;

public class SearchPageTest extends TestRunner {

    private JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    private SearchPage searchPage;
    private ArrayList<String> defaultSorted;
    private ArrayList<String> a_z_byNameSorted;

    private ArrayList<Double> priceList;
    private ArrayList<Double> byPriceSorted;

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void login(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0));
        myAccountPage.goToHomePage();
    }

    @AfterClass
    public void afterClass() {
        //clean all fields
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

        Assert.assertTrue(getHomePage()
                .searchProduct(lowerProductName)
                .getSearchCriteriaComponent()
                .searchLableContainSearchText(lowerProductName));
    }

    @Test(priority = 4)
    public void checkUncorrectFieldResultMessage() throws InterruptedException {
        String searchText = Randomizer.generateRandomString(9);
        searchPage = getHomePage().searchProduct(searchText);
        Assert.assertTrue(getHomePage()
                .searchProduct(searchText)
                .getemptyResultMessageText()
                .contains("no product"));
    }

    @Test(priority = 5)
    public void checkEmptyFieldResultMessage() throws InterruptedException {
        Assert.assertTrue(getHomePage()
                .searchProduct("")
                .getemptyResultMessageText()
                .contains("no product"));
    }

    @Parameters({"lowerSearchText", "categoryOption"})
    @Test(priority = 6)
    public void checkCategoriesCheckbox(String lowerSearchText, String categoryOption) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);
        SearchCriteriaComponent searchCriteriaComponent = searchPage.getSearchCriteriaComponent();

        Assert.assertFalse(searchCriteriaComponent.isSubcategoriesCheckboxEnabled());
        searchCriteriaComponent.clickCategoriesDropdown(categoryOption);
        searchCriteriaComponent.clickSubcategoriesCheckbox();
        Assert.assertTrue( searchCriteriaComponent.isSubcategoriesCheckboxEnabled());
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 7)
    public void checkProductCountFromLable(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);
        int listSize = searchPage.getProductContainerComponentsSize();
        Assert.assertEquals(listSize, searchPage.getProductDisplayCriteriaComponent().getListSizeCountFromLable());
    }

    @Parameters({"allProducts", "showOption"})
    @Test(priority = 8)
    public void checkShowDropbox(String allProducts, String showOption) throws InterruptedException {
        searchPage = getHomePage().searchProduct(allProducts);
        searchPage.getProductDisplayCriteriaComponent().clickShowDropdown(showOption);
        Assert.assertEquals(searchPage.getProductDisplayCriteriaComponent().getShowCountFromLable(), Integer.parseInt(showOption));
    }

    @Parameters({"allProducts", "showOption"})
    @Test(priority = 9)

    public void checkPagesCount(String allProducts, String showOption) throws InterruptedException {
        searchPage = getHomePage().searchProduct(allProducts);
        searchPage.getProductDisplayCriteriaComponent().clickShowDropdown(showOption);

        int expectedPagesCount = searchPage.getProductContainerComponentsSize() / Integer.parseInt(showOption) + 1;
        Assert.assertEquals(searchPage.getProductDisplayCriteriaComponent().getPagesCountFromLable(), expectedPagesCount);
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 10)
    public void checkListAndGridResult(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);

        ArrayList<String> gridModeProdNames = searchPage.getProductComponentNamesList();
        searchPage.getProductDisplayCriteriaComponent().clickListButton();
        ArrayList<String> listModeProdNames = searchPage.getProductComponentNamesList();

        Assert.assertEquals(gridModeProdNames, listModeProdNames);
        searchPage.getProductDisplayCriteriaComponent().clickGridButton();
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 11)
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
    @Test(priority = 12)
    public void checkSortByZ_A_Name(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);
        defaultSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(defaultSorted);
        Collections.sort(defaultSorted, Collections.reverseOrder());
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Name (Z - A)");

        a_z_byNameSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(a_z_byNameSorted);
        Assert.assertEquals(a_z_byNameSorted, defaultSorted);
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 13)
    public void checkSortByPrice(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);
        priceList = searchPage.getProductComponentPriceList();
        Collections.sort(priceList);
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Price (Low > High)");
        byPriceSorted = searchPage.getProductComponentPriceList();
        Assert.assertEquals(priceList, byPriceSorted);
    }

    @Parameters({"lowerSearchText"})
    @Test(priority = 14)
    public void checkDescriptionCheckbox(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);
        searchPage.getSearchCriteriaComponent().clickDescriptionsCheckbox();
        Assert.assertTrue(searchPage
                .getProductPageDescription(0)
                .toLowerCase()
                .contains(lowerSearchText));
    }
    @Parameters({"lowerSearchText"})
    @Test(priority = 15)
    public void checkSubcategiriesCheckboxImpact(String lowerSearchText) throws InterruptedException {
//        searchPage = getHomePage().searchProduct(lowerSearchText);
//        SearchCriteriaComponent searchCriteriaComponent = searchPage.getSearchCriteriaComponent();
//        boolean actual = searchCriteriaComponent.isSubcategoriesCheckboxEnabled();
//        Assert.assertFalse(actual);
//
//        searchCriteriaComponent.clickCategoriesDropdown(categoryOption);
//        searchCriteriaComponent.clickSubcategoriesCheckbox();
//        actual = searchCriteriaComponent.isSubcategoriesCheckboxEnabled();
//        Assert.assertTrue(actual);

        //репорт
    }
}

