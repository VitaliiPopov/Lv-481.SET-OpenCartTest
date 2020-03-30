package com.opencart.pages.comparison;

import com.opencart.pages.cart.CartPage;
import com.opencart.pages.product.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComparisonPageWithMessage {

    private WebDriver driver;
    //
    private WebElement alertMessage;
    private WebElement productNameLink;
    private WebElement shoppingCartLink;
    private WebElement closeButton;

    public ComparisonPageWithMessage(WebDriver driver) {
        this.driver = driver;
        initializeElements();
    }

    private void initializeElements() {
        alertMessage = driver.findElement(By.cssSelector(".alert"));
        productNameLink = driver.findElement(By.xpath("//button[@class='close']/preceding-sibling::a"));
        shoppingCartLink = driver.findElement(By.xpath("//button[@class='close']/preceding-sibling::a[last()]"));
        closeButton = driver.findElement(By.xpath("//button[@class='close']"));
    }

    ///region AtomicOperations
    public boolean isAlertDisplayed() {
        return alertMessage.isDisplayed();
    }

    public String getProductNameLinkText() {
        return productNameLink.getText();
    }

    public ProductPage clickProductNameLink() {
        productNameLink.click();
        return new ProductPage(driver);
    }

    public CartPage clickShoppingCartLink() {
        shoppingCartLink.click();
        return new CartPage(driver);
    }

    public void clickCloseButton() {
        closeButton.click();
    }
    ///endregion
}