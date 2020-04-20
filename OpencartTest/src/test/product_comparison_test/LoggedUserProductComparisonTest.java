package test.product_comparison_test;

import com.opencart.pages.cart.CartPage;
import com.opencart.pages.comparison.ComparisonPage;
import com.opencart.pages.comparison.EmptyComparisonPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.test_runner.CompareTestRunner;
import com.opencart.tools.JsonDataConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("ComparisonTest")
public class LoggedUserProductComparisonTest extends CompareTestRunner {

    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    @Description("Verify if user can add product to compare from search page")
    public void addProductToCompareFromSearch(String myAccountDropdownText, String search, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(7), jsonDataConfig.getPasswordFromJson(7))
                .searchProduct(search)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLink();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"myAccountDropdownText", "productName"})
    @Test
    public void addProductToCompareFromMainPage(String myAccountDropdownText, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(7), jsonDataConfig.getPasswordFromJson(7))
                .goToHomePage()
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLinkFromAlert();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"myAccountDropdownText", "productName"})
    @Test
    public void addProductToCompareFromProductPage(String myAccountDropdownText, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(7), jsonDataConfig.getPasswordFromJson(7))
                .goToHomePage()
                .clickProductNameLink(productName)
                .clickCompareButton()
                .goToComparisonPageFromAlert();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    public void addSameProductTwoTimes(String myAccountDropdownText, String searchName, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(7), jsonDataConfig.getPasswordFromJson(7))
                .searchProduct(searchName)
                .AddTwoSameProductToComparison(productName)
                .clickProductComparisonLinkFromAlert();
        Assert.assertEquals(comparisonPage.getProductsCount(), 1);
    }

    @Parameters({"myAccountDropdownText", "firstProductId", "expectedEmptyPageResult"})
    @Test
    public void cleanAllProductsFromComparison(String myAccountDropdownText, int firstId, String expected) {
        EmptyComparisonPage emptyComparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(7), jsonDataConfig.getPasswordFromJson(7))
                .goToHomePage()
                .addProductToCompareByJS(firstId)
                .clickProductComparisonLink()
                .clickFirstRemoveButton();
        Assert.assertTrue(emptyComparisonPage.isAlertDisplayed());
        Assert.assertEquals(emptyComparisonPage.getEmptyPageContentText(), expected);
    }

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    public void addToCartFromComparisonPage(String myAccountDropdownText, String searchName, String productName) {
        CartPage cartPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(7), jsonDataConfig.getPasswordFromJson(7))
                .searchProduct(searchName)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLinkFromAlert()
                .clickFirstAddToCartButton()
                .clickAddToCartLink();
        Assert.assertEquals(cartPage.getProductNameTextByName(productName), productName);
    }

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    public void goToProductPageFromComparisonPage(String myAccountDropdownText, String searchName, String productName) {
        ProductPage productPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(7), jsonDataConfig.getPasswordFromJson(7))
                .searchProduct(searchName)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLinkFromAlert()
                .clickFirstProductName();
        Assert.assertEquals(productPage.getProductName(), productName);
    }

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    public void goToComparisonPageFromProductCompareLabel(String myAccountDropdownText, String searchName, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(7), jsonDataConfig.getPasswordFromJson(7))
                .searchProduct(searchName)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLink();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }
}