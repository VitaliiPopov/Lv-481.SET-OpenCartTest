package com.opencart.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminReviewEditPage {

    private WebDriver driver;
    private Select selectStatus;
    @FindBy(how = How.CSS, css = "select#input-status")
    private WebElement status;

    @FindBy(how = How.CSS, css = "button[data-original-title='Save']")
    private WebElement buttonSave;

    public Select selectStatus() {
        this.selectStatus = new Select(status);
        return selectStatus;
    }

    public AdminReviewEditPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //initElements();
    }

    public void submitReview() {
        selectStatus = new Select(driver.findElement(By.cssSelector("select#input-status")));
        selectStatus.selectByVisibleText("Enabled");
        buttonSave.click();
    }
}
