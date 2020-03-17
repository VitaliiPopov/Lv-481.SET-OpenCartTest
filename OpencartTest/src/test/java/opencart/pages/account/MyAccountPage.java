package opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccountPage extends AbstractPageWithHeader {

    //Components
    private AccountSidebarComponent accountSidebarComponent;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        accountSidebarComponent = new AccountSidebarComponent(driver);
    }


}
