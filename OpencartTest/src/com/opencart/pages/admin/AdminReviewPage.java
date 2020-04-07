package com.opencart.pages.admin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminReviewPage extends AdminHomePage {

    private WebElement reviewRow;
    @FindBy(how = How.CSS, css = "button[data-original-title='Delete']")
    private WebElement deleteButton;

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
        if (reviewRow != null) {
            WebElement pick = reviewRow.findElement(By.cssSelector("input[type='checkbox']"));
            pick.sendKeys(Keys.SPACE);
            if (!pick.isSelected()) {
                throw new NoSuchElementException("pick isn't selected");
            }
            deleteButton.click();
            driver.switchTo().alert().accept();
            new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("div[class='alert alert-success alert-dismissible']")));
        }
    }
}