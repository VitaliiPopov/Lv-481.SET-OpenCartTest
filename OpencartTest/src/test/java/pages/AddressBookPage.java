package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.AddressBookTableComponent;
import pages.parts.AAdressBookPage;

public class AddressBookPage extends AAdressBookPage {

    //
    private AddressBookTableComponent addressBookTableComponent;

    public AddressBookPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        addressBookTableComponent = new AddressBookTableComponent(driver.findElement(By.xpath("")));
    }
}
