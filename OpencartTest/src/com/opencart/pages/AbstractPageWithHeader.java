package com.opencart.pages;

import com.opencart.pages.account.LoginPage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.cart.ProductInCartContainerComponent;
import com.opencart.pages.search.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

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
    //to check if cart button view already opened
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        //TODO
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    //cartDropdownComponent
    public CartDropdownComponent getCartDropdownComponent() {
        if (cartDropdownComponent == null) cartDropdownComponent = new CartDropdownComponent(driver, driver.findElement(By.cssSelector("#cart ul")));
        return cartDropdownComponent;
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
            cartDropdownComponent = null;
            setViewCartOpened(false);
        }
        cartDropdownComponent = null;
        setViewCartOpened(false);
    }

    public String getCartTotalMessageText() {
        return getCartDropdownComponent().getCartTotalMessageText();
    }

    public String getEmptyDropdownCartButtonText() {
        if(!isViewCartOpened) openViewCartComponent();
        return getCartDropdownComponent().getEmptyDropdownCartButtonText();
    }

    public BigDecimal getTotalPriceText() {
        if(!isViewCartOpened) openViewCartComponent();
        return getCartDropdownComponent().getTotalPriceText();
    }

    //productInCartButtonContainerComponents size
    public int getProductInCartButtonContainerComponentsSize() {
        if(!isViewCartOpened) openViewCartComponent();
        return getCartDropdownComponent().getProductInCartButtonContainerComponentsSize();
    }

    public ProductInCartButtonContainerComponent getProductInCartButtonContainerComponentByName(String productName){
        if(!isViewCartOpened) openViewCartComponent();
        return getCartDropdownComponent().getProductInCartButtonContainerComponentByName(productName);
    }

    public void removeViewProductComponentByName(String productName) {
        if(!isViewCartOpened) openViewCartComponent();
        getCartDropdownComponent().removeViewProductComponent(productName);
    }

    public boolean checkTotalPrice(){
        if(!isViewCartOpened) openViewCartComponent();
        if (getTotalPriceText().equals(getCartDropdownComponent().getTotalPriceFromColumn())) return true;
        else return false;
    }

    public void removeAllProducts(){
        if(!isViewCartOpened) openViewCartComponent();
        getCartDropdownComponent().removeAllProducts();
    }

    //String
    public boolean checkIsTheProductInCartComponentByName(String productName){
        if (getProductInCartButtonContainerComponentByName(productName) == null) return false;
        else return true;
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

    public CartPage goToCartPageByLinkInHeader(){
        clickOnShoppingCart();
        return new CartPage(driver);
    }

}
