package test;

import com.opencart.data.ConstantVariables;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class UnsuccessfulReviewTest extends TestRunner {

    private WebDriver driver;
    private ProductPage productPage;
    @FindBy(how = How.CSS, css = "img[alt='MacBook']")
    private WebElement testProduct;

    @BeforeClass
    public void setUp() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
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
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        productPage.writeReview(incorrectName, correctText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectName);
    }

    @Parameters({"nameOfAuthor", "incorrectText", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 2)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectText(String nameOfAuthor, String incorrectText, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        productPage.writeReview(nameOfAuthor, incorrectText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Parameters({"incorrectName", "incorrectText", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 3)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectTextAndName(String incorrectName, String incorrectText, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        productPage.writeReview(incorrectName, incorrectText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Parameters({"emptyNameField", "emptyTextField", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 4)
    public void unsuccessfullyWritingReviewBecauseOfEmptyNameAndTextFields(String emptyNameField, String emptyTextField, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        productPage.writeReview(emptyNameField, emptyTextField);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Parameters({ "correctText", "messageOfUndeliveredReviewBecauseOfIncorrectName"})
    @Test(priority = 5)
    public void unsuccessfullyWritingReviewBecauseOfTooLongName( String correctText, String messageOfUndeliveredReviewBecauseOfIncorrectName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        String tooLongName = Randomizer.generateRandomString(30);
        productPage.writeReview(tooLongName, correctText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectName);
    }

    @Parameters({"nameOfAuthor", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 6)
    public void unsuccessfullyWritingReviewBecauseOfTooLongText(String nameOfAuthor,String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        String tooLongText=Randomizer.generateRandomString(1020);
        productPage.writeReview(nameOfAuthor, tooLongText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

}

