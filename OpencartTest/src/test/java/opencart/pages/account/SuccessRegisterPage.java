package opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import opencart.pages.AbstractPageWithHeader;

public class SuccessRegisterPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;
    //
    private WebElement successLoginPageTitle;

    public SuccessRegisterPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
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
