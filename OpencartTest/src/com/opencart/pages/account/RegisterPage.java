package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    @FindBy(how = How.CSS, css = "#content>h1")
    private WebElement titleRegisterBlock;

    @FindBy(how = How.ID, id = "input-firstname")
    private WebElement firstnameInputField;

    @FindBy(how = How.ID, id = "input-lastname")
    private WebElement lastnameInputField;

    @FindBy(how = How.ID, id = "input-email")
    private WebElement emailInputField;

    @FindBy(how = How.ID, id = "input-telephone")
    private WebElement telephoneInputField;

    @FindBy(how = How.ID, id = "input-password")
    private WebElement passwordInputField;

    @FindBy(how = How.ID, id = "input-confirm")
    private WebElement confirmPasswordInputField;

    @FindBy(how = How.CSS, css = "div.pull-right>input[name='agree']")
    private WebElement agreeCheckBox;

    @FindBy(how = How.CSS, css = "div.pull-right>input[value='Continue']")
    private WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //titleRegisterBlock
    public String getTitleRegisterBlockText() {
        return titleRegisterBlock.getText();
    }

    //firstnameInputField
    public void clickFirstnameInputField() {
        firstnameInputField.click();
    }

    public void clearFirstnameInputField() {
        firstnameInputField.clear();
    }

    public void setFirstnameInputField(String login) {
        firstnameInputField.sendKeys(login);
    }

    //lastnameInputField
    public void clickLastnameInputField() {
        lastnameInputField.click();
    }

    public void clearLastnameInputField() {
        lastnameInputField.clear();
    }

    public void setLastnameInputField(String login) {
        lastnameInputField.sendKeys(login);
    }

    //emailInputField
    public void clickEmailInputField() {
        emailInputField.click();
    }

    public void clearEmailInputField() {
        emailInputField.clear();
    }

    public void setEmailInputField(String login) {
        emailInputField.sendKeys(login);
    }

    //telephoneInputField
    public void clickTelephoneInputField() {
        telephoneInputField.click();
    }

    public void clearTelephoneInputField() {
        telephoneInputField.clear();
    }

    public void setTelephoneInputField(String login) {
        telephoneInputField.sendKeys(login);
    }

    //passwordInputField
    public void clickPasswordInputField() {
        passwordInputField.click();
    }

    public void clearPasswordInputField() {
        passwordInputField.clear();
    }

    public void setPasswordInputField(String login) {
        passwordInputField.sendKeys(login);
    }

    //confirmPasswordInputField
    public void clickConfirmPasswordInputField() {
        confirmPasswordInputField.click();
    }

    public void clearConfirmPasswordInputField() {
        confirmPasswordInputField.clear();
    }

    public void setConfirmPasswordInputField(String login) {
        confirmPasswordInputField.sendKeys(login);
    }

    //agreeCheckBox
    public void clickAgreeCheckBox() {
        agreeCheckBox.click();
    }

    //continueButton
    public void clickRegisterButton() {
        registerButton.click();
    }

    //FUNCTIONAL

    //firstnameInputField
    public void fillInputFirstname(String firstname){
        clickFirstnameInputField();
        clearFirstnameInputField();
        setFirstnameInputField(firstname);
    }

    //lastnameInputField
    public void fillInputLastname(String lastname){
        clickLastnameInputField();
        clearLastnameInputField();
        setLastnameInputField(lastname);
    }

    //emailInputField
    public void fillInputEmail(String email){
        clickEmailInputField();
        clearEmailInputField();
        setEmailInputField(email);
    }

    //telephoneInputField
    public void fillInputTelephone(String telephone){
        clickTelephoneInputField();
        clearTelephoneInputField();
        setTelephoneInputField(telephone);
    }

    //passwordInputField
    public void fillInputPassword(String password){
        clickPasswordInputField();
        clearPasswordInputField();
        setPasswordInputField(password);
    }

    //confirmPasswordInputField
    public void fillInputConfirmPassword(String confirmPassword){
        clickConfirmPasswordInputField();
        clearConfirmPasswordInputField();
        setConfirmPasswordInputField(confirmPassword);
    }

    //BUSINESS LOGIC

    //register
    public SuccessRegisterPage register(String FIRST_NAME, String LAST_NAME, String EMAIL, String PHONE, String PASSWORD_NAME, String CONFIRM_PASSWORD_NAME){
        fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        fillInputEmail(EMAIL);
        fillInputTelephone(PHONE);
        fillInputPassword(PASSWORD_NAME);
        fillInputConfirmPassword(CONFIRM_PASSWORD_NAME);
        clickAgreeCheckBox();
        clickRegisterButton();
        return new SuccessRegisterPage(driver);
    }

}
