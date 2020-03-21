package com.opencart.pages.admin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminCustomerPage extends AdminHomePage {

    @FindBy(how = How.CSS, css = "table[class='table table-bordered table-hover']")
    private WebElement customerTable;

    @FindBy(how = How.XPATH, xpath = "//button[@class='btn btn-danger']")
    private WebElement deleteCustomerButton;

    @FindBy(how = How.XPATH, xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement successModifyAlert;

    public AdminCustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void findCustomerByEmail(String optionName){
        String xpath = "//td[text()='"+optionName+"']/preceding-sibling::td/input";
        WebElement сustomerCheckBox = driver.findElement(By.xpath(xpath));
        clickCustomer(сustomerCheckBox);
    }

    //searchedCustomer
    public void clickCustomer(WebElement searchedCustomer) {
        searchedCustomer.click();
    }

    //deleteCustomerButton
    public void clickDeleteCustomerButton() {
        deleteCustomerButton.click();
    }

    public void confirmAction() {
        driver.switchTo().alert().accept();
    }

    //successModifyAlert
    public boolean isSuccessModifyAlertDisplayed(){
        return successModifyAlert.isDisplayed();
    }
}
