package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AddressBookTableComponent {

    private WebElement addressBookTableComponentLayout;
    //
    private List<AddressBookItemComponent> addressBookItemComponents;

    public AddressBookTableComponent(WebElement addressBookTableComponentLayout) {
        this.addressBookTableComponentLayout = addressBookTableComponentLayout;
        initElements();
    }

    private void initElements() {
        addressBookItemComponents = new ArrayList<AddressBookItemComponent>();
        for (WebElement current: addressBookTableComponentLayout.findElements(By.xpath(""))) addressBookItemComponents.add(new AddressBookItemComponent(current));
    }


}
