package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductInCartButtonContainerComponent {

    //Layout
    private WebElement productInCartButtonContainerComponentLayout;
    //WebElements
    private WebElement productName;
    private WebElement qty;
    private WebElement totalProductPrice;
    private WebElement removeButton;

    public ProductInCartButtonContainerComponent(WebElement productInCartButtonContainerComponentLayout) {
        this.productInCartButtonContainerComponentLayout = productInCartButtonContainerComponentLayout;
    }

    //PAGE OBJECT

    //productName
    private WebElement getProductName() {
        productName = productInCartButtonContainerComponentLayout.findElement(By.cssSelector("td.text-left"));
        return productName;
    }

    public String getProductNameText() {
        return getProductName().getText();
    }

    //qty
    public WebElement getQty() {
        qty = productInCartButtonContainerComponentLayout.findElement(By.xpath("./td[3]"));
        return qty;
    }

    public String getQtyText() {
        return getQty().getText().substring(2);
    }

    //totalProductPrice
    public WebElement getTotalProductPrice() {
        totalProductPrice = productInCartButtonContainerComponentLayout.findElement(By.xpath("./td[4]"));
        return totalProductPrice;
    }

    public String getTotalProductPriceText() {
        return getTotalProductPrice().getText().substring(1);
    }

    //removeButton
    public WebElement getRemoveButton() {
        removeButton = productInCartButtonContainerComponentLayout.findElement(By.xpath(".//td/button"));
        return removeButton;
    }

    public void clickOnRemoveButton() {
        getRemoveButton().click();
    }
}