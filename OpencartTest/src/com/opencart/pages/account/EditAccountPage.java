package com.opencart.pages.account;

import org.openqa.selenium.WebDriver;
import com.opencart.pages.AbstractPageWithHeader;

public class EditAccountPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        accountSidebarComponent = new AccountSidebarComponent(driver);
    }

}
