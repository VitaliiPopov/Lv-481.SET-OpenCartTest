package com.opencart.pages.comparison;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.product.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComparisonPage extends AbstractPageWithHeader {

    //Locators
    private final String PAGE_HEADER_LOCATOR = "#content > h1"; // css
    private final String ALERT_LAYOUT_LOCATOR = "//div[@class='alert alert-success alert-dismissible']"; // xpath
    private final String FIRST_PRODUCT_NAME_LOCATOR = "//td[text() = 'Product']/following-sibling::td/a[1]"; //xpath
    private final String LAST_PRODUCT_NAME_LOCATOR = "//td[text() = 'Product']/following-sibling::td[last()]/a"; //xpath
    private final String ADD_TO_CART_FIRST_LOCATOR = "//tbody[last()]/descendant::input"; //xpath
    private final String ADD_TO_CART_LAST_LOCATOR = "//tbody[last()]/descendant::input[last()]"; //xpath
    private final String REMOVE_FIRST_LOCATOR = "//div[@id='content']//tbody[last()]/descendant::a"; //xpath
    private final String REMOVE_LAST_LOCATOR = "//div[@id='content']//tbody[last()]/descendant::a[last()]"; //xpath

    //Components
    private ComparisonPageAlertComponent comparisonPageAlertComponent;

    public ComparisonPage(WebDriver driver) {
        super(driver);
        verifyElements();
    }

    private void verifyElements() {
        WebElement temp = pageHeader();
        temp = null;
    }

    ///region GETTERS

    /**
     * This method gets alert component. First, it waits visibility of element, then passing
     * this element to ComparisonPageAlertComponent constructor.
     *
     * @return field of ComparisonPageAlertComponent class type.
     */
    public ComparisonPageAlertComponent getComparisonPageAlertComponent() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        comparisonPageAlertComponent = new ComparisonPageAlertComponent(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ALERT_LAYOUT_LOCATOR))));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return comparisonPageAlertComponent;
    }

    private WebElement pageHeader() {
        return driver.findElement(By.cssSelector(PAGE_HEADER_LOCATOR));
    }

    private WebElement firstProductName() {
        return driver.findElement(By.xpath(FIRST_PRODUCT_NAME_LOCATOR));
    }

    private WebElement lastProductName() {
        return driver.findElement(By.xpath(LAST_PRODUCT_NAME_LOCATOR));
    }

    private WebElement firstAddToCartButton() {
        return driver.findElement(By.xpath(ADD_TO_CART_FIRST_LOCATOR));
    }

    private WebElement lastAddToCartButton() {
        return driver.findElement(By.xpath(ADD_TO_CART_LAST_LOCATOR));
    }

    private WebElement firstRemoveButton() {
        return driver.findElement(By.xpath(REMOVE_FIRST_LOCATOR));
    }

    private WebElement lastRemoveButton() {
        return driver.findElement(By.xpath(REMOVE_LAST_LOCATOR));
    }
    //endregion

    ///region ATOMIC_OPERATIONS

    //PageHeader
    public String getPageHeaderText() {
        return this.pageHeader().getText();
    }

    //FirstProduct
    public String getFirstProductName() {
        return firstProductName().getText();
    }

    //LastProduct

    public String getLastProductName() {
        return lastProductName().getText();
    }

    //ProductCount

    /**
     * This method gets count of founded product on a page.
     *
     * @return size of founded products list.
     */
    public int getProductsCount() {
        List<WebElement> count;
        count = driver.findElements(By.xpath(FIRST_PRODUCT_NAME_LOCATOR));
        return count.size();
    }
    ///endregion

    ///region Logic

    //AlertMessage

    /**
     * This method clicks on shopping cart link from alert message.
     *
     * @return new cart page.
     */
    public CartPage clickAddToCartLink() {
        getComparisonPageAlertComponent().getAddToCartLink().click();
        return new CartPage(driver);
    }

    //FirstProduct

    /**
     * This method clicks on first product name in products comparison table.
     *
     * @return new product page.
     */
    public ProductPage clickFirstProductName() {
        firstProductName().click();
        return new ProductPage(driver);
    }

    /**
     * This method clicks on first product "add to cart" button in products comparison table.
     *
     * @return the same page.
     */
    public ComparisonPage clickFirstAddToCartButton() {
        firstAddToCartButton().click();
        return this;
    }

    /**
     * This method clicks on first product "remove" button in products comparison table.
     *
     * @return empty comparison page.
     */
    public EmptyComparisonPage clickFirstRemoveButton() {
        firstRemoveButton().click();
        return new EmptyComparisonPage(driver);
    }

    //LastProduct

    /**
     * This method clicks on last product name in products comparison table.
     *
     * @return new product page.
     */
    public ProductPage clickLastProductName() {
        lastProductName().click();
        return new ProductPage(driver);
    }

    /**
     * This method clicks on last product "add to cart" button in products comparison table.
     *
     * @return the same page.
     */
    public ComparisonPage clickLastAddToCartButton() {
        lastAddToCartButton().click();
        return this;
    }

    /**
     * This method clicks on last product "remove" button in products comparison table.
     *
     * @return new comparison page.
     */
    public ComparisonPage clickLastRemoveButton() {
        lastRemoveButton().click();
        return new ComparisonPage(driver);
    }

    ///endregion
}