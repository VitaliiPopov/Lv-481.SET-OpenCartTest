package com.opencart.pages;

import com.opencart.pages.cart.CartPage;
import com.opencart.pages.comparison.ComparisonPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.pages.search.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends AbstractPageWithHeader {

    //Locators
    private final String PRODUCT_COMPONENT_LOCATOR = ".product-layout"; // css
    private final String ALERT_LOCATOR = ".alert"; //css
    //URL
    private final String SUCCESS_SEARCH_PAGE_URL = "search&search";// url

    //Components
    private List<ProductContainersComponent> productContainersComponents;

    public HomePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    //INITIALIZATION
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

    //alertComponent
    public AlertComponent getAlertComponentWithWait() {
        try {
            Thread.sleep(3000); //Only for presentation, bug alert
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ALERT_LOCATOR)));
//        searchPageAlertComponent = new SearchPageAlertComponent(wait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(ALERT_LOCATOR)))));
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //return searchPageAlertComponent;
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
    public AbstractPageWithHeader clickProductComponentAddToCartButtonByName(String productName) {
        getProductComponentByName(productName).clickAddToCartButton();
        if (driver.getCurrentUrl().contains(SUCCESS_SEARCH_PAGE_URL)) return new SearchPage(driver);
        else return new ProductPage(driver);
    }

    public ProductPage clickOnPictureOfProductComponentByName(String productName) {
        getProductComponentByName(productName).clickPicture();
        return new ProductPage(driver);
    }

    /**
     * Add product to comparison by button.
     *
     * @param productName Product name.
     * @return Returning new Comparison Page.
     */
    public SearchPage clickProductComponentCompareButtonByName(String productName) {
        getProductComponentByName(productName).clickCompareButton();
        return new SearchPage(driver);
    }

    ///endregion

    ///region LOGIC

    /**
     * This method clicks on product comparison link from alert message
     *
     * @return Returns new Comparison page
     */
    public ComparisonPage clickProductComparisonLink() {
        getAlertComponentWithWait().clickOnCompareLink();
        return new ComparisonPage(driver);
    }

    ///endregion
}