package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.components.AccountSidebarComponent;
import pages.parts.AHeaderPart;

public class LoginPage extends AHeaderPart {

    //Components
    private AccountSidebarComponent accountSidebarComponent;
    //RETURNING CUSTOMER
    private WebElement titleLoginBlock;
    private WebElement loginInputField;
    private WebElement passwordInputField;
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
       // accountSidebarComponent = new AccountSidebarComponent(driver.findElement(By.xpath("")));
        titleLoginBlock = driver.findElement(By.xpath("//input[@id='input-password']/../../../h2"));
        loginInputField = driver.findElement(By.id("input-email"));
        passwordInputField = driver.findElement(By.id("input-password"));
        loginButton = driver.findElement(By.xpath("//input[@id = 'input-password']/../following-sibling::input"));
    }

    //PAGE OBJECT

    //titleLoginBlock
    public WebElement getTitleLoginBlock() {
        return titleLoginBlock;
    }

    public String getTitleLoginBlockText() {
        return getTitleLoginBlock().getText();
    }

    //loginInputField
    public WebElement getLoginInputField() {
        return loginInputField;
    }

    public void clickLoginInputField() {
        getLoginInputField().click();
    }

    public void clearLoginInputField() {
        getLoginInputField().clear();
    }

    public void setLoginInputField(String login) {
        getLoginInputField().sendKeys(login);
    }

    //passwordInputField
    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public void clickPasswordInputField() {
        getPasswordInputField().click();
    }

    public void setPasswordInputField(String password) {
        getPasswordInputField().sendKeys(password);
    }

    //loginButton
    public WebElement getLoginButton() {
        return loginButton;
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    //FUNCTIONAL

    //loginInputField
    public void fillInputLogin(String login){
        clickLoginInputField();
        clearLoginInputField();
        setLoginInputField(login);
    }

    //passwordInputField
    public void fillInputPassword(String password){
        clickPasswordInputField();
        clickPasswordInputField();
        setPasswordInputField(password);
    }

    //BUSINESS LOGIC

    //login
    public SuccessLoginPage login(String LOGIN_NAME, String PASSWORD_NAME){
        fillInputLogin(LOGIN_NAME);
        fillInputPassword(PASSWORD_NAME);
        clickLoginButton();
        return new SuccessLoginPage(driver);
    }

}
