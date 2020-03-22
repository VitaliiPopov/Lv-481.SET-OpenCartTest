package com.opencart.pages.admin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AdminCustomerPage extends AdminHomePage {

    public AdminCustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
