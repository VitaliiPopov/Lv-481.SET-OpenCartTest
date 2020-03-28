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
import com.opencart.tools.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class ReviewTest extends TestRunner {
    private JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    private WebDriver driver;
    private ProductPage productPage;

    private AdminReviewPage startMethod() {
        driver.navigate().to(System.getenv("ADMIN_URL"));
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        AdminHomePage adminHomePage = adminLoginPage.adminLogin(jsonDataConfig.getEmailFromJson(2), jsonDataConfig.getPasswordFromJson(2));
        return adminHomePage.openReviewPage();
    }

    @BeforeClass
    public void setUp() {
        driver = Driver.getDriver();
        productPage = new ProductPage(driver);
    }

    @BeforeMethod
    public void setUp1() {
        driver.navigate().to(System.getenv("URL"));
    }

    @AfterMethod
    @Parameters({"nameOfAuthor"})
    public void tearDown(ITestResult result, String nameOfAuthor) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }
        startMethod().deleteReview(nameOfAuthor);
    }

    @Parameters({"nameOfAuthor", "correctText"})
    @Test(priority = 1)
    public void successfulReviewProcess(String nameOfAuthor, String correctText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        js.executeScript("arguments[0].scrollIntoView();", product);
        product.click();
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
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        js.executeScript("arguments[0].scrollIntoView();", product);
        product.click();
        productPage.writeReview(nameOfAuthor, correctText);
        String textOfDeliveredReview = productPage.getTextOfDeliveredReviewMessage();
        Assert.assertEquals(textOfDeliveredReview, messageOfDeliveredReview);
    }
}

