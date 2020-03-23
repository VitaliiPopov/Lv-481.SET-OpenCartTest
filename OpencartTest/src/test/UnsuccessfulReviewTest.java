package test;

import com.opencart.pages.product.ProductPage;
import com.opencart.tools.Driver;
import com.opencart.tools.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UnsuccessfulReviewTest {

    private WebDriver driver;
    private ProductPage productPage;
    private String NAME_OF_AUTHOR = "Georgiy";
    private String INCORRECT_NAME = "Ri";
    private String EMPTY_NAME_FIELD = "";
    private String CORRECT_TEXT = "it is a product of amazing quality, everything works and the price is quite affordable.";
    private String INCORRECT_TEXT = "product is amazing.";
    private String EMPTY_TEXT_FIELD = "";
    private String messageOfUndeliveredReviewBecauseOfIncorrectText = "Warning: Review Text must be between 25 and 1000 characters!";
    private String messageOfUndeliveredReviewBecauseOfIncorrectName = "Warning: Review Name must be between 3 and 25 characters!";

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        productPage = new ProductPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }
    }

    @Test(priority = 1)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectName() {
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(INCORRECT_NAME, CORRECT_TEXT);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, this.messageOfUndeliveredReviewBecauseOfIncorrectName);
    }

    @Test(priority = 2)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectText() {
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(NAME_OF_AUTHOR, INCORRECT_TEXT);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, this.messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Test(priority = 3)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectTextAndName() {
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(INCORRECT_NAME, INCORRECT_TEXT);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, this.messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Test(priority = 4)
    public void unsuccessfullyWritingReviewBecauseOfEmptyNameAndTextFields() {
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(EMPTY_NAME_FIELD, EMPTY_TEXT_FIELD);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, this.messageOfUndeliveredReviewBecauseOfIncorrectText);
    }
}

