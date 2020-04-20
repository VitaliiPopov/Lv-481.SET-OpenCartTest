package test.product_comparison_test;

import com.opencart.pages.comparison.ComparisonPage;
import com.opencart.pages.comparison.EmptyComparisonPage;
import com.opencart.tools.test_runner.CompareTestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NonLoggedUserProductComparisonTest extends CompareTestRunner {

    @Parameters({"searchName", "productName"})
    @Test
    public void addProductToCompareFromSearch(String search, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToHomePage()
                .searchProduct(search)
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLinkFromAlert();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"productName"})
    @Test
    public void addProductToCompareFromMainPage(String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToHomePage()
                .clickProductComponentCompareButtonByName(productName)
                .clickProductComparisonLinkFromAlert();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"productName"})
    @Test
    public void addProductToCompareFromProductPage(String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToHomePage()
                .clickProductNameLink(productName)
                .clickCompareButton()
                .goToComparisonPageFromAlert();
        Assert.assertEquals(comparisonPage.getFirstProductName(), productName);
    }

    @Parameters({"searchName", "productName"})
    @Test
    public void addSameProductTwoTimes(String searchName, String productName) {
        ComparisonPage comparisonPage = getHomePage()
                .goToHomePage()
                .searchProduct(searchName)
                .AddTwoSameProductToComparison(productName)
                .clickProductComparisonLinkFromAlert();
        Assert.assertEquals(comparisonPage.getProductsCount(), 1);
    }

    @Parameters({"firstProductId", "expectedEmptyPageResult"})
    @Test
    public void cleanAllProductsFromComparison(int firstId, String expected) {
        EmptyComparisonPage emptyComparisonPage = getHomePage()
                .goToHomePage()
                .addProductToCompareByJS(firstId)
                .clickProductComparisonLink()
                .clickFirstRemoveButton();
        Assert.assertTrue(emptyComparisonPage.isAlertDisplayed());
        Assert.assertEquals(emptyComparisonPage.getEmptyPageContentText(), expected);
    }
}