package com.opencart.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductInCartContainerComponent {

    //Layout
    private WebElement productInCartContainerComponentLayout;
    //WebElements
    private WebElement productName;
    private WebElement quantityInputFild;
    private WebElement quantityButtonRefresh;
    private WebElement quantityButtonRemove;
    private WebElement unitPrice;
    private WebElement totalProductPrice;

    public ProductInCartContainerComponent(WebElement productInCartContainerComponentLayout) {
        this.productInCartContainerComponentLayout = productInCartContainerComponentLayout;
    }

    //PAGE OBJECT

    //productName
    private WebElement getProductName() {
        productName = productInCartContainerComponentLayout.findElement(By.cssSelector("td.text-left a"));
        return productName;
    }

    public String getProductNameText() {
        return getProductName().getText();
    }

    //quantityInputFild
    private WebElement getQuantityInputFild() {
        quantityInputFild = productInCartContainerComponentLayout.findElement(By.cssSelector("td div input"));
        return quantityInputFild;
    }

    public String getQuantityInputFildText() {
        return getQuantityInputFild().getAttribute("value");
    }

    public void clearQuantityInputFild() {
        getQuantityInputFild().clear();
    }

    public void clickOnQuantityInputFild() {
        getQuantityInputFild().click();
    }

    //TODO EXEPTION
    public void modifyTextQuantityInputFild(int number) {
        quantityInputFild.sendKeys(Integer.toString(number));
    }

    //quantityButtonRefresh
    private WebElement getQuantityButtonRefresh() {
        quantityButtonRefresh = productInCartContainerComponentLayout.findElement(By.cssSelector("td div button[type='submit']"));
        return quantityButtonRefresh;
    }

    public void clickOnQuantityButtonRefresh() {
        getQuantityButtonRefresh().click();
    }

    //quantityButtonRemov
    private WebElement getQuantityButtonRemove() {
        quantityButtonRemove = productInCartContainerComponentLayout.findElement(By.cssSelector("td div button[type='button']"));
        return quantityButtonRemove;
    }

    public void clickOnQuantityButtonRemove() {
        getQuantityButtonRemove().click();
    }

    //unitPrice
    private WebElement getUnitPrice() {
        unitPrice = productInCartContainerComponentLayout.findElement(By.xpath("./td[5]"));
        return unitPrice;
    }

    public String getUnitPriceText() {
        return getUnitPrice().getText();
    }

    //totalProductPrice
    private WebElement getTotalProductPrice() {
        totalProductPrice = productInCartContainerComponentLayout.findElement(By.xpath("./td[6]"));
        return totalProductPrice;
    }

    public String getTotalProductPriceText() {
        return getTotalProductPrice().getText();
    }

    //FUNCTIONAL
    public void setQuantityInput(int value) {
        clickOnQuantityInputFild();
        clearQuantityInputFild();
        modifyTextQuantityInputFild(value);
    }

    public void refreshProductQuantity(int value) {
        setQuantityInput(value);
        clickOnQuantityButtonRefresh();
    }
}