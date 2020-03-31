package com.opencart.pages;

import com.opencart.pages.account.LoginPage;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.account.AccountLogoutPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.pages.account.RegisterPage;
import com.opencart.pages.search.SearchPage;
import com.opencart.pages.wishlist.WishListPage;
import com.opencart.tools.RegexUtils;

import com.opencart.tools.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.List;


public class AbstractPageWithHeader {

    //ExceptionsText
    private final String OPTION_NULL_MESSAGE = "DropdownComponent is null";
    private final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
    //Wait
    private WaitUtils cartWait;
    //
    private boolean isCartButtonOpen;

    //selectors
    private final String DROPDOWN_MYACCONT_SELECTOR = "ul.dropdown-menu.dropdown-menu-right"; //css

    //WebDriver
    protected WebDriver driver;

    //Components
    private DropdownComponent dropdownComponent;

    @FindBy(how = How.CSS, css = ".btn.btn-link.dropdown-toggle")
    private WebElement currency;

    @FindBy(how = How.CSS, css = "i.fa-user")
    private WebElement myAccount;

    @FindBy(how = How.ID, id = "wishlist-total")
    private WebElement wishList;

    @FindBy(how = How.XPATH, xpath = "//a[@title='Shopping Cart']")
    private WebElement shoppingCart;

    @FindBy(how = How.XPATH, xpath = "//h1/a")
    private WebElement logo;

    @FindBy(how = How.NAME, name = "search")
    private WebElement searchTopField;

    @FindBy(how = How.CSS, css = "button.btn.btn-default")
    private WebElement searchTopButton;

    @FindBy(how = How.CSS, css = "#cart > button")//"#cart > button"
    private WebElement cartButton;

