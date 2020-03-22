package com.opencart.pages.search;

import com.opencart.pages.AbstractPageWithHeader;
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
    private final String PRODUCT_COMPONENT_CSSSELECTOR = "//div[@class='product-thumb']";
    private final String SUCCESS_SEARCH_PAGE_URL = "search&search";
    //Components
    private List<ProductContainersComponent> productContainersComponents;
    private SearchCriteriaComponent searchCriteriaComponent;
    private ProductDisplayCriteriaComponent productDisplayCriteriaComponent;

    public SearchPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        productContainersComponents = new ArrayList<>();
        for (WebElement current: driver.findElements(By.xpath(PRODUCT_COMPONENT_CSSSELECTOR))) productContainersComponents.add(new ProductContainersComponent(current));
    }

    //PAGE OBJECT

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

    public Object afterClickProductComponentAddToCartButtonByName(String productName){
        clickProductComponentAddToCartButtonByName(productName);
        if(driver.getCurrentUrl().contains(SUCCESS_SEARCH_PAGE_URL)) return new ProductPage(driver);
        else return new SearchPage(driver);
    }

}
