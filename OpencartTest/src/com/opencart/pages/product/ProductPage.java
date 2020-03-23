package com.opencart.pages.product;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.common.AddProductToCartAlertComponent;
import com.opencart.pages.product_table.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage extends AbstractPageWithHeader {

    @FindBy(how = How.CSS, css = "#content h1")
    private WebElement productName;
    //
    private AvailableOptionsComponent availableOptionsComponent;
    private AddProductToCartAlertComponent addProductToCartAlertComponent;

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public AvailableOptionsComponent getAvailableOptionsComponent() {
        availableOptionsComponent = new AvailableOptionsComponent(driver.findElement(By.cssSelector("div#product")));
        return availableOptionsComponent;
    }

    //PAGE OBJECT

    //productName
    public String getProductName() {
        return productName.getText();
    }

    //addProductToCartAlertComponent
    public AddProductToCartAlertComponent getAddProductToCartAlertComponent() {
        addProductToCartAlertComponent = new AddProductToCartAlertComponent(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
        return addProductToCartAlertComponent;
    }

    //BUSINESS LOGIC

    public ProductPage goToProductPageAfterAddToCart(int value){
        getAvailableOptionsComponent().setTextQty(value);
        getAvailableOptionsComponent().clickOnAddToCartButton();
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
