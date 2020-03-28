package com.opencart.pages;

import com.opencart.pages.comparison.ComparisonPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.pages.search.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends AbstractPageWithHeader {

    private final String PRODUCT_COMPONENT_LOCATOR = ".product-layout"; // css
    private final String ALERT_LOCATOR = ".alert"; //css

    //Components
    private List<ProductContainersComponent> productContainersComponents;

    public HomePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    //INITIALIZATION

    /**
     * This method initializing product inside list of products on a page. First, it waits visibility of all elements on the page,
     * then pass every element in list in ProductContainerComponent constructor.
     */
    private void initElements() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        productContainersComponents = new ArrayList<>();
        for (WebElement current : wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(PRODUCT_COMPONENT_LOCATOR)))) {
            productContainersComponents.add(new ProductContainersComponent(current));
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    ///region ATOMIC_OPERATIONS

    public AlertComponent getAlertComponentWithWait() {
        /*try {
            Thread.sleep(1000); //Only for presentation, bug alert
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return new AlertComponent(driver.findElement(By.cssSelector(ALERT_LOCATOR)));
    }

    ///endregion

    ///region FUNCTIONAL

    /**
     * Find product in productContainersComponent by name.
     *
     * @param productName Product name.
     * @return The Object of ProductContainersComponent.
     */
    public ProductContainersComponent getProductComponentByName(String productName) {
        ProductContainersComponent result = null;
        for (ProductContainersComponent current : productContainersComponents) {
            if (current.getNameText().equalsIgnoreCase(productName)) {
                result = current;
                break;
            }
        }
        return result;
    }


    /**
     * Add product to cart by button.
     *
     * @param productName Product name.
     */
    public HomePage clickProductComponentAddToCartButtonByName(String productName) {
        getProductComponentByName(productName).clickAddToCartButton();
        return this;
    }

    /**
     * Add product to wish list by button.
     *
     * @param productName Product name.
     */
    public HomePage clickProductComponentAddToWishListButtonByName(String productName) {
        getProductComponentByName(productName).clickAddToWishListButton();
        return this;
    }

    /**
     * Add product to comparison by button.
     *
     * @param productName Product name.
     * @return Returning new Search Page.
     */
    public SearchPage clickProductComponentCompareButtonByName(String productName) {
        getProductComponentByName(productName).clickCompareButton();
        return new SearchPage(driver);
    }

    /**
     * This method use javascript method for fast adding product to comparison.
     *
     * @param id of product.
     * @return the same page.
     */
    public HomePage addProductToCompareByJS(int id) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("document.onclick=compare.add(" + id + ");");
        }
        return this;
    }

    ///endregion

    ///region LOGIC

    /**
     * This method clicks on product name label
     *
     * @return Returns new Product page
     */
    public ProductPage clickOnProductNameLabel(String productName) {
        getProductComponentByName(productName).clickOnNameLabel();
        return new ProductPage(driver);
    }

    /**
     * This method clicks on product comparison link from alert message
     *
     * @return new Comparison page
     */
    public ComparisonPage clickProductComparisonLink() {
        getAlertComponentWithWait().clickOnCompareLink();
        return new ComparisonPage(driver);
    }

    public ProductPage clickProductNameLink(String name) {
        getProductComponentByName(name).clickOnNameLabel();
        return new ProductPage(driver);
    }

    ///endregion
}