package com.opencart.pages.admin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminCustomerPage extends AdminHomePage {

    @FindBy(how = How.XPATH, xpath = "//td[contains(text(), 'aaa@gmail.com')]/preceding-sibling::td//preceding-sibling::td")
    private WebElement chooseCustomerCheckBox;

    @FindBy(how = How.XPATH, xpath = "//button[@class='btn btn-danger']")
    private WebElement deleteCustomerButton;

    //alertMessage

    @FindBy(how = How.XPATH, xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement successModifyAlert;

    public AdminCustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    //chooseCustomerCheckBox
    public void chooseCustomer() {
        chooseCustomerCheckBox.click();
    }


    //deleteCustomerButton
    public void clickDeleteCustomerButton() {
        deleteCustomerButton.click();
    }

    //successModifyAlert
    public boolean isSuccessModifyAlertDisplayed(){
        return successModifyAlert.isDisplayed();
    }
}
