package com.opencart.pages.product_table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends AbstractPageWithHeader {

    //Errors
    private final String PRODUCT_NOT_FOUND = "PRODUCT NOT FOUND";
    //Selectors
    private final String PRODUCT_IN_CART_COMPONENT_XPATHSELECTOR = "//h2/preceding-sibling::form//tbody/tr";
    //WebElements
    @FindBy(how = How.CSS, css = "div.col-sm-4.col-sm-offset-8 tr:nth-child(2) > td:nth-child(2)")
    private WebElement totalPrice;
    @FindBy(how = How.CSS, css = "#content p")
    private WebElement emptyCartText;
    //Components
    private List<ProductInCartContainerComponent> productInCartContainerComponents;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        initElements();
    }

    private void initElements(){
        productInCartContainerComponents = new ArrayList<>();
        for(WebElement current: driver.findElements(By.xpath(PRODUCT_IN_CART_COMPONENT_XPATHSELECTOR)))
            productInCartContainerComponents.add(new ProductInCartContainerComponent(current));
    }

    //PAGE OBJECT

    //totalPrice
    public BigDecimal getTotalPrice() {
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(totalPrice.getText().substring(1)));
        return price;
    }

    //TODO
    //emptyCartText
    public String getEmptyCartText() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content p")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return emptyCartText.getText();
    }

    //size
    public int getProductInCartContainerComponentsSize() {
        return productInCartContainerComponents.size();
    }

    //FUNCTIONAL

    //findElement
    //String
    private ProductInCartContainerComponent getProductInCartComponentByName(String productName){
        ProductInCartContainerComponent result = null;
        for (ProductInCartContainerComponent current: productInCartContainerComponents) {
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
        BigDecimal totalPrice1 = new BigDecimal(0);
        for (ProductInCartContainerComponent current: productInCartContainerComponents) {
            totalPrice1 = totalPrice1.add(BigDecimal.valueOf(Double.parseDouble(current.getTotalProductPriceText().substring(1))));
            System.out.println("total price = " + totalPrice1);
            String name = current.getProductNameText();
            System.out.println(current.getProductNameText());
        }
        System.out.println("getTotalPriceFromColumn() = " + totalPrice1);
        return totalPrice1;
    }

    private BigDecimal getTotalPriceFromCalculation(){
        BigDecimal totalPrice = new BigDecimal(0);
        for (ProductInCartContainerComponent current: productInCartContainerComponents) {
            int quantity = Integer.parseInt(current.getQuantityInputFildText());
            BigDecimal unitPrice = BigDecimal.valueOf(Double.parseDouble(current.getUnitPriceText().substring(1)));
            totalPrice = totalPrice.add(unitPrice.multiply(BigDecimal.valueOf(quantity)));
        }
        System.out.println("getTotalPriceFromCalculation() = " + totalPrice);
        return totalPrice;
    }

    //checkTotalPrice
    private boolean checkTotalPriceFromColumn(){
        if (getTotalPrice().equals(getTotalPriceFromColumn())) return true;
        else return false;
    }

    private boolean checkTotalPriceFromCalculation(){
        if (getTotalPrice().equals(getTotalPriceFromCalculation())) return true;
        else return false;
    }

    public boolean checkTotalPrice(){
        if (checkTotalPriceFromColumn() & checkTotalPriceFromCalculation()) return true;
        else return false;
    }

    //checkAddElements
    public boolean checkAddElements(List<String> productsName){
        List<String> temp = new ArrayList<>(productsName);
        for (ProductInCartContainerComponent current: productInCartContainerComponents) {
            for (String currentProductName: temp) {
                if(current.getProductNameText().equals(currentProductName)) temp.remove(currentProductName);
                else return false;
            }
        }
        if(temp.size() != 0) return false;
        else return true;
    }

    //removeElement
    private void removeProduct(String productName){
        getProductInCartComponentByName(productName).clickOnQuantityButtonRemove();
    }

    //removeAllProducts
    private void removeAllProducts(){
        for (ProductInCartContainerComponent current: productInCartContainerComponents) {
            current.clickOnQuantityButtonRemove();
        }
    }

    //BUSINESS LOGIC

    public CartPage goToCartPageAfterRemoveProductByName(String productName) {
        removeProduct(productName);
        return new CartPage(driver);
    }

    public CartPage goToCartPageAfterRemoveAllProducts() {
        removeAllProducts();
        return new CartPage(driver);
    }



    //TODO ????????
    public CartPage goToCartPageAfterRefreshProductQuantityByName(String productName, int value) {
        getProductInCartComponentByName(productName).refreshProductQuantity(value);
        return new CartPage(driver);
    }

    //checkRefreshFunction
    public boolean checkRefreshFunction(String productName, int value){
        ProductInCartContainerComponent current = getProductInCartComponentByName(productName);
        int oldPrice = Integer.getInteger(current.getQuantityInputFildText()) * Integer.getInteger(current.getUnitPriceText());
        current.refreshProductQuantity(value);
        int newPrice = Integer.getInteger(current.getQuantityInputFildText()) * Integer.getInteger(current.getUnitPriceText());
        if (oldPrice == newPrice) return true;
        else return false;
    }

    //checkRemoveFunction
    public boolean checkRemoveFunction(String productName){
        if (getProductInCartContainerComponentsSize() > 1) {
            int oldSize = getProductInCartContainerComponentsSize();
            ProductInCartContainerComponent current = getProductInCartComponentByName(productName);
            current.clickOnQuantityButtonRemove();
            int newSize = getProductInCartContainerComponentsSize();
            if ((oldSize - 1) == newSize) return true;
            else return false;
        }else{
            ProductInCartContainerComponent current = getProductInCartComponentByName(productName);
            current.clickOnQuantityButtonRemove();
            if (getProductInCartContainerComponentsSize() == 0) return true;
            else return false;
        }
    }

}
