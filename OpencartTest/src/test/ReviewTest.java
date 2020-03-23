package test;

import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.pages.admin.AdminReviewEditPage;
import com.opencart.pages.admin.AdminReviewPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.Driver;
import com.opencart.tools.TestRunner;
import com.opencart.tools.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReviewTest extends TestRunner {

    private WebDriver driver;
    private ProductPage productPage;
    private AdminReviewPage adminReviewPage;
    private String NAME_OF_AUTHOR = "Georgiy";
    private String CORRECT_TEXT = "it is a product of amazing quality, everything works and the price is quite affordable.";
    private String ADMIN_LOGIN = "root";
    private String ADMIN_PASSWORD = "root";
    private String messageOfDeliveredReview = "Thank you for your review. It has been submitted to the webmaster for approval.";

    private void startMethod() {
        AdminLoginPage adminLoginPage = new AdminLoginPage(Driver.getAdminDriver());
        AdminHomePage adminHomePage = adminLoginPage.adminLogin(ADMIN_LOGIN, ADMIN_PASSWORD);
        adminReviewPage = adminHomePage.openReviewPage();
    }

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
        startMethod();
        adminReviewPage.deleteReview(NAME_OF_AUTHOR);
    }

    @Test(priority = 1)
    public void successfulReviewProcess() {
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        int startCount = productPage.getReviewCounter();
        productPage.writeReview(NAME_OF_AUTHOR, CORRECT_TEXT);
        String productUrl = driver.getCurrentUrl();
        startMethod();
        AdminReviewEditPage adminReviewEditPage = adminReviewPage.openReviewEditPage(NAME_OF_AUTHOR);
        adminReviewEditPage.submitReview();
        driver.navigate().to(productUrl);
        int currentCount = productPage.getReviewCounter();
        Assert.assertEquals(currentCount, startCount + 1);
    }

    @Test(priority = 2)
    public void successfullyWritingReview() {
        WebElement product = driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(NAME_OF_AUTHOR, CORRECT_TEXT);
        String textOfDeliveredReview = productPage.getTextOfDeliveredReviewMessage();
        Assert.assertEquals(textOfDeliveredReview, this.messageOfDeliveredReview);
    }
}

