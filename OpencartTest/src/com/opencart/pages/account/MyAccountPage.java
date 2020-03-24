package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends AbstractPageWithHeader {

    //Components
    private AccountSidebarComponent accountSidebarComponent;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        accountSidebarComponent = new AccountSidebarComponent(driver);
    }
}


