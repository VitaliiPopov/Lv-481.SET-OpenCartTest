package test;

import com.opencart.data.ConstantVariables;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.pages.admin.AdminReviewEditPage;
import com.opencart.pages.admin.AdminReviewPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.Driver;
import com.opencart.tools.JsonDataConfig;
import com.opencart.tools.TestRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ReviewTest extends TestRunner {
    private JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    private WebDriver driver;
    private ProductPage productPage;
    @FindBy(how = How.CSS, css = "img[alt='MacBook']")
    private WebElement testProduct;

    private AdminReviewPage startMethod() {
        driver.navigate().to(ConstantVariables.ADMIN_URL);
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        AdminHomePage adminHomePage = adminLoginPage.adminLogin(jsonDataConfig.getEmailFromJson(2), jsonDataConfig.getPasswordFromJson(2));
        return adminHomePage.openReviewPage();
    }

    @BeforeClass
    public void setUp() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
        productPage = new ProductPage(driver);
    }

    @BeforeMethod
    public void setUp1() {
        driver.navigate().to(ConstantVariables.URL);
    }

    @AfterMethod
    @Parameters({"nameOfAuthor"})
    public void tearDown(ITestResult result, String nameOfAuthor) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] takeScreenShot = takeScreenShot(result.getMethod().getMethodName());
            Allure.addAttachment(result.getMethod().getMethodName(), new ByteArrayInputStream(takeScreenShot));
        }
        startMethod().deleteReview(nameOfAuthor);
    }

    private byte[] takeScreenShot(String methodName) throws IOException {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Parameters({"nameOfAuthor", "correctText"})
    @Test(priority = 1)
    public void successfulReviewProcess(String nameOfAuthor, String correctText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        int startCount = productPage.getReviewCounter();
        productPage.writeReview(nameOfAuthor, correctText);
        String productUrl = driver.getCurrentUrl();
        AdminReviewEditPage adminReviewEditPage = startMethod().openReviewEditPage(nameOfAuthor);
        adminReviewEditPage.submitReview();
        driver.navigate().to(productUrl);
        int currentCount = productPage.getReviewCounter();
        Assert.assertEquals(currentCount, startCount + 1);
    }

    @Parameters({"nameOfAuthor", "correctText", "messageOfDeliveredReview"})
    @Test(priority = 2)
    public void successfullyWritingReview(String nameOfAuthor, String correctText, String messageOfDeliveredReview) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", testProduct);
        testProduct.click();
        productPage.writeReview(nameOfAuthor, correctText);
        String textOfDeliveredReview = productPage.getTextOfDeliveredReviewMessage();
        Assert.assertEquals(textOfDeliveredReview, messageOfDeliveredReview);
    }
}

