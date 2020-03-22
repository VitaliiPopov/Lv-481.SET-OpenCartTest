package com.opencart.pages.account;


import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditAdressPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    @FindBy(how = How.XPATH, xpath = "//input[@id='input-firstname']")
    private WebElement firstNameInput;
    @FindBy(how = How.XPATH, xpath = "//input[@id='input-lastname']")
    private WebElement lastNameInput;
    @FindBy(how = How.XPATH, xpath = "//*[@id='input-address-1']")
    private WebElement addressInput;
    @FindBy(how = How.XPATH, xpath = "//input[@id='input-city']")
    private WebElement cityInput;
    /* @FindBy(how = How.XPATH,xpath = "//input[@id='input-postcode']")
     private WebElement postCodeInput;*/
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

    private void initElements() {
        accountSidebarComponent = new AccountSidebarComponent(driver);
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

/*    //Postcode input
    public void clickPostCodeInputField() {
        postCodeInput.click();
    }
    public void clearPostCodeInputField() {
        postCodeInput.clear();
    }
    public void setPostCodeInputField(String login) {
        postCodeInput.sendKeys(login);
    }*/

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
    // DefaultAddresRadioB and not default
    public void clicknotDefaultAddresRadioB() {
        notDefaultAddresRadioB.click();
    }

    public void clickDefaultAddresRadioB() {
        defaultAddresRadioB.click();
    }

    //continueButton
    public void clickContinueSubmitButton() {
        continueSubmitButton.click();
    }
    //FUNCTIONAL


    //firstnameInputField
    public void fillInputFirstname(String firstname) {
        clickFirstnameInputField();
        clearFirstnameInputField();
        setFirstnameInputField(firstname);
    }

    //firstnameInputField
    public void fillInputLastname(String lastname) {
        clickLastnameInputField();
        clearLastnameInputField();
        setLastnameInputField(lastname);
    }

    //addressinputField
    public void fillAdressfield(String addres) {
        clickAddresInputField();
        clearAddresInputField();
        setAddresInputField(addres);
    }

    //firstnameInputField
    public void fillInputCity(String city) {
        clickCityInputField();
        clearCityInputField();
        setCityInputField(city);
    }
   /* //firstnameInputField
    public void fillInputPostcode(String postcode){
        clickCityInputField();
        clearCityInputField();
        setCityInputField(postcode);
    }*/

//alerts
    public boolean succesFirstNameAlert() {
        if (firstNameAlert.getText().equalsIgnoreCase("First Name must be between 1 and 32 characters!")) {
            return firstNameAlert.isDisplayed();
        } else return false;
    }

    public boolean succesLastNameAlert() {
        if (lastNameAlert.getText().equalsIgnoreCase("Last Name must be between 1 and 32 characters!")) {
            return lastNameAlert.isDisplayed();
        } else return false;
    }

    public boolean succesAddressAlert() {
        if (addressAlert.getText().equalsIgnoreCase("Address must be between 3 and 128 characters!")) {
            return addressAlert.isDisplayed();
        } else return false;
    }

    public boolean succesCityAlert() {
        if (cityAlert.getText().equalsIgnoreCase("City must be between 2 and 128 characters!")) {
            return firstNameAlert.isDisplayed();
        } else return false;
    }
    public boolean succesCountryAlert() {
        if (countryAlert.getText().equalsIgnoreCase("Please select a country!")) {
            return countryAlert.isDisplayed();
        } else return false;
    }
    public boolean succesRegionAlert() {
        if (regionAlert.getText().equalsIgnoreCase("Please select a region / state!")) {
            return regionAlert.isDisplayed();
        } else return false;
    }
    public boolean alerts() {
        if (succesAddressAlert() && succesFirstNameAlert() && succesLastNameAlert() && succesCityAlert()) {
            return true;
        } else return false;
    }
//Busines logic
    public AddressBookPage register(String FIRST_NAME, String LAST_NAME, String ADDRESS, String CITY, String COUNTRY, String REGION) {
        fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        fillAdressfield(ADDRESS);
        fillInputCity(CITY);
        selectCountry(COUNTRY);
        selectRegion(REGION);
        clickContinueSubmitButton();
        return new AddressBookPage(driver);
    }

    public AddressBookPage edituser(String FIRST_NAME, String LAST_NAME, String ADDRESS, String CITY, String COUNTRY, String REGION) {
        fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        fillAdressfield(ADDRESS);
        fillInputCity(CITY);
        selectCountry(COUNTRY);
        selectRegion(REGION);
        clickContinueSubmitButton();
        return new AddressBookPage(driver);
    }

    public void EmptyFirstName(String LAST_NAME, String ADDRESS, String CITY, String COUNTRY, String REGION) {
        //    fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        fillAdressfield(ADDRESS);
        fillInputCity(CITY);
        selectCountry(COUNTRY);
        selectRegion(REGION);
        clickContinueSubmitButton();
        // return new EditAdressPage(driver);
    }

    public void EmptyLastName(String FIRST_NAME, String ADDRESS, String CITY, String COUNTRY, String REGION) {
        fillInputFirstname(FIRST_NAME);
        //   fillInputLastname(LAST_NAME);
        fillAdressfield(ADDRESS);
        fillInputCity(CITY);
        selectCountry(COUNTRY);
        selectRegion(REGION);
        clickContinueSubmitButton();
        // return new EditAdressPage(driver);
    }

    public void EmptyAddress(String FIRST_NAME, String LAST_NAME, String CITY, String COUNTRY, String REGION) {
        fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        // fillAdressfield(ADDRESS);
        fillInputCity(CITY);
        selectCountry(COUNTRY);
        selectRegion(REGION);
        clickContinueSubmitButton();
        // return new EditAdressPage(driver);
    }
    public void EmptyCity(String FIRST_NAME, String LAST_NAME, String ADDRESS, String COUNTRY, String REGION) {
        fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        fillAdressfield(ADDRESS);
        //fillInputCity(CITY);
        selectCountry(COUNTRY);
        selectRegion(REGION);
        clickContinueSubmitButton();
        // return new EditAdressPage(driver);
    }
    public void EmptyCountry(String FIRST_NAME, String LAST_NAME, String ADDRESS, String CITY,  String REGION) {
        fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        fillAdressfield(ADDRESS);
        fillInputCity(CITY);
        selectCountryDefault();
        //selectRegion(REGION);
        clickContinueSubmitButton();
        // return new EditAdressPage(driver);
    }
    public void EmptyCountry(String FIRST_NAME, String LAST_NAME, String ADDRESS, String CITY, String COUNTRY, String REGION) {
        fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        fillAdressfield(ADDRESS);
        fillInputCity(CITY);
        selectCountry(COUNTRY);
        selectRegionDefault();
        clickContinueSubmitButton();
        // return new EditAdressPage(driver);
    }
    public void tooShortInputsAlertCheck(String FIRST_NAME, String LAST_NAME, String ADDRESS, String CITY, String COUNTRY, String REGION) {
        fillInputFirstname(FIRST_NAME);
        fillInputLastname(LAST_NAME);
        fillAdressfield(ADDRESS);
        fillInputCity(CITY);
        selectCountry(COUNTRY);
        selectRegion(REGION);
        clickContinueSubmitButton();

    }
    public AddressBookPage goToAdressBookPage() {
        return new AddressBookPage(driver);
    }
}
