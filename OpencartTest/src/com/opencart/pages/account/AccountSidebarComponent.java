package com.opencart.pages.account;

import com.opencart.tools.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AccountSidebarComponent {

    public AccountSidebarComponent(WebDriver driver) {
        initElements();
    }

    private void initElements(){

    }
}

    /*private WebElement myAccountSideBarLink;
    private WebElement editAccountSideBarLink;
    private WebElement changePasswordSideBarLink;
    private WebElement logoutSideBarLink;

    public AccountSidebarComponent(WebDriver driver) {
        InitializeElements(driver);
    }

    private void InitializeElements(WebDriver driver){
        myAccountSideBarLink = driver.findElement(By.xpath("//*[@id='column-right']/div/a[contains(text(),'My Account')]"));
        editAccountSideBarLink = driver.findElement(By.xpath("//*[@id='column-right']/div/a[contains(text(),'Edit')]"));
        changePasswordSideBarLink = driver.findElement(By.xpath("//*[@id='column-right']/div/a[contains(text(),'Password')]"));
        logoutSideBarLink = driver.findElement(By.xpath("//*[@id='column-right']/div/a[contains(text(),'Logout')]"));
    }

    public MyAccountPage clickMyAccountSideBar(){
        myAccountSideBarLink.click();
        return new MyAccountPage(Driver.getDriver());
    }

    public EditAccountPage clickEditAccountSideBar(){
        editAccountSideBarLink.click();
        return new EditAccountPage(Driver.getDriver());
    }

    public ChangePasswordPage clickPasswordSideBar(){
        changePasswordSideBarLink.click();
        return new ChangePasswordPage(Driver.getDriver());
    }

    public AccountLogoutPage clickLogoutSideBar(){
        logoutSideBarLink.click();
        return new AccountLogoutPage(Driver.getDriver());
    }
*/
