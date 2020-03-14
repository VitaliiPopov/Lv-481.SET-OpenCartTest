package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.AccountSidebarComponent;
import pages.parts.AHeaderPart;

public class MyAccountPage extends AHeaderPart {

    private AccountSidebarComponent accountSidebarComponent;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        accountSidebarComponent = new AccountSidebarComponent(driver.findElement(By.xpath("")));
    }
}
