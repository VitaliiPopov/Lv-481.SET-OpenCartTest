package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductContainersComponent {

    //
    private WebElement name;
    private WebElement compareButton;
    private WebElement addToCartButton;

    public ProductContainersComponent(WebElement product) {
        InitializeElements(product);
    }

    private void InitializeElements(WebElement product){
        name = product.findElement(By.cssSelector(".caption>h4>a"));
        compareButton = product.findElement(By.xpath(".//i[@class='fa fa-exchange']/.."));
        addToCartButton = product.findElement(By.xpath(".//i[@class='fa fa-shopping-cart']/.."));
    }

    //Name
    public String getNameText(){
        return this.name.getText();
    }

    //CompareButton
    public void clickCompareButton(){
        compareButton.click();
    }

    //AddToCartButton
    public void clickAddToCartButton(){
        addToCartButton.click();
    }
}