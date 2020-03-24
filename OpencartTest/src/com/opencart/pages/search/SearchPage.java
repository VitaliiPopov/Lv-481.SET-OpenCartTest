package com.opencart.pages.search;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.tools.Driver;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SearchPage extends AbstractPageWithHeader {

    private List<WebElement> alert;
    private WebElement emptyResultMessage;
    private List<ProductContainersComponent> productContainersComponents;

    private SearchCriteriaComponent searchCriteriaComponent;
    private ProductDisplayCriteriaComponent productDisplayCriteriaComponent;

    private final String searchCriteriaElementLocator = "//div[@id='content']";

    private final String ProductDisplayElementLocator = "//div[@id='content']/p/following-sibling::div[1]";

    public SearchPage(WebDriver driver) {
        super(driver);
        InitializeProductContainers();
        InitializeDisplayCriteriaComponent();
        InitializeSearchCriteriaComponent();
    }

    private void InitializeDisplayCriteriaComponent() {
        if (!isEmptyResult()) {
            WebElement ProductDisplayElement = driver.findElement(By.xpath(ProductDisplayElementLocator));
            productDisplayCriteriaComponent = new ProductDisplayCriteriaComponent(driver, ProductDisplayElement);
        }
    }

    public boolean isEmptyResult() {
        try {
            driver.findElement(By.xpath("//p[contains(text(),'no product')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void InitializeSearchCriteriaComponent() {
        WebElement SearchCriteriaElement = driver.findElement(By.xpath(searchCriteriaElementLocator));
        searchCriteriaComponent = new SearchCriteriaComponent(SearchCriteriaElement);
    }

    public void InitializeProductContainers() {

        List<WebElement> productContainers = driver.findElements(By.xpath("//div[@class='product-thumb']"));
        productContainersComponents = new ArrayList<>();
        for (WebElement current : productContainers) {
            productContainersComponents.add(new ProductContainersComponent(current));
        }
    }

    public List<ProductContainersComponent> getProductContainerList() {
        return productContainersComponents;
    }

    private void InitializeAlert() {
        alert = driver.findElements(By.cssSelector(".alert-dismissible"));
    }

    public boolean isAlertDisplayed() {
        return !alert.isEmpty();
    }

    //productContainersComponents
    public Integer getProductContainerComponentsSize() {
        return productContainersComponents.size();
    }


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

    public ArrayList<String> getProductComponentNamesList() {
        ArrayList<String> ProductComponentNamesList = new ArrayList<>();
        for (ProductContainersComponent current : productContainersComponents) {
            ProductComponentNamesList.add(current.getNameText());
        }
        return ProductComponentNamesList;
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

    public SearchCriteriaComponent getSearchCriteriaComponent() {
        return searchCriteriaComponent;
    }

    public ProductDisplayCriteriaComponent getProductDisplayCriteriaComponent() {
        if (!isEmptyResult()) {
            return productDisplayCriteriaComponent;
        } else {
            return null;
        }
    }

    public Integer getListSize() {
        return productContainersComponents.size();
    }

    public void toLowerCaseProductList(List<String> list){
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext())
        {
            iterator.set(iterator.next().toLowerCase());
        }
    }

}
