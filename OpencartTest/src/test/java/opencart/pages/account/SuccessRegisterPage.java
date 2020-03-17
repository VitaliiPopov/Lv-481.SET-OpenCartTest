package opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import opencart.pages.AbstractPageWithHeader;

public class SuccessRegisterPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    public SuccessRegisterPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        accountSidebarComponent = new AccountSidebarComponent(driver);
    }

    //PAGE OBJECT




}
