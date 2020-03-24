package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPageWithHeader {

    //Components
    private AccountSidebarComponent accountSidebarComponent;
    //RETURNING CUSTOMER
    @FindBy(how = How.XPATH, xpath = "//input[@id='input-password']/../../../h2")
    private WebElement titleLoginBlock;
    @FindBy(how = How.ID, id = "input-email")
    private WebElement loginInputField;
    @FindBy(how = How.ID, id = "input-password")
    private WebElement passwordInputField;
    @FindBy(how = How.ID, xpath = "//input[@id = 'input-password']/../following-sibling::input")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        initElements();
    }

    private void initElements(){
        accountSidebarComponent = new AccountSidebarComponent(driver);
    }

    //PAGE OBJECT

    //titleLoginBlock
    public String getTitleLoginBlockText() {
        return titleLoginBlock.getText();
    }

    //loginInputField
    public void clickLoginInputField() {
        loginInputField.click();
    }

    public void clearLoginInputField() {
        loginInputField.clear();
    }

    public void setLoginInputField(String login) {
        loginInputField.sendKeys(login);
    }

    //passwordInputField
    public void clickPasswordInputField() {
        passwordInputField.click();
    }

    public void setPasswordInputField(String password) {
        passwordInputField.sendKeys(password);
    }

    //loginButton
    public void clickLoginButton() {
        loginButton.click();
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
    public MyAccountPage login(String LOGIN_NAME, String PASSWORD_NAME){
        fillInputLogin(LOGIN_NAME);
        fillInputPassword(PASSWORD_NAME);
        clickLoginButton();
        return new MyAccountPage(driver);
    }
}