package test.product_comparison_test;

import com.opencart.pages.cart.CartPage;
import com.opencart.pages.comparison.ComparisonPage;
import com.opencart.pages.comparison.EmptyComparisonPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.CompareTestRunner;
import com.opencart.tools.JsonDataConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("ComparisonTest")
public class LoggedUserProductComparisonTest extends CompareTestRunner {

    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    @Description("Verify if user can add product to compare from search page")
    public void AddProductToCompareFromSearch(String myAccountDropdownText, String search, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .searchProduct(search)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLink();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"myAccountDropdownText", "productName"})
    @Test
    public void AddProductToCompareFromMainPage(String myAccountDropdownText, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .goToHomePage()
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLinkFromAlert();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"myAccountDropdownText", "productName"})
    @Test
    public void AddProductToCompareFromProductPage(String myAccountDropdownText, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .goToHomePage()
                .clickProductNameLink(productName)
                .clickCompareButton()
                .goToComparisonPageFromAlert();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    public void AddSameProductTwoTimes(String myAccountDropdownText, String searchName, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .searchProduct(searchName)
                .AddTwoSameProductToComparison(productName)
                .clickProductComparisonLinkFromAlert();
        Assert.assertEquals(comparisonPage.getProductsCount(), 1);
    }

    @Parameters({"myAccountDropdownText", "firstProductId", "expectedEmptyPageResult"})
    @Test
    public void CleanAllProductsFromComparison(String myAccountDropdownText, int firstId, String expected) {
        EmptyComparisonPage emptyComparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .goToHomePage()
                .addProductToCompareByJS(firstId)
                .clickProductComparisonLink()
                .clickFirstRemoveButton();
        Assert.assertTrue(emptyComparisonPage.isAlertDisplayed());
        Assert.assertEquals(emptyComparisonPage.getEmptyPageContentText(), expected);
    }

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    public void AddToCartFromComparisonPage(String myAccountDropdownText, String searchName, String productName) {
        CartPage cartPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .searchProduct(searchName)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLinkFromAlert()
                .clickFirstAddToCartButton()
                .clickAddToCartLink();
        Assert.assertEquals(cartPage.getProductNameTextByName(productName), productName);
    }

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    public void GoToProductPageFromComparisonPage(String myAccountDropdownText, String searchName, String productName) {
        ProductPage productPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .searchProduct(searchName)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLinkFromAlert()
                .clickFirstProductName();
        Assert.assertEquals(productPage.getProductName(), productName);
    }

    @Parameters({"myAccountDropdownText", "searchName", "productName"})
    @Test
    public void GoToComparisonPageFromProductCompareLabel(String myAccountDropdownText, String searchName, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToLoginPage(myAccountDropdownText)
                .login(jsonDataConfig.getEmailFromJson(0), jsonDataConfig.getPasswordFromJson(0))
                .searchProduct(searchName)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLink();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }
}