package com.opencart.pages.account;

import com.opencart.data.users.CustomUser;
import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends AbstractPageWithHeader {

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

    @FindBy(how = How.CSS, css = "div[class='alert alert-danger alert-dismissible']")
    private WebElement warning;

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
    public void fillInputFirstname(String firstname) {
        clickFirstnameInputField();
        clearFirstnameInputField();
        setFirstnameInputField(firstname);
    }

    //lastnameInputField
    public void fillInputLastname(String lastname) {
        clickLastnameInputField();
        clearLastnameInputField();
        setLastnameInputField(lastname);
    }

    //emailInputField
    public void fillInputEmail(String email) {
        clickEmailInputField();
        clearEmailInputField();
        setEmailInputField(email);
    }

    //telephoneInputField
    public void fillInputTelephone(String telephone) {
        clickTelephoneInputField();
        clearTelephoneInputField();
        setTelephoneInputField(telephone);
    }

    //passwordInputField
    public void fillInputPassword(String password) {
        clickPasswordInputField();
        clearPasswordInputField();
        setPasswordInputField(password);
    }

    //confirmPasswordInputField
    public void fillInputConfirmPassword(String confirmPassword) {
        clickConfirmPasswordInputField();
        clearConfirmPasswordInputField();
        setConfirmPasswordInputField(confirmPassword);
    }

    //BUSINESS LOGIC

    //register
    public SuccessRegisterPage register(String firstname, String lastname, String email, String phone, String password, String confirm) {
        fillInputFirstname(firstname);
        fillInputLastname(lastname);
        fillInputEmail(email);
        fillInputTelephone(phone);
        fillInputPassword(password);
        fillInputConfirmPassword(confirm);
        clickAgreeCheckBox();
        clickRegisterButton();
        return new SuccessRegisterPage(driver);
    }

    public SuccessRegisterPage register(CustomUser user) {
        fillInputFirstname(user.getFirstName());
        fillInputLastname(user.getLastName());
        fillInputEmail(user.getEmail());
        fillInputTelephone(user.getTelephone());
        fillInputPassword(user.getPassword());
        fillInputConfirmPassword(user.getPassword());
        clickAgreeCheckBox();
        clickRegisterButton();
        return new SuccessRegisterPage(driver);
    }

    public String getWarningText() {
        return warning.getText();
    }

    public boolean isFirstNameAlertPresent(){
        boolean present = false;
        try{
            driver.findElement(By.xpath("//*[@id='input-firstname']/following-sibling::div"));
            present = true;
        }
        catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }

    public boolean isLastNameAlertPresent(){
        boolean present = false;
        try{
            driver.findElement(By.xpath("//*[@id='input-lastname']/following-sibling::div"));
            present = true;
        }
        catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }

    public boolean isEmailAlertPresent(){
        boolean present = false;
        try{
            driver.findElement(By.xpath("//*[@id='input-email']/following-sibling::div"));
            present = true;
        }
        catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }

    public boolean isTelephoneAlertPresent(){
        boolean present = false;
        try{
            driver.findElement(By.xpath("//*[@id='input-telephone']/following-sibling::div"));
            present = true;
        }
        catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }

    public boolean isPasswordAlertPresent(){
        boolean present = false;
        try{
            driver.findElement(By.xpath("//*[@id='input-password']/following-sibling::div"));
            present = true;
        }
        catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }

    public boolean isConfirmAlertPresent(){
        boolean present = false;
        try{
            driver.findElement(By.xpath("//*[@id='input-confirm']/following-sibling::div"));
            present = true;
        }
        catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }
}