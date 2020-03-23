package com.opencart.pages.search;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.tools.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

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
            productDisplayCriteriaComponent = new ProductDisplayCriteriaComponent(ProductDisplayElement);
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

    private void InitializeSearchCriteriaComponent() {
        WebElement SearchCriteriaElement = driver.findElement(By.xpath(searchCriteriaElementLocator));
        searchCriteriaComponent = new SearchCriteriaComponent(SearchCriteriaElement);
    }

    private void InitializeProductContainers() {

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







    public static class Randomizer {

        private static SecureRandom random = new SecureRandom();
        private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        private static final String NUMBER = "0123456789";

        private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

        public static String generateRandomString(int length) {
            if (length < 1) throw new IllegalArgumentException();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
                char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
                sb.append(rndChar);
            }
            return sb.toString();

        }
    }
}
