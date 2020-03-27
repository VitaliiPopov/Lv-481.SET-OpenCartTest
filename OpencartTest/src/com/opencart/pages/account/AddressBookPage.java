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
    private List<AddressBookContainersComponent> addressBookContainersComponents;

    @FindBy(how = How.CSS, css = ".btn-primary")
    private WebElement newAdressButton;

    @FindBy(how = How.XPATH, xpath = "//*[@class='alert alert-success alert-dismissible']")
    private WebElement adresssBookAlertMessages;

    @FindBy(how = How.CSS, css = ".alert")
    private WebElement adresssDeleteDefaultAlert;


    public AddressBookPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        initializeAddressContainers();
    }

    public EditAdressPage clickNewAddress() {
        newAdressButton.click();
        return new EditAdressPage(driver);
    }

    public EditAdressPage clickEditAddress() {
        newAdressButton.click();
        return new EditAdressPage(driver);
    }

    private void initializeAddressContainers() {
        List<WebElement> addressContainers = driver.findElements(By.xpath("//*[@id='content']/div[1]/table/tbody//tr"));
        addressBookContainersComponents = new ArrayList<AddressBookContainersComponent>();
        for (WebElement singleAddress : addressContainers) {
            addressBookContainersComponents.add(new AddressBookContainersComponent(singleAddress));
        }
    }

    //Functional
    public void deleteAll() {
        int count = addressBookContainersComponents.size();
        while (count != 1) {
            /*addressBookContainersComponents.get(count-1).clickDeleteAddressButton();*/
            driver.findElement(By.cssSelector(".btn-danger")).click();
            count--;
        }
    }

    public void deleteDefaultAddress() {
        addressBookContainersComponents.get(0).clickDeleteAddressButton();
    }

    public AddressBookPage addAdressIfEmpty() {
        if (addressBookContainersComponents.size() == 1) {
            return clickNewAddress().register(jsonDataConfig.getFirstNameFromJson(3), // Adding default valid test address from json
                    jsonDataConfig.getLastNameFromJson(3),
                    jsonDataConfig.getAddressFromJson(3),
                    jsonDataConfig.getCityFromJson(3),
                    jsonDataConfig.getCountryFromJson(3),
                    jsonDataConfig.getRegionFromJson(3));
        }
        return new AddressBookPage(driver);
    }

    public void deleteLastElement() {
        addressBookContainersComponents.get(addressBookContainersComponents.size()-1).clickDeleteAddressButton();
    }

    public EditAdressPage clickEditAddresss() {
        addressBookContainersComponents.get(0).clickEditAddressButton();
        return new EditAdressPage(driver);
    }

    public boolean checkAddresTextCorect(String firstName, String lastName, String address, String city, String country, String region) {
        String actualAddress = addressBookContainersComponents.get(0).getAddressText();
        String rightAddress = firstName + " " + lastName + "\n" + address + "\n" + city + "\n" + region + "\n" + country;
        return rightAddress.equalsIgnoreCase(actualAddress);
    }

    //ALERTS FUNCTIONS
    public boolean succesAddingAlert() {
        if (adresssBookAlertMessages.getText().equalsIgnoreCase("Your address has been successfully added")) {
            return adresssBookAlertMessages.isDisplayed();
        } else return false;

    }

    public boolean succesDeletingAlert() {
        if (adresssBookAlertMessages.getText().equalsIgnoreCase("Your address has been successfully deleted")) {
            return adresssBookAlertMessages.isDisplayed();
        } else return false;

    }

    public boolean successEditingAlert() {
        if (adresssBookAlertMessages.getText().equalsIgnoreCase("Your address has been successfully updated")) {
            return adresssBookAlertMessages.isDisplayed();
        } else return false;

    }

    public boolean defaultDeleteAlert() {
        if (adresssDeleteDefaultAlert.getText().equalsIgnoreCase("Warning: You must have at least one address!")) {
            return adresssDeleteDefaultAlert.isDisplayed();
        } else return false;
    }
}