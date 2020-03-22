package test;

import com.opencart.pages.account.AddressBookPage;
import com.opencart.pages.account.EditAdressPage;
import com.opencart.pages.account.LoginPage;
import com.opencart.pages.account.MyAccountPage;
import com.opencart.tools.ExcelDataConfig;
import com.opencart.tools.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends TestRunner {
    ExcelDataConfig excelDataConfig = new ExcelDataConfig("TestData.xlsx");

    @Parameters({"myAccountDropdownText"})
    @Test(priority = 1)
    public void abilityToAddAddress(String myAccountDropdownText) throws InterruptedException {
        /*LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        EmptyAddressBookPage test =myAccountPage.clickModifyyouraddresswhuileempty();
        EditAdressPage page =test.clickEditAddress();*/
        LoginPage loginPage = getHomePage().goToLoginPage(myAccountDropdownText);
        MyAccountPage myAccountPage = loginPage.login(excelDataConfig.getData(0, 0, 0), excelDataConfig.getData(0, 0, 1));
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        AddressBookPage tryfill = edittest.register("Yura", "Sereda", "Shopena Str,", "Ternopil", "Ukraine", "Ternopil's'ka Oblast'");
        Assert.assertTrue(tryfill.succesAddingAlert());
       // Assert.assertTrue(tryfill.checkAddresTextCorect("Yura", "Sereda", "Shopena Str,", "Ternopil", "Ukraine", "Ternopil's'ka Oblast'"), "Incorect Address was added");
        tryfill.deleteLastElement();


    }

    @Test(priority = 2)
    public void abilityToDeleteAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        Thread.sleep(4000);
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        addressBookPage = addressBookPage.addAddressIfthereisnoaddress();
        //addressBookPage.addAddressIfthereisnoaddress();
        addressBookPage.deleteLastElement();
        Thread.sleep(4000);
        Assert.assertTrue(addressBookPage.succesDeletingAlert());

    }

    @Test(priority = 3)
    public void editAddress() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickEditAddresss();
        AddressBookPage adres=  edittest.edituser("Yura", "Sereda", "Shopena Str,", "Ternopil", "Ukraine", "Ternopil's'ka Oblast'");
        Assert.assertTrue(adres.successEditingAlert());
        Assert.assertTrue(adres.checkAddresTextCorect("Yura", "Sereda", "Shopena Str,", "Ternopil", "Ukraine", "Ternopil's'ka Oblast'"));

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
        Thread.sleep(5000);
    }
    @Test(priority = 6)
    public void createUserWithEmptyFirstname() throws InterruptedException {
        MyAccountPage myAccountPage = getHomePage().clickMyAccauntInDropdownHardcode();
        AddressBookPage addressBookPage = myAccountPage.clickModifyYourAddressBookEntries();
        EditAdressPage edittest = addressBookPage.clickNewAddress();
        edittest.EmptyFirstName("Sereda","Shopena Str,","Ternopil","Ukraine","Ternopil's'ka Oblast'");
        Assert.assertTrue(edittest.succesFirstNameAlert());
        Thread.sleep(5000);
    }
}
