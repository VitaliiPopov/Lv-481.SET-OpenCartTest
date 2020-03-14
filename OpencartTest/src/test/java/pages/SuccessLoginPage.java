package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.components.AccountSidebarComponent;
import pages.parts.AHeaderPart;

public class SuccessLoginPage extends AHeaderPart {

    private AccountSidebarComponent accountSidebarComponent;
    //
    private WebElement successLoginPageTitle;

    public SuccessLoginPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        //accountSidebarComponent = new AccountSidebarComponent(driver.findElement(By.xpath("")));
        successLoginPageTitle = driver.findElement(By.xpath("//div[@id='content']/*[1] "));
    }

    //PAGE OBJECT

    //myAccountTitle
    public WebElement getSuccessLoginPageTitle() {
        return successLoginPageTitle;
    }

    public String getSuccessLoginPageTitleText() {
        return getSuccessLoginPageTitle().getText();
    }


}
