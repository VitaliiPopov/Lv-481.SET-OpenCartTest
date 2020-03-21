package com.opencart.pages.admin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AdminReviewPage extends AdminHomePage {

    public AdminReviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
