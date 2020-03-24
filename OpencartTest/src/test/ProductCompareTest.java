package test;

import com.opencart.pages.HomePage;
import com.opencart.pages.comparison.ComparisonPage;
import com.opencart.pages.comparison.EmptyComparisonPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.pages.product_table.CartPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProductCompareTest extends TestRunner {

    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @Parameters({"myAccountDropdownText", "search", "productName1"})
    @Test(priority = 1)
    public void AddProductToCompareFromSearchByAlert(String myAccountDropdownText, String search, String firstName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .goToHomePage()
                .SearchProduct(search)
                .clickProductComponentCompareButtonByName(firstName)
                .clickProductComparisonLink();
        Assert.assertEquals(firstName, comparisonPage.getFirstProductName());
    }

    @Test(priority = 2)
    public void AddProductToCompareFromMainPage() {

    }

    @Test(priority = 3)
    public void AddProductFromProductPage() {

    }

    @Parameters({"myAccountDropdownText", "search", "productName1"})
    @Test(priority = 4)
    public void AddSameProductTwoTimes(String myAccountDropdownText, String searchName, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .goToHomePage()
                .SearchProduct(searchName)
                .AddTwoSameProductToComparison(productName)
                .clickProductComparisonLink();
        Assert.assertEquals(1, comparisonPage.getProductsCount());
    }

    @Parameters({"myAccountDropdownText", "search", "productName1", "productName2"})
    @Test(priority = 5)
    public void CleanProductFromCompare(String myAccountDropdownText, String searchName, String firstName, String secondName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .goToHomePage()
                .SearchProduct(searchName)
                .clickProductComponentCompareButtonByName(firstName)
                .clickProductComponentCompareButtonByName(secondName)
                .clickProductComparisonLink()
                .clickLastRemoveButton();
        Assert.assertTrue(comparisonPage.getComparisonPageAlertComponent().isRemoveAlertDisplayed());
    }

    @Parameters({"myAccountDropdownText", "search", "productName1"})
    @Test(priority = 6)
    public void AddToCartFromComparisonPage(String myAccountDropdownText, String searchName, String productName) {
        CartPage cartPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .goToHomePage()
                .SearchProduct(searchName)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLink()
                .clickFirstAddToCartButton()
                .clickShoppingCartLink();
        //Assert.
    }

    @Parameters({"myAccountDropdownText", "search", "productName1"})
    @Test(priority = 7)
    public void GoToProductPageFromComparisonPage(String myAccountDropdownText, String searchName, String productName) {
        ProductPage productPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .goToHomePage()
                .SearchProduct(searchName)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLink()
                .clickFirstProductName();
        Assert.assertEquals(productPage.getProductName(), productName);
    }
}