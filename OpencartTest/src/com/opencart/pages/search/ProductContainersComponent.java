package com.opencart.pages.search;

import com.opencart.tools.RegexUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductContainersComponent {

    //
    private WebElement name;
    private WebElement compareButton;
    private WebElement addToCartButton;
    private WebElement addToWishList;
    private WebElement price;

    public ProductContainersComponent(WebElement product) {
        InitializeElements(product);
    }

    private void InitializeElements(WebElement product){
        name = product.findElement(By.cssSelector("h4 a"));
        compareButton = product.findElement(By.xpath("//div[@class='button-group']/button/i[@class='fa fa-exchange']/.."));
        addToCartButton = product.findElement(By.cssSelector(".fa.fa-shopping-cart"));
        addToWishList=product.findElement(By.xpath("//div[@class='button-group']/button/i[contains(@class,'fa-heart')]"));;
        price=product.findElement(By.cssSelector(".price"));;
    }

    //Name
    public String getNameText(){
        return name.getText();
    }

    //CompareButton
    public void clickCompareButton(){
        compareButton.click();
    }

    //AddToCartButton
    public void clickAddToCartButton(){
        addToCartButton.click();
    }

    //Price
    public WebElement getPrice() {
        return price;
    }

    public String getPriceText() {
        return getPrice().getText();
    }
    public double getPriceAmount() {
        return RegexUtils.extractFirstDouble(getPriceText());
    }

    //AddToWishList
    public void clickAddToWishList() {
        addToWishList.click();
    }
}
