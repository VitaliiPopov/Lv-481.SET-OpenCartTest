package com.opencart.pages.search;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.common.SearchPageAlertComponent;
import com.opencart.pages.product.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPageWithHeader {

    //Errors
    public final String PRODUCT_NOT_FOUND = "PRODUCT NOT FOUND";
    //Selectors
    private final String PRODUCT_COMPONENT_LOCATOR = "//div[@class='product-thumb']";//Css
    private final String SUCCESS_SEARCH_PAGE_URL = "search&search";
    private final String ALERT_LOCATOR = "//div[@class='alert alert-success alert-dismissible']";//Xpath
    //Components
    private List<ProductContainersComponent> productContainersComponents;
    private SearchCriteriaComponent searchCriteriaComponent;
    private ProductDisplayCriteriaComponent productDisplayCriteriaComponent;
    private SearchPageAlertComponent searchPageAlertComponent;

    public SearchPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    //INITIALIZATION
    private void initElements() {
        productContainersComponents = new ArrayList<>();
        for (WebElement current : driver.findElements(By.xpath(PRODUCT_COMPONENT_LOCATOR)))
            productContainersComponents.add(new ProductContainersComponent(current));
    }

    ///region ATOMIC_OPERATIONS

    //searchPageAlertComponent
    public SearchPageAlertComponent getSearchPageAlertComponent() {
        searchPageAlertComponent = new SearchPageAlertComponent(driver.findElement(By.xpath(ALERT_LOCATOR)));
        return searchPageAlertComponent;
    }

    //productContainerComponents
    public Integer getProductContainerComponentsSize() {
        return productContainersComponents.size();
    }

    ///endregion

    ///region FUNCTIONAL

    /**
     * Find pr oduct in productContainersComponents by name.
     *
     * @param productName Product name.
     * @return The Object of ProductContainersComponent.
     */
    public ProductContainersComponent getProductComponentByName(String productName) {
        ProductContainersComponent result = null;
        for (ProductContainersComponent current : productContainersComponents) {
            if (current.getNameText().equals(productName)) {
                result = current;
                break;
            }
        }
        if (result == null) throw new RuntimeException(PRODUCT_NOT_FOUND);
        return result;
    }

    /**
     * Add product to cart by button.
     *
     * @param productName Product name.
     */
    public void clickOnProductComponentAddToCartButtonByName(String productName) {
        getProductComponentByName(productName).clickAddToCartButton();
    }

    ///endregion

    ///region LOGIC

    /**
     * Return SearchPage or ProductPage depending on options of product.
     * @param productName Product name.
     * @return SearchPage or ProductPage.
     */
    public AbstractPageWithHeader afterClickOnProductComponentAddToCartButtonByName(String productName) {
        clickOnProductComponentAddToCartButtonByName(productName);
        //TODO
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (driver.getCurrentUrl().contains(SUCCESS_SEARCH_PAGE_URL)) return returnSearchPage();
        else return returnProductPage();
    }

    //returnSearchPage
    private SearchPage returnSearchPage() {
        return new SearchPage(driver);
    }

    //returnProductPage
    private ProductPage returnProductPage() {
        return new ProductPage(driver);
    }

    //alert after add to cart
    public CartPage goToShoppingCartFromAlert() {
        getSearchPageAlertComponent().clickOnCartLink();
        return new CartPage(driver);
    }

    public ProductPage goToProductPageFromAlert() {
        getSearchPageAlertComponent().clickOnProductLink();
        return new ProductPage(driver);
    }

    ///endregion

}
