package opencart.pages.account;

import org.openqa.selenium.WebDriver;
import opencart.pages.AbstractPageWithHeader;

public class MyAccountPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){

    }
}
