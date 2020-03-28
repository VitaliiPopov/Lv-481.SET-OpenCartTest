package test;

import com.opencart.data.ConstantVariables;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.CompareTestRunner;
import com.opencart.tools.Driver;
import com.opencart.tools.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class UnsuccessfulReviewCompareTest extends CompareTestRunner {

    private WebDriver driver;
    private ProductPage productPage;

    @BeforeClass
    public void setUp() {
        driver = Driver.getDriver();
        productPage = new ProductPage(driver);
    }

    @BeforeMethod
    public void setUp1() {
        driver.navigate().to(ConstantVariables.URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }
    }

    @Parameters({"incorrectName", "correctText", "messageOfUndeliveredReviewBecauseOfIncorrectName"})
    @Test(priority = 1)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectName(String incorrectName, String correctText, String messageOfUndeliveredReviewBecauseOfIncorrectName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        js.executeScript("arguments[0].scrollIntoView();", product);
        product.click();
        productPage.writeReview(incorrectName, correctText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectName);
    }

    @Parameters({"nameOfAuthor", "incorrectText", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 2)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectText(String nameOfAuthor, String incorrectText, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        js.executeScript("arguments[0].scrollIntoView();", product);
        product.click();
        productPage.writeReview(nameOfAuthor, incorrectText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Parameters({"incorrectName", "incorrectText", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 3)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectTextAndName(String incorrectName, String incorrectText, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        js.executeScript("arguments[0].scrollIntoView();", product);
        product.click();
        productPage.writeReview(incorrectName, incorrectText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Parameters({"emptyNameField", "emptyTextField", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 4)
    public void unsuccessfullyWritingReviewBecauseOfEmptyNameAndTextFields(String emptyNameField, String emptyTextField, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        js.executeScript("arguments[0].scrollIntoView();", product);
        product.click();
        productPage.writeReview(emptyNameField, emptyTextField);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }
}