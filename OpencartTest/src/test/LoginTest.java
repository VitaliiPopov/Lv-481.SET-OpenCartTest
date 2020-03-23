package test;

import com.opencart.pages.account.*;
import com.opencart.tools.ExcelDataConfig;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends TestRunner {
    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void abilityToAddAddress(String myAccountDropdownText) throws InterruptedException {
        /*LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        EmptyAddressBookPage test =myAccountPage.clickModifyyouraddresswhuileempty();
        EditAdressPage page =test.clickEditAddress();*/
      /*  LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
       */
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
     //   AddressBookPage tryfill = edittest.register("Yura", "Sereda", "Shopena Str,", "Ternopil", "Ukraine", "Ternopil's'ka Oblast'");
        AddressBookPage tryfill = edittest.register(jsonDataConfig.getFirstNameFromJson(0),jsonDataConfig.getLastNameFromJson(0),jsonDataConfig.getAddressFromJson(0),jsonDataConfig.getCityFromJson(0),jsonDataConfig.getCountryFromJson(0),jsonDataConfig.getRegionFromJson(0));
        Assert.assertTrue(tryfill.succesAddingAlert());
       // Assert.assertTrue(tryfill.checkAddresTextCorect("Yura", "Sereda", "Shopena Str,", "Ternopil", "Ukraine", "Ternopil's'ka Oblast'"), "Incorect Address was added");
        tryfill.deleteLastElement();

    }

    @Test(priority = 2)
    public void abilityToDeleteAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage = addressBookPage.addAddressIfthereisnoaddress();
        //addressBookPage.addAddressIfthereisnoaddress();
        addressBookPage.deleteLastElement();
        Assert.assertTrue(addressBookPage.succesDeletingAlert());

    }

    @Test(priority = 3)
    public void editAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddresss();
        AddressBookPage adres=  edittest.edituser(jsonDataConfig.getFirstNameFromJson(0),jsonDataConfig.getLastNameFromJson(0),jsonDataConfig.getAddressFromJson(0),jsonDataConfig.getCityFromJson(0),jsonDataConfig.getCountryFromJson(0),jsonDataConfig.getRegionFromJson(0));
        Assert.assertTrue(adres.successEditingAlert());
        Assert.assertTrue(adres.checkAddresTextCorect(jsonDataConfig.getFirstNameFromJson(0),jsonDataConfig.getLastNameFromJson(0),jsonDataConfig.getAddressFromJson(0),jsonDataConfig.getCityFromJson(0),jsonDataConfig.getCountryFromJson(0),jsonDataConfig.getRegionFromJson(0)));

    }

    @Test(priority = 4)
    public void checkEditPageAlertMassages() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddress();
        edittest.clickContinueSubmitButton();
        Assert.assertTrue(edittest.alerts());
     //   System.out.println(addressBookPage.checkAddresTextCorect("Yura", "Sereda", "Shopena Str,", "Ternopil", "Ukraine", "Ternopil's'ka Oblast'"));


    }
    @Test(priority = 5)
    public void checkAbilityToDeleteDefaultAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage.clickDeleteDefaultAddress();
        Assert.assertTrue(addressBookPage.defaultDeleteAlert());

    }
    @Test(priority = 6)
    public void createAddressWithEmptyFirstname() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyFirstName(jsonDataConfig.getLastNameFromJson(0),jsonDataConfig.getAddressFromJson(0),jsonDataConfig.getCityFromJson(0),jsonDataConfig.getCountryFromJson(0),jsonDataConfig.getRegionFromJson(0));
        Assert.assertTrue(edittest.succesFirstNameAlert());

    }
    @Test(priority = 7)
    public void createAddressWithEmptyLastname() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyLastName(jsonDataConfig.getFirstNameFromJson(0),jsonDataConfig.getAddressFromJson(0),jsonDataConfig.getCityFromJson(0),jsonDataConfig.getCountryFromJson(0),jsonDataConfig.getRegionFromJson(0));
        Assert.assertTrue(edittest.succesLastNameAlert());

    }
    @Test(priority = 8)
    public void createAddressWithEmptyAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyAddress(jsonDataConfig.getFirstNameFromJson(0),jsonDataConfig.getLastNameFromJson(0),jsonDataConfig.getCityFromJson(0),jsonDataConfig.getCountryFromJson(0),jsonDataConfig.getRegionFromJson(0));
        Assert.assertTrue(edittest.succesAddressAlert());

    }
    @Test(priority = 9)
    public void createAddressWithEmptyCity() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyCity(jsonDataConfig.getFirstNameFromJson(0),jsonDataConfig.getLastNameFromJson(0),jsonDataConfig.getAddressFromJson(0),jsonDataConfig.getCountryFromJson(0),jsonDataConfig.getRegionFromJson(0));
        Assert.assertTrue(edittest.succesCityAlert());
        Thread.sleep(5000);
    }
    @Test(priority = 10)
    public void createAddressWithEmptyCountry() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyCountry(jsonDataConfig.getFirstNameFromJson(0),jsonDataConfig.getLastNameFromJson(0),jsonDataConfig.getAddressFromJson(0),jsonDataConfig.getCityFromJson(0),jsonDataConfig.getRegionFromJson(0));
        Assert.assertTrue(edittest.succesCountryAlert());
        Thread.sleep(5000);
    }
    @Test(priority = 11)
    public void createAddressWithEmptyRegion() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyCountry(jsonDataConfig.getFirstNameFromJson(0),jsonDataConfig.getLastNameFromJson(0),jsonDataConfig.getAddressFromJson(0),jsonDataConfig.getCityFromJson(0),jsonDataConfig.getCountryFromJson(0));
        Assert.assertTrue(edittest.succesRegionAlert());
        Thread.sleep(5000);
    }
    @Test(priority = 12)
    public void checkAlertsWithTooShortInputs() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddress();
        edittest.tooShortInputsAlertCheck(jsonDataConfig.getFirstNameFromJson(1),jsonDataConfig.getLastNameFromJson(1),jsonDataConfig.getAddressFromJson(1),jsonDataConfig.getCityFromJson(1),jsonDataConfig.getCountryFromJson(1),jsonDataConfig.getRegionFromJson(1));
        Assert.assertTrue(edittest.alerts());
        Thread.sleep(5000);
    }
    @Test(priority = 13)
    public void checkJson() throws InterruptedException {
        System.out.println(jsonDataConfig.getFirstNameFromJson(0));
        System.out.println(jsonDataConfig.getLastNameFromJson(0));
        System.out.println(jsonDataConfig.getAddressFromJson(0));
        System.out.println(jsonDataConfig.getCityFromJson(0));
        System.out.println(jsonDataConfig.getCountryFromJson(0));
        System.out.println(jsonDataConfig.getRegionFromJson(0));



    }
}
