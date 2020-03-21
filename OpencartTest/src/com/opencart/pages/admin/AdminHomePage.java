package com.opencart.pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminHomePage {
    protected WebDriver driver;
    @FindBy(how = How.XPATH, xpath = "a[@href='#collapse1']")
    private WebElement catalogDropdown;
    @FindBy(how = How.XPATH, xpath = "a[contains(text(), \"Reviews\")]")
    private WebElement reviewTab;
    @FindBy(how = How.XPATH, xpath = "a[@href='#collapse5']")
    private WebElement customerDropdown;
    @FindBy(how = How.XPATH, xpath = "ul[@id='collapse5']/li/a[contains(text(), 'Customers')]")
    private WebElement customerTab;

    public AdminHomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCatalogDropdown(){
        catalogDropdown.click();
    }

    public void clickOnReviewTab(){
        reviewTab.click();
    }

    public void clickOnCustomerDropdown(){
        customerDropdown.click();
    }

    public AdminCustomerPage clickOnCustomerTab(){
        customerTab.click();
        customerTab.clear();
        return new AdminCustomerPage(driver);
    }

}
