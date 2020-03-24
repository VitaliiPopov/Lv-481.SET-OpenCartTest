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

    private final String PAGE_HEADER = "#content > h1"; // css
    private final String FIRST_PRODUCT_NAME = "//td[text() = 'Product']/following-sibling::td/a"; //xpath
    private final String LAST_PRODUCT_NAME = "//td[text() = 'Product']/following-sibling::td[last()]/a"; //xpath
    private final String ADD_TO_CART_FIRST = "//tbody[last()]/descendant::input"; //xpath
    private final String ADD_TO_CART_LAST = "//tbody[last()]/descendant::input[last()]"; //xpath
    private final String REMOVE_FIRST = "//div[@id='content']//tbody[last()]/descendant::a"; //xpath
    private final String REMOVE_LAST = "//div[@id='content']//tbody[last()]/descendant::a[last()]"; //xpath

    private ComparisonPageAlertComponent comparisonPageAlertComponent;

    public ComparisonPage(WebDriver driver) {
        super(driver);
        VerifyElements();
    }

    private void VerifyElements() {
        WebElement temp = pageHeader();
        temp = firstProductName();
        temp = firstAddToCartButton();
        temp = firstRemoveButton();
        temp = null;
    }

    //region Getters
    public ComparisonPageAlertComponent getComparisonPageAlertComponent() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        comparisonPageAlertComponent = new ComparisonPageAlertComponent(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success alert-dismissible']"))));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return comparisonPageAlertComponent;
    }

    private WebElement pageHeader() {
        return driver.findElement(By.cssSelector(PAGE_HEADER));
    }

    private WebElement firstProductName() {
        /*WebElement name;
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_PRODUCT_NAME)));
        return name;*/
        return driver.findElement(By.xpath(FIRST_PRODUCT_NAME));
    }

    private WebElement lastProductName() {
        return driver.findElement(By.xpath(LAST_PRODUCT_NAME));
    }

    private WebElement firstAddToCartButton() {
        return driver.findElement(By.xpath(ADD_TO_CART_FIRST));
    }

    private WebElement lastAddToCartButton() {
        return driver.findElement(By.xpath(ADD_TO_CART_LAST));
    }

    private WebElement firstRemoveButton() {
        /*WebElement button = driver.findElement(By.xpath(REMOVE_FIRST));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).perform();
        return button;*/
        return driver.findElement(By.xpath(REMOVE_FIRST));
    }

    private WebElement lastRemoveButton() {
        /*WebElement button = driver.findElement(By.xpath(REMOVE_LAST));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).perform();
        return button;*/
        return driver.findElement(By.xpath(REMOVE_LAST));
    }
    //endregion

    ///region AtomicOperations

    //PageHeader
    public String getPageHeaderText() {
        return this.pageHeader().getText();
    }

    //FirstProduct
    public String getFirstProductName() {
        return this.firstProductName().getText();
    }

    public ProductPage clickFirstProductName() {
        firstProductName().click();
        return new ProductPage(driver);
    }

    public ComparisonPageWithMessage clickFirstAddToCartButton() {
        firstAddToCartButton().click();
        return new ComparisonPageWithMessage(driver);
    }

    public EmptyComparisonPage clickFirstRemoveButton() {
        firstRemoveButton().click();
        return new EmptyComparisonPage(driver);
    }

    //SecondProduct
    public String getLastProductName() {
        return this.lastProductName().getText();
    }

    public ProductPage clickLastProductName() {
        lastProductName().click();
        return new ProductPage(driver);
    }

    public ComparisonPageWithMessage clickLastAddToCartButton() {
        lastAddToCartButton().click();
        return new ComparisonPageWithMessage(driver);
    }

    public ComparisonPage clickLastRemoveButton() {
        lastRemoveButton().click();
        return new ComparisonPage(driver);
    }

    public int getProductsCount() {
        List<WebElement> count;
        count = driver.findElements(By.xpath(FIRST_PRODUCT_NAME));
        return count.size();
    }
    ///endregion

    ///region Logic

    public CartPage clickAddToCartLink() {
        getComparisonPageAlertComponent().getAddToCartAlertMessage().click();
        return new CartPage(driver);
    }
}