package com.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AddressBookContainersComponent {

    private WebElement address;
    private WebElement editAddressButton;
    private WebElement deleteAddressButton;
    private WebElement userFullName;
    private WebElement userAddress;
    private WebElement userCity;
    private WebElement userPostCode;
    private WebElement Country;
    private WebElement Region;

    private Boolean adressDefault;

    public AddressBookContainersComponent(WebElement singleAddress) {
        initElements(singleAddress);
    }

    private void initElements(WebElement singleAddress) {
        address = singleAddress.findElement(By.cssSelector(".text-left"));
        editAddressButton = singleAddress.findElement(By.cssSelector(".btn-info"));
        deleteAddressButton = singleAddress.findElement(By.cssSelector(".btn-danger"));
        // userFullName=singleAddress.findElement(By.xpath(""))
    }

    public String getAddressText() {
        return address.getText();
    }

    public void clickEditAddressButton() {
        editAddressButton.click();
    }

    public void clickDeleteAddressButton() {
        deleteAddressButton.click();
    }

    public boolean checkText(String x) {
        if (address.getText().contains(x)) {
            return true;
        } else return false;

    }
}

