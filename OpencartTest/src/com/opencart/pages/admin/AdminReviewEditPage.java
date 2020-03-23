package com.opencart.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdminReviewEditPage {

    private WebDriver driver;
    private Select selectStatus;
    private WebElement buttonSave;

    public AdminReviewEditPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        selectStatus = new Select(driver.findElement(By.cssSelector("select#input-status")));
        buttonSave = driver.findElement(By.cssSelector("button[data-original-title='Save']"));
    }

    public void submitReview() {
        selectStatus.selectByVisibleText("Enabled");
        buttonSave.click();
    }
}
