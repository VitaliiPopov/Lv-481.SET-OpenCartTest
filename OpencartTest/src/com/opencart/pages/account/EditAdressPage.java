package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;

public class EditAdressPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    public EditAdressPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        accountSidebarComponent = new AccountSidebarComponent(driver);
    }

}
