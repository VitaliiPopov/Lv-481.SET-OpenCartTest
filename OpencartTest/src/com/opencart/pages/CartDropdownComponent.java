package com.opencart.pages;

import com.opencart.tools.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDropdownComponent {

    //Errors
    private final String PRODUCT_NOT_FOUND = "PRODUCT NOT FOUND";
    //Locators
    private final String PRODUCTS_IN_CART_BUTTON_CSSLOCATOR = "table.table.table-striped tr";
    //
    private WebElement cartDropdownComponentLayout;
    //
    private WebElement cartTotalMessage;
    //
    private WebElement totalPrice;
    private WebElement cartRemoveButton;
    //Components
    private List<ProductInCartButtonContainerComponent> productInCartButtonContainerComponents;

    public CartDropdownComponent(WebElement cartDropdownComponentLayout) {
        this.cartDropdownComponentLayout = cartDropdownComponentLayout;
        initElements();
    }

    public void initElements() {
        productInCartButtonContainerComponents = new ArrayList<>();
        for (WebElement current : cartDropdownComponentLayout.findElements(By.cssSelector(PRODUCTS_IN_CART_BUTTON_CSSLOCATOR)))
            productInCartButtonContainerComponents.add(new ProductInCartButtonContainerComponent(current));
    }

    //getCartTotalMessage
    public WebElement getCartTotalMessage() {
        cartTotalMessage = cartDropdownComponentLayout.findElement(By.id("cart-total"));
        return cartTotalMessage;
    }

    public String getCartTotalMessageText() {
        return cartTotalMessage.getText();
    }

    //totalPrice
    public WebElement getTotalPrice() {
        totalPrice = cartDropdownComponentLayout.findElement(By.xpath(".//tr[2]/td[2][@class='text-right']"));
        return totalPrice;
    }

    public BigDecimal getTotalPriceText() {
        System.out.println(getTotalPrice().getText().substring(1).replaceAll(",", ""));
        System.out.println(BigDecimal.valueOf(Double.parseDouble(getTotalPrice().getText().substring(1).replaceAll(",", ""))));
        return BigDecimal.valueOf(Double.parseDouble(getTotalPrice().getText().substring(1).replaceAll(",", "")));
    }

    //cartButton
    public WebElement getCartRemoveButton() {
        cartRemoveButton = cartDropdownComponentLayout.findElement(By.xpath(".//p[@class='text-right']/a[@href[contains(., '/cart')]]"));
        return cartRemoveButton;
    }

    public void clickOnCartRemoveButtonInCartDropdown() {
        getCartRemoveButton().click();
    }

    //productInCartButtonContainerComponents size
    public List<ProductInCartButtonContainerComponent> getProductInCartButtonContainerComponents() {
        return productInCartButtonContainerComponents;
    }

    //productInCartButtonContainerComponents size
    public int getProductInCartButtonContainerComponentsSize() {
        return productInCartButtonContainerComponents.size();
    }

    //emptyDropdownCartMessage
    public WebElement getEmptyDropdownCartMessage() {
        return  cartDropdownComponentLayout.findElement(By.xpath(".//p[@class='text-center']"));
    }

    public String getEmptyDropdownCartButtonText() {
        return getEmptyDropdownCartMessage().getText();
    }

    //FUNCTIONAL

    //findElement
    //String
    public ProductInCartButtonContainerComponent getProductInCartButtonContainerComponentByName(String productName) {
        ProductInCartButtonContainerComponent result = null;
        for (ProductInCartButtonContainerComponent current : productInCartButtonContainerComponents) {
            if (current.getProductNameText().contains(productName)) {
                result = current;
                break;
            }
        }
        if (result == null) throw new RuntimeException(PRODUCT_NOT_FOUND);
        return result;
    }

    //remove product
    public void removeViewProductComponent(String productName) {
        getProductInCartButtonContainerComponentByName(productName).clickOnRemoveButton();
    }

    //findTotalPrise
    public BigDecimal getTotalPriceFromColumn() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (ProductInCartButtonContainerComponent current : productInCartButtonContainerComponents) {
            totalPrice = totalPrice.add(BigDecimal.valueOf(Double.parseDouble(current.getTotalProductPriceText())));
        }
        return totalPrice;
    }

    //checkTotalPrice
    public boolean checkTotalPrice() {
        if (getTotalPriceText().equals(getTotalPriceFromColumn())) return true;
        else return false;
    }


}