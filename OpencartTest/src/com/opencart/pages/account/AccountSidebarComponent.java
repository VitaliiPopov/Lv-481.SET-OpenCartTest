package com.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountSidebarComponent {

    private WebElement myAccountSideBarLink;
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

    public void clickMyAccountSideBar(){
        myAccountSideBarLink.click();
    }

    public void clickEditAccountSideBar(){
        editAccountSideBarLink.click();
    }

    public void clickPasswordSideBar(){
        changePasswordSideBarLink.click();
    }

    public void clickLogoutSideBar(){
        logoutSideBarLink.click();
    }

}