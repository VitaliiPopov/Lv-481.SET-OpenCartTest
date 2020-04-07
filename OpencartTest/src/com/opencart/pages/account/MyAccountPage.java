package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends AbstractPageWithHeader {

    @FindBy(how = How.CSS, css = "#content>h2")
    private WebElement titleMyAccount;

    @FindBy(how = How.XPATH, xpath = "//*[@id='content']/ul/li/a[contains(text(),'Edit')]")
    private WebElement editMyAccountLink;

    @FindBy(how = How.XPATH, xpath = "//*[@id='content']/ul/li/a[contains(text(),'password')]")
    private WebElement changePasswordLink;

    @FindBy(how = How.XPATH, xpath = "//a[.='Modify your address book entries']")
    private WebElement adressLink;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //titleMyAccount
    public String getTitleMyAccountText() {
        return titleMyAccount.getText();
    }

    //editMyAccountLink
    public EditAccountPage clickEditMyAccountLink() {
        editMyAccountLink.click();
        return new EditAccountPage(driver);
    }

    public boolean isSuccessAlertPresent(){
        boolean present = false;
        try{
            driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
            present = true;
        }
        catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }

    //changePasswordLink
    public ChangePasswordPage clickChangePasswordLink() {
        changePasswordLink.click();
        return new ChangePasswordPage(driver);
    }

    public EmptyAddressBookPage clickModifyyouraddresswhuileempty() {
        adressLink.click();
        return new EmptyAddressBookPage(driver);
    }

    //Addrespage link
    public AddressBookPage clickModifyYourAddressBookEntries() {
        adressLink.click();
        return new AddressBookPage(driver);
    }
}