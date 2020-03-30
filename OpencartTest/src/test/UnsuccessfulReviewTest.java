package test;

import com.opencart.data.ConstantVariables;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.Driver;
import com.opencart.tools.Screenshot;
import com.opencart.tools.test_runner.TestRunner;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class UnsuccessfulReviewTest extends TestRunner {

    private WebDriver driver;
    private ProductPage productPage;
    @FindBy(how = How.CSS, css = "img[alt='MacBook']")
    private WebElement testProduct;

    public void scrollToSpecificProductAndClickOnIt(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
    }

    @BeforeClass
    public void setUp() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
        productPage = new ProductPage(driver);
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.navigate().to(ConstantVariables.URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            Screenshot.run(result,driver);
        }
    }

    @Parameters({"incorrectName", "correctText", "messageOfUndeliveredReviewBecauseOfIncorrectName"})
    @Test(priority = 1)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectName(String incorrectName, String correctText, String messageOfUndeliveredReviewBecauseOfIncorrectName) {
        scrollToSpecificProductAndClickOnIt();
        productPage.writeReview(incorrectName, correctText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectName);
    }

    @Parameters({"nameOfAuthor", "incorrectText", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 2)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectText(String nameOfAuthor, String incorrectText, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        scrollToSpecificProductAndClickOnIt();
        productPage.writeReview(nameOfAuthor, incorrectText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Parameters({"incorrectName", "incorrectText", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 3)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectTextAndName(String incorrectName, String incorrectText, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        scrollToSpecificProductAndClickOnIt();
        productPage.writeReview(incorrectName, incorrectText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Parameters({"emptyNameField", "emptyTextField", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 4)
    public void unsuccessfullyWritingReviewBecauseOfEmptyNameAndTextFields(String emptyNameField, String emptyTextField, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        scrollToSpecificProductAndClickOnIt();
        productPage.writeReview(emptyNameField, emptyTextField);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }

    @Parameters({"correctText", "messageOfUndeliveredReviewBecauseOfIncorrectName"})
    @Test(priority = 5)
    public void unsuccessfullyWritingReviewBecauseOfTooLongName(String correctText, String messageOfUndeliveredReviewBecauseOfIncorrectName) {
        scrollToSpecificProductAndClickOnIt();
        String tooLongName = RandomStringUtils.randomAlphabetic(30);
        productPage.writeReview(tooLongName, correctText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectName);
    }

    @Parameters({"nameOfAuthor", "messageOfUndeliveredReviewBecauseOfIncorrectText"})
    @Test(priority = 6)
    public void unsuccessfullyWritingReviewBecauseOfTooLongText(String nameOfAuthor, String messageOfUndeliveredReviewBecauseOfIncorrectText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        String tooLongText = RandomStringUtils.randomAlphabetic(1020)+".";
        productPage.writeReview(nameOfAuthor, tooLongText);
        String textOfUndeliveredReview = productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview, messageOfUndeliveredReviewBecauseOfIncorrectText);
    }
}

