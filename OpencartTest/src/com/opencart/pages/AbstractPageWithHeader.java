package com.opencart.pages;

import com.opencart.pages.product_table.CartPage;
import com.opencart.pages.product_table.WishListPage;
import com.opencart.pages.search.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.product_table.CartDropdownComponent;

public class AbstractPageWithHeader {

    //ExceptionsText
    private final String OPTION_NULL_MESSAGE = "DropdownComponent is null";
    private final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
    //selectors
    private final String DROPDOWN_MYACCONT_CSSSELECTOR = "ul.dropdown-menu.dropdown-menu-right";
    //WebDriver
    protected WebDriver driver;
    //
    private WebElement myAccount;
    private WebElement shoppingCart;
    private WebElement currency;
    private WebElement wishList;
    //
    private WebElement logo;
    private WebElement searchField;
    private WebElement searchButton;
    //Components
    private CartDropdownComponent cartDropdownComponent;
    private DropdownComponent dropdownComponent;

    public AbstractPageWithHeader(WebDriver driver) {
        this.driver = driver;
        InitializeElements();
    }

    private void InitializeElements(){
        myAccount=driver.findElement(By.cssSelector("i.fa-user"));
        logo=driver.findElement(By.xpath("//h1/a"));;
        searchField=driver.findElement(By.cssSelector(".form-control.input-lg"));
        searchButton=driver.findElement(By.cssSelector(".btn.btn-default"));
        currency=driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle"));
        wishList=driver.findElement(By.cssSelector("#wishlist-total"));
        shoppingCart=driver.findElement(By.cssSelector(".fa.fa-shopping-cart"));
    }


    //CURRENCY
    public WebElement getCurrency(){
        return currency;
    }

    public String getCurrencyText(){
        return getCurrency().getText().substring(0,1);
    }

    public void clickCurrency(){
        getCurrency().click();
    }

    public void clickCurrencyByPartialName(String optionName) {
        clickCurrency();
        createDropdownComponent(By.cssSelector("div.btn-group.open ul.dropdown-menu li"));
        clickDropdownComponentByPartialName(optionName);
    }
    //logo
    private void clickLogo() {
        logo.click();
    }

    //myAccount
    private void clickMyAccount() {
        myAccount.click();
    }

    //searchButton
    private void clickSearchButton() {
        searchButton.click();
    }

//    public WebElement getWishList(){
//        return wishList;
//    }
//    public WishListPage clickWishList(){
//        getWishList().click();
//        return new WishListPage(driver);
//    }

    private void clickWishList(){
        wishList.click();

    }
//    public WebElement getWishList(){
//        return wishList;
//    }
//    public HomePage clickWishList(){
//        getWishList().click();
//        return new HomePage(driver);
//    }
    private void clickCart(){
        shoppingCart.click();

    }

    //searchField
    private void clearSearchField() {
        searchField.clear();
    }

    private void inputSearchField(String search) {
        searchField.sendKeys(search);
    }

    private void clickSearchField() {
        searchField.click();
    }

    //dropdownComponent
    protected DropdownComponent getDropdownComponent(){
        if(dropdownComponent == null){
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownComponent;
    }

    private DropdownComponent createDropdownComponent(By searchLocator){
        dropdownComponent = new DropdownComponent(driver, searchLocator);
        return dropdownComponent;
    }

    private void clickDropdownComponentByPartialName(String optionName){
        if(getDropdownComponent().isExistDropdownOptionByPartialName(optionName)){
            getDropdownComponent().clickDropdownOptionByPartialName(optionName);
        }
        else{
            throw new RuntimeException(String.format(OPTION_NOT_FOUND_MESSAGE, optionName));
        }
    }

    //FUNCTIONAL

    //MyAccount
    public void openMyAccountDropdown(){
        clickMyAccount();
        createDropdownComponent(By.cssSelector(DROPDOWN_MYACCONT_CSSSELECTOR));
    }

    public void clickMyAccountDropdownComponentByPartialName(String text){
        openMyAccountDropdown();
        clickDropdownComponentByPartialName(text);
    }

    //BUSINESS LOGIC

    public LoginPage goToLoginPage(String MY_ACCOUNT_DROPDOWN_TEXT){
        clickMyAccountDropdownComponentByPartialName(MY_ACCOUNT_DROPDOWN_TEXT);
        return new LoginPage(driver);
    }

    public HomePage goToHomePage(){
        clickLogo();
        return new HomePage(driver);
    }


    public WishListPage goToWishList() {
        clickWishList();
        return new WishListPage(driver);
    }

    public CartPage goToCart() {
        clickCart();
        return new CartPage(driver);
    }

    //Search
    public SearchPage SearchProduct(String name){
        clickSearchField();
        clearSearchField();
        inputSearchField(name);
        clickSearchButton();
        return new SearchPage(driver);
    }

}
