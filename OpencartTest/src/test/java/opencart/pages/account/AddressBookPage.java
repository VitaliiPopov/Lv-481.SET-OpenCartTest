package opencart.pages.account;

import opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class AddressBookPage extends AbstractPageWithHeader {

    //Components
    private AccountSidebarComponent accountSidebarComponent;
    private List<AddressBookContainersComponent> addressBookContainersComponents;

    public AddressBookPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        accountSidebarComponent = new AccountSidebarComponent(driver);
        addressBookContainersComponents = new ArrayList<>();
    }
}