    public AbstractPageWithHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        isCartButtonOpen = false;
    }

    ///region ATOMIC_OPERATIONS

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

    //shoppingCart
    private void clickOnShoppingCart() {
        shoppingCart.click();
    }

    //currency
    public String getCurrencyText() {
        return currency.getText().substring(0, 1);
    }

    public void clickCurrency() {
        currency.click();
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
        searchTopButton.click();
    }

    //wishList
    private void clickWishList() {
        wishList.click();
    }

    private void clickCart() {
        shoppingCart.click();
    }

    //searchField
    private void clearSearchField() {
        searchTopField.clear();
    }

    private void inputSearchField(String search) {
        searchTopField.sendKeys(search);
    }

    private void clickSearchField() {
        searchTopField.click();
    }

    //cartButton
    public String getCartButtonText() {
        return cartButton.getText();
    }

    public void clickOnCartButton() {
        cartButton.click();
    }

    //dropdownComponent
    protected DropdownComponent getDropdownComponent() {
        if (dropdownComponent == null) {
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownComponent;
    }

    private DropdownComponent createDropdownComponent(By searchLocator) {
        dropdownComponent = new DropdownComponent(driver, searchLocator);
        return dropdownComponent;
    }

    private void clickDropdownComponentByPartialName(String optionName) {
        if (getDropdownComponent().isExistDropdownOptionByPartialName(optionName)) {
            getDropdownComponent().clickDropdownOptionByPartialName(optionName);
        } else {
            throw new RuntimeException(String.format(OPTION_NOT_FOUND_MESSAGE, optionName));
        }
    }

    //cartDropdownComponent
    public CartDropdownComponent getCartDropdownComponent() {
        this.cartWait = new WaitUtils(driver, 5);
        return new CartDropdownComponent(driver.findElement(By.cssSelector("#cart ul")));
    }

    ///endregion

    ///region FUNCTIONAL

    //MyAccount
    public void openMyAccountDropdown() {
        clickMyAccount();
        createDropdownComponent(By.cssSelector(DROPDOWN_MYACCONT_SELECTOR));
    }

    public void clickMyAccountDropdownComponentByPartialName(String text) {
        openMyAccountDropdown();
        clickDropdownComponentByPartialName(text);
    }

    //SearchTopField
    public void fillSearchTopField(String searchText) {
        clickSearchTopField();
        clearSearchTopField();
        setSearchTopField(searchText);
    }

    //CartButton
    public void openViewCartComponent() {
        clickOnCartButton();
    }

    public String getCartTotalMessageText() {
        return getCartDropdownComponent().getCartTotalMessageText();
    }

    public String getEmptyDropdownCartButtonText() {
        openViewCartComponent();
        return getCartDropdownComponent().getEmptyDropdownCartButtonText();
    }

    public BigDecimal getTotalPriceText() {
        return getCartDropdownComponent().getTotalPriceText();
    }

    //productInCartButtonContainerComponents size
    public int getProductInCartButtonContainerComponentsSize() {
        openViewCartComponent();
        return getCartDropdownComponent().getProductInCartButtonContainerComponentsSize();
    }

    public ProductInCartButtonContainerComponent getProductInCartButtonContainerComponentByName(String productName) {
        openViewCartComponent();
        return getCartDropdownComponent().getProductInCartButtonContainerComponentByName(productName);
    }

    public void removeViewProductComponentByName(String productName) {
        openViewCartComponent();
        getCartDropdownComponent().removeViewProductComponent(productName);
    }

    public boolean checkTotalPrice() {
        openViewCartComponent();

        try {
            Thread.sleep(1000);//OnlyForPresentation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (getTotalPriceText().equals(getCartDropdownComponent().getTotalPriceFromColumn())) return true;
        else return false;
    }

    //String
    public boolean checkIsTheProductInCartComponentByName(String productName) {
        if (getProductInCartButtonContainerComponentByName(productName) == null) return false;
        else return true;
    }

    //WishList
    public String getWishListText() {
        return wishList.getText();
    }

    public int getWishListNumberOfProducts() {
        return RegexUtils.extractFirstNumber(getWishListText());
    }

    // hardcode by Yura
    public MyAccountPage clickMyAccauntInDropdownHardcode() {
        openMyAccountDropdown();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li[last()-4]")).click();
        return new MyAccountPage(driver);
    }

    // Anya work with dropdown myAccount
    public void clickMyAccountDropdownComponentByName(String optionName) {
        if(!isCartButtonOpen) openMyAccountDropdown();
        WebElement dropdown = driver.findElement(By.cssSelector(DROPDOWN_MYACCONT_SELECTOR));
        List<WebElement> options = dropdown.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText().equals(optionName)) {
                option.click();
                break;
            }
        }
    }

    public boolean isExistMyAccountDropdownOption(String optionName) {
        boolean isFound = false;
        if(!isCartButtonOpen) openMyAccountDropdown();
        WebElement dropdown = driver.findElement(By.cssSelector(DROPDOWN_MYACCONT_SELECTOR));
        List<WebElement> options = dropdown.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText().equals(optionName)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    ///endregion

    ///region LOGIC
    public RegisterPage goToRegisterPage() {
        clickMyAccountDropdownComponentByName("Register");
        return new RegisterPage(driver);
    }

    public AccountLogoutPage goToLogoutPage() {
        clickMyAccountDropdownComponentByName("Logout");
        return new AccountLogoutPage(driver);
    }

    public LoginPage goToLoginPage(String MY_ACCOUNT_DROPDOWN_TEXT) {
        clickMyAccountDropdownComponentByPartialName(MY_ACCOUNT_DROPDOWN_TEXT);
        return new LoginPage(driver);
    }

    public HomePage goToHomePage() {
        clickLogo();
        return new HomePage(driver);
    }
    public SearchPage getSearchPage(){
        return new SearchPage(driver);
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
    public SearchPage searchProduct(String name) {
        clickSearchField();
        clearSearchField();
        inputSearchField(name);
        clickSearchButton();
        return new SearchPage(driver);
    }

    public SearchPage goToSearchPageAfterSearchProduct(String productName) {
        fillSearchTopField(productName);
        clickSearchTopButton();
        return new SearchPage(driver);
    }

    public CartPage goToCartPageByLinkInHeader() {

        try {
            Thread.sleep(500);//Only for presentation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOnShoppingCart();
        return new CartPage(driver);
    }

    public AbstractPageWithHeader removeAllProductsFromCartButton() {
        if(!isCartButtonOpen) openViewCartComponent();
        int size = getCartDropdownComponent().getProductInCartButtonContainerComponentsSize();

        for (int i = 0; i < size; i++) {
            if(!isCartButtonOpen) openViewCartComponent();
            //getCartDropdownComponent().initElements();

            getCartDropdownComponent().getProductInCartButtonContainerComponents().get(size-1).clickOnRemoveButton();
        }

        return new AbstractPageWithHeader(driver);
    }

    ///endregion

}