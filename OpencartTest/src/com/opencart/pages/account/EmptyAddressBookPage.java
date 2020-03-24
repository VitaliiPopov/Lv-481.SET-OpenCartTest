package com.opencart.pages.account;


import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EmptyAddressBookPage extends AbstractPageWithHeader {

    //Components
    private AccountSidebarComponent accountSidebarComponent;
   // private List<AddressBookContainersComponent> addressBookContainersComponents;
    @FindBy(how = How.CSS, css = ".btn-primary")
    private WebElement newAdressButton;
    @FindBy(how = How.XPATH, xpath = "//*[@class='alert alert-success alert-dismissible']")
    private WebElement AdresssBookAlertMassages;


    public EmptyAddressBookPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean succesAddingAlert() {
        if (AdresssBookAlertMassages.getText().equalsIgnoreCase("Your address has been successfully added")) {
            return AdresssBookAlertMassages.isDisplayed();
        } else return false;

    }
    public boolean succesDeletingAlert() {
        if (AdresssBookAlertMassages.getText().equalsIgnoreCase("Your address has been successfully deleted")) {
            return AdresssBookAlertMassages.isDisplayed();
        } else return false;

    }
    public boolean successEditingAlert() {
        if (AdresssBookAlertMassages.getText().equalsIgnoreCase(" Your address has been successfully updated")) {
            return AdresssBookAlertMassages.isDisplayed();
        } else return false;

    }

    public EditAdressPage clickNewAddress() {
        newAdressButton.click();
        return new EditAdressPage(driver);
    }
    public EditAdressPage clickEditAddress() {

        newAdressButton.click();
        return new EditAdressPage(driver);
    }

}

