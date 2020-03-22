package com.opencart.pages.product;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends AbstractPageWithHeader {

    private AvailableOptionsComponent availableOptionsComponent;

    public ProductPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        availableOptionsComponent = new AvailableOptionsComponent(driver.findElement(By.cssSelector("div#product")));
    }

    public AvailableOptionsComponent getAvailableOptionsComponent() {
        return availableOptionsComponent;
    }

    //BUSINESS LOGIC
    public ProductPage goToProductPageAfterAddToCart(int value){
        getAvailableOptionsComponent().enterTextQty(value);
        getAvailableOptionsComponent().clickOnAddToCartButton();
        return new ProductPage(driver);
    }

}
