package test.search_test;

import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.search.SearchCriteriaComponent;
import com.opencart.pages.search.SearchPage;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Collections;

import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


public class SearchPageTest extends TestRunner {

    private SearchPage searchPage;

    //lists for sorting by product name
    private ArrayList<String> defaultSorted;
    private ArrayList<String> a_z_byNameSorted;

    //lists for sorting by product price
    private ArrayList<Double> priceList;
    private ArrayList<Double> byPriceSorted;

    //check if product names are the same when lowercase text and uppercase text
    @Parameters({"searchText", "lowerSearchText"})
    @Test(priority = 2)
    public void checkLowerCase(String searchText, String lowerSearchText) {
        ArrayList<String> upperCaseSearchText = getHomePage()
                .searchProduct(searchText)
                .getProductComponentNamesList();
        ArrayList<String> lowerCaseSearchText = getHomePage()
                .searchProduct(lowerSearchText)
                .getProductComponentNamesList();
        Assert.assertEquals(upperCaseSearchText, lowerCaseSearchText);
    }

    //check if search label contain text from searsh field
    @Parameters({"lowerProductName"})
    @Test(priority = 3)
    public void searchLabelContainSearchText(String lowerProductName) throws InterruptedException {
        Assert.assertTrue(getHomePage()
                .searchProduct(lowerProductName)
                .getSearchCriteriaComponent()
                .searchLabelContainSearchText(lowerProductName));
    }

    //check that "no product" message appears after entering random string to search field
    @Test(priority = 4)
    public void checkUncorrectFieldResultMessage() throws InterruptedException {
        Assert.assertTrue(getHomePage()
                .searchProduct(randomAlphabetic(9))
                .getemptyResultMessageText()
                .contains("no product"));
    }

    //check that "no product" message appears after entering empty string to search field
    @Test(priority = 5)
    public void checkEmptyFieldResultMessage() throws InterruptedException {
        Assert.assertTrue(getHomePage()
                .searchProduct("")
                .getemptyResultMessageText()
                .contains("no product"));
    }

    //check if categories checkbox is disabled when categories dropdown is default
    @Parameters({"lowerSearchText", "categoryOption"})
    @Test(priority = 6)
    public void checkCategoriesCheckboxDisabled(String lowerSearchText, String categoryOption) throws InterruptedException {
        Assert.assertFalse(getHomePage()
                .searchProduct(lowerSearchText)
                .getSearchCriteriaComponent()
                .isSubcategoriesCheckboxEnabled());
    }

    //check if categories checkbox is enabled when categories dropdown is not default
    @Parameters({"lowerSearchText", "categoryOption"})
    @Test(priority = 7)
    public void checkCategoriesCheckboxEnabled(String lowerSearchText, String categoryOption) {
        SearchCriteriaComponent searchCriteriaComponent = getHomePage()
                .searchProduct(lowerSearchText)
                .getSearchCriteriaComponent();
        searchCriteriaComponent.clickCategoriesDropdown(categoryOption);
        searchCriteriaComponent.clickSubcategoriesCheckbox();
        Assert.assertTrue(searchCriteriaComponent.isSubcategoriesCheckboxEnabled());
    }

    //check if product count in label on the bottom of the page and size of product list are equal
    @Parameters({"lowerSearchText"})
    @Test(priority = 8)
    public void checkProductCountFromLablel(String lowerSearchText) {
        Assert.assertEquals(
                getHomePage()
                        .searchProduct(lowerSearchText)
                        .getProductContainerComponentsSize(),
                getHomePage()
                        .searchProduct(lowerSearchText)
                        .getProductDisplayCriteriaComponent()
                        .getListSizeCountFromLabel());
    }

    //check if pages count in label on the bottom of the page and expected pages count are equal
    @Parameters({"allProducts", "showOption"})
    @Test(priority = 9)
    public void checkPagesCount(String allProducts, String showOption) {
        searchPage = getHomePage().searchProduct(allProducts);
        searchPage.getProductDisplayCriteriaComponent().clickShowDropdown(showOption);
        Assert.assertEquals(
                searchPage
                        .getProductDisplayCriteriaComponent()
                        .getPagesCountFromLabel(),
                searchPage
                        .getCalculatedPagesCount(showOption));
    }

    //check if products count in label on the bottom of the page and products count in Show dropbox are equal
    @Parameters({"allProducts", "showOption"})
    @Test(priority = 10)
    public void checkShowDropbox(String allProducts, String showOption) {
        searchPage = getHomePage().searchProduct(allProducts);
        searchPage.getProductDisplayCriteriaComponent().clickShowDropdown(showOption);
        Assert.assertEquals(searchPage
                .getProductDisplayCriteriaComponent()
                .getShowCountFromLabel(),
                Integer.parseInt(showOption));
    }

    //check if product names list in default grid mode and product names list in list mode are equal
    @Parameters({"lowerSearchText"})
    @Test(priority = 11)
    public void checkListAndGridResult(String lowerSearchText) {
        searchPage = getHomePage().searchProduct(lowerSearchText);

        ArrayList<String> gridModeProdNames = searchPage.getProductComponentNamesList();
        searchPage.getProductDisplayCriteriaComponent().clickListButton();
        ArrayList<String> listModeProdNames = searchPage.getProductComponentNamesList();

        Assert.assertEquals(gridModeProdNames, listModeProdNames);
        searchPage.getProductDisplayCriteriaComponent().clickGridButton();
    }

    //check if list is correctly sorted by name after clicking "Name (A - Z)" option in SortBy dropdown
    @Parameters({"lowerSearchText"})
    @Test(priority = 12)
    public void checkSortByA_Z_Name(String lowerSearchText) {

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

    //check if list is correctly sorted by name after clicking "Name (Z - A)" option in SortBy dropdown
    @Parameters({"lowerSearchText"})
    @Test(priority = 13)
    public void checkSortByZ_A_Name(String lowerSearchText) {
        searchPage = getHomePage().searchProduct(lowerSearchText);
        defaultSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(defaultSorted);
        Collections.sort(defaultSorted, Collections.reverseOrder());
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Name (Z - A)");

        a_z_byNameSorted = searchPage.getProductComponentNamesList();
        searchPage.toLowerCaseProductList(a_z_byNameSorted);
        Assert.assertEquals(a_z_byNameSorted, defaultSorted);
    }

    //check if list are correctly sorted by price after clicking "Price (Low > High)" option in SortBy dropdown
    @Parameters({"lowerSearchText"})
    @Test(priority = 14)
    public void checkSortByPrice(String lowerSearchText) {
        searchPage = getHomePage().searchProduct(lowerSearchText);
        priceList = searchPage.getProductComponentPriceList();
        Collections.sort(priceList);
        searchPage.getProductDisplayCriteriaComponent().clickSortByDropdown("Price (Low > High)");
        byPriceSorted = searchPage.getProductComponentPriceList();
        Assert.assertEquals(priceList, byPriceSorted);
    }

    //check if search in descriptions is correct after clicking description checkbox
    @Parameters({"lowerSearchText"})
    @Test(priority = 15)
    public void checkDescriptionCheckbox(String lowerSearchText) throws InterruptedException {
        searchPage = getHomePage().searchProduct(lowerSearchText);
        searchPage.getSearchCriteriaComponent().clickDescriptionsCheckbox();
        Assert.assertTrue(searchPage
                .getProductPageDescription(0)
                .toLowerCase()
                .contains(lowerSearchText));
    }
}

