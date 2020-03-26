package test;

import com.opencart.pages.account.AddressBookPage;
import com.opencart.pages.account.EditAdressPage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.tools.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.apache.commons.lang3.RandomStringUtils.random;

public class AddressTests extends TestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }
    }

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
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
        Assert.assertTrue(tryfill.succesAddingAlert());
        tryfill.deleteLastElement();

    }

    @Test(priority = 2)
    public void abilityToDeleteAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage = addressBookPage.addAdressIfEmpty();
        //addressBookPage.addAddressIfthereisnoaddress();
        addressBookPage.deleteLastElement();
        Assert.assertTrue(addressBookPage.succesDeletingAlert());

    }

    @Test(priority = 3)
    public void editAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddresss();
        AddressBookPage adres = edittest.edituser(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(adres.successEditingAlert());
        Assert.assertTrue(adres.checkAddresTextCorect(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3)));

    }

    @Test(priority = 4)
    public void checkEditPageAlertMassages() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddress();
        edittest.clickContinueSubmitButton();
        Assert.assertTrue(edittest.allMandatoryAlerts());
    }

    @Test(priority = 5)
    public void checkAbilityToDeleteDefaultAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage.deleteDefaultAddress();
        Assert.assertTrue(addressBookPage.defaultDeleteAlert());

    }

    @Test(priority = 6)
    public void createAddressWithEmptyFirstname() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyFirstName(jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.succesFirstNameAlert());

    }

    @Test(priority = 7)
    public void createAddressWithEmptyLastname() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyLastName(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.succesLastNameAlert());

    }

    @Test(priority = 8)
    public void createAddressWithEmptyAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyAddress(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.succesAddressAlert());

    }

    @Test(priority = 9)
    public void createAddressWithEmptyCity() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyCity(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCountryFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.succesCityAlert());
        Thread.sleep(5000);
    }

    @Test(priority = 10)
    public void createAddressWithEmptyCountry() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyCountry(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getRegionFromJson(3));
        Assert.assertTrue(edittest.succesCountryAlert());
        Thread.sleep(5000);
    }

    @Test(priority = 11)
    public void createAddressWithEmptyRegion() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.emptyRegion(jsonDataConfig.getFirstNameFromJson(3),
                jsonDataConfig.getLastNameFromJson(3),
                jsonDataConfig.getAddressFromJson(3),
                jsonDataConfig.getCityFromJson(3),
                jsonDataConfig.getCountryFromJson(3));
        Assert.assertTrue(edittest.succesRegionAlert());
        Thread.sleep(5000);
    }

    @Test(priority = 12)
    public void checkAlertsWithTooShortInputs() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
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

    @Test(priority = 13)
    public void checkAlertsWithTooLongInputs() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddress();
        edittest.invalidInputsAlertCheck(
                random(33),
                random(33),
                random(129),
                random(129),
                jsonDataConfig.getCountryFromJson(5),
                jsonDataConfig.getRegionFromJson(5));
        Assert.assertTrue(edittest.allMandatoryAlerts());
        Thread.sleep(5000);
    }

    @Test(priority = 14)
    public void createAddressWithNumbersInsteadCharacters() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddress();
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
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage.deleteAll();
        Thread.sleep(5000);
    }
}
