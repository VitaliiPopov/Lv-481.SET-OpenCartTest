package com.opencart.pages.account;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AccountLogoutPage extends AbstractPageWithHeader {

    private AccountSidebarComponent accountSidebarComponent;

    @FindBy(how = How.CSS, css = "#content>h1")
    private WebElement titleLogoutBlock;

    @FindBy(how = How.CSS, css = "div.pull-right>a")
    private WebElement logoutButton;

    public AccountLogoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //titleLogoutBlock
    public String getTitleLogoutBlockText() {
        return titleLogoutBlock.getText();
    }

    //logoutButton
    public void clickLogoutButton() {
        logoutButton.click();
    }

    //BUSINESS LOGIC

    //logout
    public HomePage logout(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        clickLogoutButton();
        return new HomePage(driver);
    }

}