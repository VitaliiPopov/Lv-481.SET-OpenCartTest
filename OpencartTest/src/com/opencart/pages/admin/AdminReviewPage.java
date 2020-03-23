package com.opencart.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminReviewPage extends AdminHomePage {

    private WebElement reviewRow;

    public AdminReviewPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getReviewRow(String nameOfAuthor) {
        for (int i = 0; i < driver.findElements(By.cssSelector(".table-responsive tbody tr")).size(); i++) {
            WebElement row = driver.findElement(By.cssSelector(".table-responsive tbody tr:nth-child(" + (i + 1) + ")"));
            String text = row.findElement(By.cssSelector("td:nth-child(3)")).getText();

            if (text.equals(nameOfAuthor)) {
                return row;
            }
        }
        return null;
    }

    public AdminReviewEditPage openReviewEditPage(String nameOfAuthor) {
        reviewRow = getReviewRow(nameOfAuthor);
        if (reviewRow != null) {
            WebElement button = reviewRow.findElement(By.cssSelector("a[data-original-title='Edit']"));
            button.click();

            return new AdminReviewEditPage(driver);
        }
        return null;
    }

    public void deleteReview(String nameOfAuthor) {
        reviewRow = getReviewRow(nameOfAuthor);
        WebElement pick = reviewRow.findElement(By.cssSelector("input[type='checkbox']"));
        pick.click();
        WebElement deleteButton = driver.findElement(By.cssSelector("button[data-original-title='Delete']"));
        deleteButton.click();
        driver.switchTo().alert().accept();
    }
}