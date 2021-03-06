package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPageWithHeader {

    //RETURNING CUSTOMER
    @FindBy(how = How.XPATH, xpath = "//input[@id='input-password']/../../../h2")
    private WebElement titleLoginBlock;

    @FindBy(how = How.ID, id = "input-email")
    private WebElement loginInputField;

    @FindBy(how = How.ID, id = "input-password")
    private WebElement passwordInputField;

    @FindBy(how = How.XPATH, xpath = "//input[@value='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    public void clearPasswordInputField() {
        passwordInputField.clear();
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
    public void fillInputLogin(String login) {
        clickLoginInputField();
        clearLoginInputField();
        setLoginInputField(login);
    }

    //passwordInputField
    public void fillInputPassword(String password) {
        clickPasswordInputField();
        clearPasswordInputField();
        setPasswordInputField(password);
    }

    //BUSINESS LOGIC

    //login
    public MyAccountPage login(String login, String password) {
        fillInputLogin(login);
        fillInputPassword(password);
        clickLoginButton();
        return new MyAccountPage(driver);
    }

    public boolean isAlertPresent(){
        boolean present = false;
        try{
            driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
            present = true;
        }
        catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }
}