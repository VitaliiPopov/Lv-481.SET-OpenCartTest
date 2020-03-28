package test;

import com.opencart.pages.account.AddressBookPage;
import com.opencart.pages.account.EditAdressPage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.Randomizer;
import com.opencart.tools.TestRunnerYura;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddressTests extends TestRunnerYura {

    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1, description = "Check the ability to add Address")
    public void abilityToAddAddress(String myAccountDropdownText) throws InterruptedException {
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(jsonDataConfig.getEmailFromJson(3), jsonDataConfig.getPasswordFromJson(3));
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        AddressBookPage tryfill = edittest.register(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(tryfill.successAddingAlert());
        tryfill.deleteLastElement();

    }

    @Test(priority = 2,description = "Check the ability to delete address")
    public void abilityToDeleteAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage = addressBookPage.addAddressIfEmpty();
        addressBookPage.deleteLastElement();
        Assert.assertTrue(addressBookPage.successDeletingAlert());

    }

    @Test(priority = 3,description = "Check the ability to edit address")
    public void editAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddresss();
        AddressBookPage adres = edittest.editUser(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(adres.successEditingAlert());
        Assert.assertTrue(adres.checkAddressTextCorrect(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3)));

    }

    @Test(priority = 4, description = "Check All edit Page Alerts")
    public void checkEditPageAlertMassages() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.clickContinueSubmitButton();
        Assert.assertTrue(edittest.allMandatoryAlerts());
    }

    @Test(priority = 5,description = "Check the ability to delete default address")
    public void checkAbilityToDeleteDefaultAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage.deleteDefaultAddress();
        Assert.assertTrue(addressBookPage.defaultDeleteAlert());

    }

    @Test(priority = 6, description = "Check the ability to create address with empty First Name mandatory field")
    public void createAddressWithEmptyFirstname() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.emptyFirstName(jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.firstNameAlert());

    }

    @Test(priority = 7,description = "Check the ability to create address with empty Last Name mandatory field")
    public void createAddressWithEmptyLastname() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.emptyLastName(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.lastNameAlert());

    }

    @Test(priority = 8, description = "Check the ability to create address with empty Address mandatory field")
    public void createAddressWithEmptyAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.emptyAddress(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.addressAlert());

    }

    @Test(priority = 9,description = "Check the ability to create address with empty City mandatory field")
    public void createAddressWithEmptyCity() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.emptyCity(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.cityAlert());
        Thread.sleep(5000);
    }

    @Test(priority = 10, description = "Check the ability to create address with empty Country Drop down box")
    public void createAddressWithEmptyCountry() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.emptyCountry(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3));
        Assert.assertTrue(edittest.countryAlert());
        Thread.sleep(5000);
    }

    @Test(priority = 11,description = "Check the ability to create address with empty Region Drop down box")
    public void createAddressWithEmptyRegion() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.emptyRegion(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3));
        Assert.assertTrue(edittest.regionAlert());
        Thread.sleep(5000);
    }

    @Test(priority = 12,description = "Check the alerts while creating address with too short inputs")
    public void checkAlertsWithTooShortInputs() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddress();
        edittest.invalidInputsAlertCheck(jsonDataConfig.getFirstNameFromJson(4),
                jsonDataConfig.getLastNameFromJson(4),
                jsonDataConfig.getAddressFromJson(4),
                jsonDataConfig.getCityFromJson(4),
                jsonDataConfig.getCountryFromJson(4),
                jsonDataConfig.getRegionFromJson(4));
        Assert.assertTrue(edittest.allMandatoryAlerts());
        Thread.sleep(5000);
    }

    @Test(priority = 13,description = "Check the alerts while creating address with too short inputs")
    public void checkAlertsWithTooLongInputs() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddress();
        edittest.invalidInputsAlertCheck(
                Randomizer.generateRandomString(33),
                Randomizer.generateRandomString(33),
                Randomizer.generateRandomString(129),
                Randomizer.generateRandomString(129),
                jsonDataConfig.getCountryFromJson(5),
                jsonDataConfig.getRegionFromJson(5));
        Assert.assertTrue(edittest.allMandatoryAlerts());
        Thread.sleep(5000);
    }

    @Test(priority = 14,description = "Check the ability to create address with numbers in First and Last name fields")
    public void createAddressWithNumbersInsteadCharacters() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.invalidInputsAlertCheck(jsonDataConfig.getFirstNameFromJson(6),
                jsonDataConfig.getLastNameFromJson(6),
                jsonDataConfig.getAddressFromJson(6),
                jsonDataConfig.getCityFromJson(6),
                jsonDataConfig.getCountryFromJson(6),
                jsonDataConfig.getRegionFromJson(6));
        Assert.assertTrue(edittest.firstLastNameAlerts(), "Created User With numeric First and Last Names");
        Thread.sleep(5000);
    }

    @Test(priority = 15)
    public void clearAddressBook() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccountInDropdown();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage.deleteAll();
        Thread.sleep(5000);
    }

}
