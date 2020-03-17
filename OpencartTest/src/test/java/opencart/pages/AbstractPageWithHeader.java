package opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import opencart.pages.account.LoginPage;
import opencart.pages.product_table.CartDropdownComponent;
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
    private WebElement shoppingCart;
    //
    @FindBy(how = How.XPATH, xpath = "//h1/a")
    private WebElement logo;
    private WebElement searchTopField;
    private WebElement searchTopButton;
    //Components
    private CartDropdownComponent cartDropdownComponent;
    private DropdownComponent dropdownComponent;

    public AbstractPageWithHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageObject

    //logo
    private void clickLogo() {
        logo.click();
    }

    //myAccount
    private void clickMyAccount() {
        myAccount.click();
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

    //BUSINESS LOGIC

    public LoginPage goToLoginPage(String MY_ACCOUNT_DROPDOWN_TEXT){
        clickMyAccountDropdownComponentByPartialName(MY_ACCOUNT_DROPDOWN_TEXT);
        return new LoginPage(driver);
    }

    public HomePage goToHomePage(){
        clickLogo();
        return new HomePage(driver);
    }


}
