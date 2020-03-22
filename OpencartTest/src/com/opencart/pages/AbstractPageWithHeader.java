package com.opencart.pages;

import com.opencart.pages.account.LoginPage;
import com.opencart.pages.product_table.CartPage;
import com.opencart.pages.search.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AbstractPageWithHeader {

    //ExceptionsText
    private final String OPTION_NULL_MESSAGE = "DropdownComponent is null";
    private final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
    //selectors
    private final String DROPDOWN_MYACCONT_CSSSELECTOR = "ul.dropdown-menu.dropdown-menu-right";
    //WebDriver
    protected WebDriver driver;
    //
    @FindBy(how = How.CSS, css = "i.fa-user")
    private WebElement myAccount;
    @FindBy(how = How.XPATH, xpath = "//a[@title='Shopping Cart']")
    private WebElement shoppingCart;
    //
    @FindBy(how = How.XPATH, xpath = "//h1/a")
    private WebElement logo;
    @FindBy(how = How.NAME, name = "search")
    private WebElement searchTopField;
    @FindBy(how = How.CSS, css = "button.btn.btn-default")
    private WebElement searchTopButton;
    @FindBy(how = How.ID, id = "cart-total")//"#cart > button"
    private WebElement cartButton;
    //Components
    private CartDropdownComponent cartDropdownComponent;
    private DropdownComponent dropdownComponent;
    // to check if cart view already opened
    private boolean isViewCartOpened;

    public AbstractPageWithHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        isViewCartOpened = false;
    }

    //PageObject

    //searchTopField
    public void clickSearchTopField() {
        searchTopField.click();
    }

    public void clearSearchTopField() {
        searchTopField.clear();
    }

    public void setSearchTopField(String text) {
        searchTopField.sendKeys(text);
    }

    //searchTopButton
    public void clickSearchTopButton() {
        searchTopButton.click();
    }

    //TODO
    //shoppingCart
    private void clickOnShoppingCart() {
        shoppingCart.click();
    }

    //logo
    private void clickLogo() {
        logo.click();
    }

    //myAccount
    private void clickMyAccount() {
        myAccount.click();
    }


    //cartButton
    public String getCartButtonText() {
        return cartButton.getText();
    }

    public void clickOnCartButton() {
        cartButton.click();
    }

    //isViewCartOpened
    public void setViewCartOpened(boolean viewCartOpened) {
        isViewCartOpened = viewCartOpened;
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
        }else{
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

    //SearchTopField
    public void fillSearchTopField(String searchText){
        clickSearchTopField();
        clearSearchTopField();
        setSearchTopField(searchText);
    }

    //CartButton
    public void openViewCartComponent() {
        clickOnCartButton();
        setViewCartOpened(true);
    }

    public void closeViewCartComponent() {
        clickOnCartButton();
        if(isViewCartOpened){
            clickOnCartButton();
            //viewCartComponent = null;
            setViewCartOpened(false);
        }
        //viewCartComponent = null;
        setViewCartOpened(false);
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

    public SearchPage goToSearchPageAfterSearchProduct(String productName){
        fillSearchTopField(productName);
        clickSearchTopButton();
        return new SearchPage(driver);
    }

    public CartPage goToCartPage(){
        clickOnShoppingCart();
        return new CartPage(driver);
    }

    public void deleteProductInCartButton(){
        clickOnCartButton();
        cartDropdownComponent.remove1Products();
        //clickOnCartButton();
    }

}
