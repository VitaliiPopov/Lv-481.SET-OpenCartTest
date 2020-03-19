package com.opencart.pages.search;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPageWithHeader {

    private List<WebElement> alert;
    private List<ProductContainersComponent> productContainersComponents;

    //private SearchCriteriaComponent searchCriteriaComponent; TODO
    //private ProductDisplayCriteriaComponent productDisplayCriteriaComponent;TODO

    public SearchPage(WebDriver driver) {
        super(driver);
        InitializeProductContainers();
    }

    private void InitializeProductContainers() {
        //
        List<WebElement> productContainers = driver.findElements(By.xpath("//div[@class='product-thumb']"));
        productContainersComponents = new ArrayList<>();
        for (WebElement current : productContainers){
            productContainersComponents.add(new ProductContainersComponent(current));
        }
    }

    private void InitializeAlert(){
        alert = driver.findElements(By.cssSelector(".alert-dismissible"));
    }

    public boolean isAlertDisplayed(){
        return !alert.isEmpty();
    }

    //productContainersComponents
    public Integer getProductContainerComponentsSize() {
        return productContainersComponents.size();
    }

    //FUNCTIONAL

    //findElement
    //String
    public ProductContainersComponent getProductComponentByName(String productName) {
        ProductContainersComponent result = null;
        for (ProductContainersComponent current : productContainersComponents) {
            if (current.getNameText().equalsIgnoreCase(productName)) {
                result = current;
                break;
            }
        }
        //if (result == null) throw new RuntimeException();
        return result;
    }

    //CompareButton
    public void clickProductComponentCompareButtonByName(String productName) {
        getProductComponentByName(productName).clickCompareButton();
        InitializeAlert();
    }

    //add to cart by button
    public void clickProductComponentAddToCartButtonByName(String productName) {
        getProductComponentByName(productName).clickAddToCartButton();
    }

    //BUSINESS LOGIC

}
