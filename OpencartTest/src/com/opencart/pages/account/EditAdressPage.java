package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditAdressPage extends AbstractPageWithHeader {

    @FindBy(how = How.XPATH, xpath = "//input[@id='input-firstname']")
    private WebElement firstNameInput;

    @FindBy(how = How.XPATH, xpath = "//input[@id='input-lastname']")
    private WebElement lastNameInput;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-address-1']")
    private WebElement addressInput;

    @FindBy(how = How.XPATH, xpath = "//input[@id='input-city']")
    private WebElement cityInput;

    @FindBy(how = How.XPATH, xpath = "//select[@id='input-country']")
    private WebElement countryDropdown;

    @FindBy(how = How.XPATH, xpath = "//select[@id='input-zone']")
    private WebElement regionStateDropdown;

    @FindBy(how = How.XPATH, xpath = "//input[@value='1']")
    private WebElement defaultAddresRadioB;

    @FindBy(how = How.XPATH, xpath = "//input[@value='0']")
    private WebElement notDefaultAddresRadioB;

    @FindBy(how = How.XPATH, xpath = "//input[@class='btn btn-primary']")
    private WebElement continueSubmitButton;

    @FindBy(how = How.XPATH, xpath = "#content>h2")
    private WebElement addAdressPageTitle;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameAlert;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameAlert;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-address-1']/following-sibling::div")
    private WebElement addressAlert;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-city']/following-sibling::div")
    private WebElement cityAlert;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-postcode']/following-sibling::div")
    private WebElement postcodeAlert;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-country']/following-sibling::div")
    private WebElement countryAlert;

    @FindBy(how = How.XPATH, xpath = "//*[@id='input-zone']/following-sibling::div")
    private WebElement regionAlert;

    public EditAdressPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitleRegisterBlockText() {
        return addAdressPageTitle.getText();
    }

    //firstnameInputField
    public void clickFirstnameInputField() {
        firstNameInput.click();
    }

    public void clearFirstnameInputField() {
        firstNameInput.clear();
    }

    public void setFirstnameInputField(String login) {
        firstNameInput.sendKeys(login);
    }

    //lastnameInputField
    public void clickLastnameInputField() {
        lastNameInput.click();
    }

    public void clearLastnameInputField() {
        lastNameInput.clear();
    }

    public void setLastnameInputField(String login) {
        lastNameInput.sendKeys(login);
    }

    //address InputField
    public void clickAddresInputField() {
        addressInput.click();
    }

    public void clearAddresInputField() {
        addressInput.clear();
    }

    public void setAddresInputField(String login) {
        addressInput.sendKeys(login);
    }

    //inputField
    public void clickCityInputField() {
        cityInput.click();
    }

    public void clearCityInputField() {
        cityInput.clear();
    }

    public void setCityInputField(String login) {
        cityInput.sendKeys(login);
    }

    //Country dropdownfield
    public void selectCountry(String country) {
        Select statusDropdown = new Select(countryDropdown);
        statusDropdown.selectByVisibleText(country);
    }

    public void selectCountryDefault() {
        Select statusDropdown = new Select(countryDropdown);
        statusDropdown.selectByVisibleText("--- Please Select ---");
    }

    //RegionDropdown
    public void selectRegion(String region) {
        Select statusDropdown = new Select(regionStateDropdown);
        statusDropdown.selectByVisibleText(region);
    }

    public void selectRegionDefault() {
        Select statusDropdown = new Select(regionStateDropdown);
        statusDropdown.selectByVisibleText("--- None ---");
    }
    //continueButton
    public void clickContinueSubmitButton() {
        continueSubmitButton.click();
    }
    //FUNCTIONAL


    //firstnameInputField
    public void inputFirstname(String firstname) {
        clickFirstnameInputField();
        clearFirstnameInputField();
        setFirstnameInputField(firstname);
    }

    //firstnameInputField
    public void inputLastname(String lastname) {
        clickLastnameInputField();
        clearLastnameInputField();
        setLastnameInputField(lastname);
    }

    //addressinputField
    public void inputAdress(String addres) {
        clickAddresInputField();
        clearAddresInputField();
        setAddresInputField(addres);
    }

    //City InputField
    public void inputCity(String city) {
        clickCityInputField();
        clearCityInputField();
        setCityInputField(city);
    }

    //Alerts
    public boolean firstNameAlert() {
        if (firstNameAlert.getText().equalsIgnoreCase("First Name must be between 1 and 32 characters!")) {
            return firstNameAlert.isDisplayed();
        } else return false;
    }

    public boolean lastNameAlert() {
        if (lastNameAlert.getText().equalsIgnoreCase("Last Name must be between 1 and 32 characters!")) {
            return lastNameAlert.isDisplayed();
        } else return false;
    }

    public boolean addressAlert() {
        if (addressAlert.getText().equalsIgnoreCase("Address must be between 3 and 128 characters!")) {
            return addressAlert.isDisplayed();
        } else return false;
    }

    public boolean cityAlert() {
        if (cityAlert.getText().equalsIgnoreCase("City must be between 2 and 128 characters!")) {
            return firstNameAlert.isDisplayed();
        } else return false;
    }

    public boolean countryAlert() {
        if (countryAlert.getText().equalsIgnoreCase("Please select a country!")) {
            return countryAlert.isDisplayed();
        } else return false;
    }

    public boolean regionAlert() {
        if (regionAlert.getText().equalsIgnoreCase("Please select a region / state!")) {
            return regionAlert.isDisplayed();
        } else return false;
    }

    public boolean allMandatoryAlerts() {
        if (addressAlert() && firstNameAlert() && lastNameAlert() && cityAlert()) {
            return true;
        } else return false;
    }

    public boolean firstLastNameAlerts() {
        if (firstNameAlert() && lastNameAlert()) {
            return true;
        } else return false;
    }

    //Busines logic
    public AddressBookPage register(String firstName, String lastName, String address, String city, String country, String region) {
        inputFirstname(firstName);
        inputLastname(lastName);
        inputAdress(address);
        inputCity(city);
        selectCountry(country);
        selectRegion(region);
        clickContinueSubmitButton();
        return new AddressBookPage(driver);
    }

    public AddressBookPage editUser(String firstName, String lastName, String address, String city, String country, String region) {
        inputFirstname(firstName);
        inputLastname(lastName);
        inputAdress(address);
        inputCity(city);
        selectCountry(country);
        selectRegion(region);
        clickContinueSubmitButton();
        return new AddressBookPage(driver);
    }

    public void emptyFirstName(String lastName, String address, String city, String country, String region) {
        inputLastname(lastName);
        inputAdress(address);
        inputCity(city);
        selectCountry(country);
        selectRegion(region);
        clickContinueSubmitButton();
    }

    public void emptyLastName(String firstName, String address, String city, String country, String region) {
        inputFirstname(firstName);
        inputAdress(address);
        inputCity(city);
        selectCountry(country);
        selectRegion(region);
        clickContinueSubmitButton();
    }

    public void emptyAddress(String firstName, String lastName, String city, String country, String region) {
        inputFirstname(firstName);
        inputLastname(lastName);
        inputCity(city);
        selectCountry(country);
        selectRegion(region);
        clickContinueSubmitButton();
    }

    public void emptyCity(String firstName, String lastName, String address, String country, String region) {
        inputFirstname(firstName);
        inputLastname(lastName);
        inputAdress(address);
        selectCountry(country);
        selectRegion(region);
        clickContinueSubmitButton();

    }

    public void emptyCountry(String firstName, String lastName, String address, String city) {
        inputFirstname(firstName);
        inputLastname(lastName);
        inputAdress(address);
        inputCity(city);
        selectCountryDefault();
        clickContinueSubmitButton();

    }

    public void emptyRegion(String firstName, String lastName, String address, String city, String country) {
        inputFirstname(firstName);
        inputLastname(lastName);
        inputAdress(address);
        inputCity(city);
        selectCountry(country);
        clickContinueSubmitButton();
    }

    public void invalidInputsAlertCheck(String firstName, String lastName, String address, String city, String country, String region) {
        inputFirstname(firstName);
        inputLastname(lastName);
        inputAdress(address);
        inputCity(city);
        selectCountry(country);
        selectRegion(region);
        clickContinueSubmitButton();

    }

}