package com.opencart.pages;

import org.openqa.selenium.By;
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
    private WebElement totalPrice;
    private WebElement cartButton;
    //Components
    private List<ProductInCartButtonContainerComponent> productInCartButtonContainerComponents;

    public CartDropdownComponent(WebElement cartDropdownComponentLayout) {
        this.cartDropdownComponentLayout = cartDropdownComponentLayout;
        initElements();
    }

    private void initElements() {
        productInCartButtonContainerComponents = new ArrayList<>();
        for (WebElement current: cartDropdownComponentLayout.findElements(By.cssSelector(PRODUCTS_IN_CART_BUTTON_CSSLOCATOR)))
            productInCartButtonContainerComponents.add(new ProductInCartButtonContainerComponent(current));
    }

    //PAGE OBJECT

    //totalPrice
    public WebElement getTotalPrice() {
        totalPrice = cartDropdownComponentLayout.findElement(By.xpath(".//table[@class='table table-bordered']//tr[2]/td[2]"));
        return totalPrice;
    }

    public BigDecimal getTotalPriceText() {
        return  BigDecimal.valueOf(Double.parseDouble(getTotalPrice().getText().substring(1)));
    }

    //cartButton
    public WebElement getCartButton() {
        cartButton = cartDropdownComponentLayout.findElement(By.xpath(".//p[@class='text-right']/a[@href[contains(., '/cart')]]"));
        return cartButton;
    }

    public void clickOnCartButtonInCartDropdown(){
        getCartButton().click();
    }

    //findElement
    //String
    private ProductInCartButtonContainerComponent getProductInCartButtonContainerComponentByName(String productName){
        ProductInCartButtonContainerComponent result = null;
        for (ProductInCartButtonContainerComponent current: productInCartButtonContainerComponents) {
            if (current.getProductNameText().equalsIgnoreCase(productName)){
                result = current;
                break;
            }
        }
        if (result == null) throw new RuntimeException(PRODUCT_NOT_FOUND);
        return result;
    }

    //findTotalPrise
    private BigDecimal getTotalPriceFromColumn(){
        BigDecimal totalPrice = new BigDecimal(0);
        for (ProductInCartButtonContainerComponent current: productInCartButtonContainerComponents) {
            totalPrice = totalPrice.add(BigDecimal.valueOf(Double.parseDouble(current.getTotalProductPriceText())));//.substring(1)
            System.out.println("total price = " + totalPrice);
            System.out.println(current.getProductNameText());
        }
        System.out.println("getTotalPriceFromColumn() = " + totalPrice);
        return totalPrice;
    }

    //checkTotalPrice
    public boolean checkTotalPrice(){
        if (getTotalPriceText().equals(getTotalPriceFromColumn())) return true;
        else return false;
    }

    //removeAllProducts
    public void removeAllProducts(){

        while (productInCartButtonContainerComponents != null){
            productInCartButtonContainerComponents.get(0).clickOnRemoveButton();
            removeAllProducts();
        }
    }

    //removeAllProducts
    public void remove1Products(){
        productInCartButtonContainerComponents.get(0).clickOnRemoveButton();
    }


}
