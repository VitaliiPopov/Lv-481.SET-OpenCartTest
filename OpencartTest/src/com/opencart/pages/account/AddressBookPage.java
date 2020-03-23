package com.opencart.pages.account;


import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.tools.JsonDataConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AddressBookPage extends AbstractPageWithHeader {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    //Components
    private AccountSidebarComponent accountSidebarComponent;
    private List<AddressBookContainersComponent> addressBookContainersComponents;
    @FindBy(how = How.CSS, css = ".btn-primary")
    private WebElement newAdressButton;
    @FindBy(how = How.XPATH, xpath = "//*[@class='alert alert-success alert-dismissible']")
    private WebElement AdresssBookAlertMassages;
    @FindBy(how = How.CSS, css = ".alert")
    private WebElement adresssDeleteDefault;


    public AddressBookPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        InitializeAddressContainers();

    }

    private void InitializeAddressContainers() {
        List<WebElement> addressContainers = driver.findElements(By.xpath("//*[@id='content']/div[1]/table/tbody//tr"));
        //System.out.println(addressContainers.size()); //знаходить всі тр-ки
        //System.out.println(addressContainers.get(1).getText());

        addressBookContainersComponents = new ArrayList<AddressBookContainersComponent>();
        for (WebElement singleAddress : addressContainers) {
            addressBookContainersComponents.add(new AddressBookContainersComponent(singleAddress));

        }
    }

    public void getAddresBookText() {
        AddressBookContainersComponent result = null;
        for (AddressBookContainersComponent current : addressBookContainersComponents) {
            System.out.println(current.getAddressText());
        }
        //if (result == null) throw new RuntimeException();
    }

    public EditAdressPage clickNewAddress() {
        newAdressButton.click();
        return new EditAdressPage(driver);
    }

    public EditAdressPage clickEditAddress() {
        newAdressButton.click();
        return new EditAdressPage(driver);
    }

    public void clickDeleteDefaultAddress() {
        addressBookContainersComponents.get(0).clickDeleteAddressButton();
    }


    public AddressBookPage addAddressIfthereisnoaddress() {
        if (addressBookContainersComponents.size() == 1) {
            return clickNewAddress().register(jsonDataConfig.getFirstNameFromJson(3), jsonDataConfig.getLastNameFromJson(3), jsonDataConfig.getAddressFromJson(3), jsonDataConfig.getCityFromJson(3), jsonDataConfig.getCountryFromJson(3), jsonDataConfig.getRegionFromJson(3));
        }
        return new AddressBookPage(driver);
    }

    public void deleteLastElement() {
        addressBookContainersComponents.get(addressBookContainersComponents.size() - 1).clickDeleteAddressButton();
    }

    public EditAdressPage clickEditAddresss() {
        addressBookContainersComponents.get(0).clickEditAddressButton();
        return new EditAdressPage(driver);
    }

    public boolean checkAddresTextCorect(String FIRST_NAME, String LAST_NAME, String ADDRESS, String CITY, String COUNTRY, String REGION) {
        String actualAddress = addressBookContainersComponents.get(0).getAddressText();
        System.out.println(actualAddress + "\n");
        String rightAddress = FIRST_NAME + " " + LAST_NAME + "\n" + ADDRESS + "\n" + CITY + "\n" + REGION + "\n" + COUNTRY;
        System.out.println(rightAddress);
        return rightAddress.equalsIgnoreCase(actualAddress);
    }

    //ALERTS FUNCTIONS
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
        if (AdresssBookAlertMassages.getText().equalsIgnoreCase("Your address has been successfully updated")) {
            return AdresssBookAlertMassages.isDisplayed();
        } else return false;

    }

    public boolean defaultDeleteAlert() {
        if (adresssDeleteDefault.getText().equalsIgnoreCase("Warning: You must have at least one address!")) {
            return adresssDeleteDefault.isDisplayed();
        } else return false;

    }
}
