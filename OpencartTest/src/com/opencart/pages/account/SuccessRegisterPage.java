package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SuccessRegisterPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    @FindBy(how = How.CSS, css = "#content>h1")
    private WebElement titleSuccessRegistration;

    @FindBy(how = How.CSS, css = "div.pull-right>a")
    private WebElement continueButton;

    public SuccessRegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //PAGE OBJECT

    //titleSuccessRegistration
    public String getTitleSuccessRegistrationText() {
        return titleSuccessRegistration.getText();
    }

    //continueButton
    public void clickContinueButton() {
        continueButton.click();
    }

    //BUSINESS LOGIC

    //go to Account after success registration
    public MyAccountPage goToAccountAfterRegistration() {
        clickContinueButton();
        return new MyAccountPage(driver);
    }

}