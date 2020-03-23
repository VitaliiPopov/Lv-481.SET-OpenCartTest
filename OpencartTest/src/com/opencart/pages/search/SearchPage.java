package com.opencart.pages.search;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.common.AddProductToCartAlertComponent;
import com.opencart.pages.product.ProductPage;
import com.opencart.pages.product_table.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPageWithHeader {

    //Errors
    public final String PRODUCT_NOT_FOUND = "PRODUCT NOT FOUND";
    //Selectors
    private final String PRODUCT_COMPONENT_CSSSELECTOR = "//div[@class='product-thumb']";
    private final String SUCCESS_SEARCH_PAGE_URL = "search&search";
    //Components
    private List<ProductContainersComponent> productContainersComponents;
    private SearchCriteriaComponent searchCriteriaComponent;
    private ProductDisplayCriteriaComponent productDisplayCriteriaComponent;
    //
    private AddProductToCartAlertComponent addProductToCartAlertComponent;

    public SearchPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        productContainersComponents = new ArrayList<>();
        for (WebElement current: driver.findElements(By.xpath(PRODUCT_COMPONENT_CSSSELECTOR))) productContainersComponents.add(new ProductContainersComponent(current));
    }


    //PAGE OBJECT

    //addProductToCartAlertComponent
    public AddProductToCartAlertComponent getAddProductToCartAlertComponent() {
        addProductToCartAlertComponent = new AddProductToCartAlertComponent(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
        return addProductToCartAlertComponent;
    }

    //productContainersComponents
    public Integer getProductContainerComponentsSize() {
        return productContainersComponents.size();
    }

    //FUNCTIONAL

    //findElement
    //String
    public ProductContainersComponent getProductComponentByName(String productName){
        ProductContainersComponent result = null;
        for (ProductContainersComponent current: productContainersComponents) {
            if(current.getNameText().equals(productName)){
                result = current;
                break;
            }
        }
        if(result == null) throw new RuntimeException(PRODUCT_NOT_FOUND);
        return result;
    }

    //add to cart by button
    public void clickProductComponentAddToCartButtonByName(String productName)
    {
        getProductComponentByName(productName).clickAddToCartButton();
    }

    //BUSINESS LOGIC

    //after add to cart
    public AbstractPageWithHeader afterClickProductComponentAddToCartButtonByName(String productName){
        clickProductComponentAddToCartButtonByName(productName);
        //TODO
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(driver.getCurrentUrl().contains(SUCCESS_SEARCH_PAGE_URL)) return returnSearchPage();
        else return returnProductPage();
    }

    private SearchPage returnSearchPage(){
        return new SearchPage(driver);
    }

    private ProductPage returnProductPage(){
        return new ProductPage(driver);
    }

    //alert after add to cart
    public CartPage goToShoppingCartFromAlert() {
        getAddProductToCartAlertComponent().clickOnCartLink();
        return new CartPage(driver);
    }

    public ProductPage goToProductPageFromAlert() {
        getAddProductToCartAlertComponent().clickOnProductLink();
        return new ProductPage(driver);
    }

}
