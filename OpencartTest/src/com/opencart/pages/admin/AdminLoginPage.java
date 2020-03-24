package com.opencart.pages.admin;

import com.opencart.pages.account.MyAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {

    protected WebDriver driver;
    @FindBy(how = How.ID, id = "input-username")
    private WebElement adminUsernameField;

    @FindBy(how = How.ID, id = "input-password")
    private WebElement adminPasswordField;

    @FindBy(how = How.CSS, css = "button[type=submit]")
    private WebElement adminLoginButton;


    public AdminLoginPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void setAdminUsernameField(String login){
        adminUsernameField.click();
        adminUsernameField.clear();
        adminUsernameField.sendKeys(login);
    }

    public void setAdminPasswordField(String password){
        adminPasswordField.click();
        adminPasswordField.clear();
        adminPasswordField.sendKeys(password);
    }

    public void clickAdminLoginButton(){
        adminLoginButton.click();
    }

    public AdminHomePage adminLogin(String LOGIN_NAME, String PASSWORD_NAME){
        setAdminUsernameField(LOGIN_NAME);
        setAdminPasswordField(PASSWORD_NAME);
        clickAdminLoginButton();
        return new AdminHomePage(driver);
    }
}