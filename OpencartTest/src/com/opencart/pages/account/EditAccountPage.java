package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EditAccountPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    @FindBy(how = How.CSS, css = "#content>h1")
    private WebElement titleAccountInformation;

    @FindBy(how = How.ID, id = "input-firstname")
    private WebElement firstnameEditField;

    @FindBy(how = How.ID, id = "input-lastname")
    private WebElement lastnameEditField;

    @FindBy(how = How.ID, id = "input-email")
    private WebElement emailEditField;

    @FindBy(how = How.ID, id = "input-telephone")
    private WebElement telephoneEditField;

    @FindBy(how = How.CSS, css = "div.pull-right>input[value='Continue']")
    private WebElement editButton;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-firstname']/following-sibling::div")
    private WebElement alertBadFirstname;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-lastname']/following-sibling::div")
    private WebElement alertBadLastname;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-email']/following-sibling::div")
    private WebElement alertBadEmail;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-telephone']/following-sibling::div")
    private WebElement alertBadTelephone;

    @FindBy(how = How.CSS, css = "div[class='alert alert-danger alert-dismissible']")
    private WebElement warning;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //titleAccountInformation
    public String getTitleAccountInformationText() {
        return titleAccountInformation.getText();
    }

    public String getFirstNameEditValue() {
        return firstnameEditField.getAttribute("value");
    }

    public String getLastNameEditValue() {
        return lastnameEditField.getAttribute("value");
    }

    public String getEmailEditValue() {
        return emailEditField.getAttribute("value");
    }

    public String getTelephoneEditValue() {
        return telephoneEditField.getAttribute("value");
    }

    //firstnameEditField
    public void clickFirstnameEditField() {
        firstnameEditField.click();
    }

    public void clearFirstnameEditField() {
        firstnameEditField.clear();
    }

    public void setFirstnameEditField(String login) {
        firstnameEditField.sendKeys(login);
    }

    //lastnameEditField
    public void clickLastnameEditField() {
        lastnameEditField.click();
    }

    public void clearLastnameEditField() {
        lastnameEditField.clear();
    }

    public void setLastnameEditField(String login) {
        lastnameEditField.sendKeys(login);
    }

    //emailEditField
    public void clickEmailEditField() {
        emailEditField.click();
    }

    public void clearEmailEditField() {
        emailEditField.clear();
    }

    public void setEmailEditField(String login) {
        emailEditField.sendKeys(login);
    }

    //telephoneEditField
    public void clickTelephoneEditField() {
        telephoneEditField.click();
    }

    public void clearTelephoneEditField() {
        telephoneEditField.clear();
    }

    public void setTelephoneEditField(String login) {
        telephoneEditField.sendKeys(login);
    }

    //continueButton
    public void clickEditButton() {
        editButton.click();
    }

    //FUNCTIONAL

    public void clearFields() {
        clearFirstnameEditField();
        clearLastnameEditField();
        clearEmailEditField();
        clearTelephoneEditField();
    }

    //firstnameEditField
    public void editFirstnameField(String firstname) {
        clickFirstnameEditField();
        clearFirstnameEditField();
        setFirstnameEditField(firstname);
    }

    //lastnameEditField
    public void editLastnameField(String lastname) {
        clickLastnameEditField();
        clearLastnameEditField();
        setLastnameEditField(lastname);
    }

    //emailEditField
    public void editEmailField(String email) {
        clickEmailEditField();
        clearEmailEditField();
        setEmailEditField(email);
    }

    //telephoneEditField
    public void editTelephoneField(String telephone) {
        clickTelephoneEditField();
        clearTelephoneEditField();
        setTelephoneEditField(telephone);
    }


    //BUSINESS LOGIC

    //register
    public MyAccountPage editAccountInformation(String firstname, String lastname, String email, String phone) {
        editFirstnameField(firstname);
        editLastnameField(lastname);
        editEmailField(email);
        editTelephoneField(phone);
        clickEditButton();
        return new MyAccountPage(driver);
    }

    public boolean isAlertFirstnameDisplayed() {
        return alertBadFirstname.isDisplayed();
    }

    public boolean isAlertLastnameDisplayed() {
        return alertBadLastname.isDisplayed();
    }

    public boolean isAlertEmailDisplayed() {
        return alertBadEmail.isDisplayed();
    }

    public boolean isAlertTelephoneDisplayed() {
        return alertBadTelephone.isDisplayed();
    }

    public boolean isWarningDisplayed() {
        return warning.isDisplayed();
    }
}