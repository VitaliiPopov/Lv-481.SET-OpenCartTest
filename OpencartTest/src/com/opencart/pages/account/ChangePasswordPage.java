package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    @FindBy(how = How.CSS, css = "#content>h1")
    private WebElement titleChangePassword;

    @FindBy(how = How.ID, id = "input-password")
    private WebElement passwordChangeField;

    @FindBy(how = How.ID, id = "input-confirm")
    private WebElement confirmPasswordChangeField;

    @FindBy(how = How.CSS, css = "div.pull-right>input[value='Continue']")
    private WebElement changePasswordButton;

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        accountSidebarComponent = new AccountSidebarComponent(driver);
    }

    public AccountSidebarComponent getAccountSidebarComponent() {
        return accountSidebarComponent;
    }

    //titleChangePassword
    public String getTitleAccountInformationText() {
        return titleChangePassword.getText();
    }

    //passwordChangeField
    public void clickPasswordChangeField() {
        passwordChangeField.click();
    }

    public void clearPasswordChangeField() {
        passwordChangeField.clear();
    }

    public void setPasswordChangeField(String login) {
        passwordChangeField.sendKeys(login);
    }

    //confirmPasswordChangeField
    public void clickConfirmPasswordChangeField() {
        confirmPasswordChangeField.click();
    }

    public void clearConfirmPasswordChangeField() {
        confirmPasswordChangeField.clear();
    }

    public void setConfirmPasswordChangeField(String login) {
        confirmPasswordChangeField.sendKeys(login);
    }

    //continueButton
    public void clickChangePasswordButton() {
        changePasswordButton.click();
    }

    //passwordChangeField
    public void changePasswordField(String password) {
        clickPasswordChangeField();
        clearPasswordChangeField();
        setPasswordChangeField(password);
    }

    //confirmPasswordChangeField
    public void changeConfirmPasswordField(String confirmPassword) {
        clickConfirmPasswordChangeField();
        clearConfirmPasswordChangeField();
        setConfirmPasswordChangeField(confirmPassword);
    }

    //BUSINESS LOGIC

    //change password
    public MyAccountPage changePassword(String password, String confirm) {
        changePasswordField(password);
        changeConfirmPasswordField(confirm);
        clickChangePasswordButton();
        return new MyAccountPage(driver);
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