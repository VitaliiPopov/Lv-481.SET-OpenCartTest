package test;

import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
import com.opencart.pages.admin.AdminReviewEditPage;
import com.opencart.pages.admin.AdminReviewPage;
import com.opencart.pages.product.ProductPage;
import com.opencart.tools.Driver;
import com.opencart.tools.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReviewTest extends TestRunner {

    private WebDriver driver;
    private ProductPage productPage;
    private AdminReviewPage adminReviewPage;
    private String NAME_OF_AUTHOR="Georgiy";
    private String INCORRECT_NAME="Ri";
    private String CORRECT_TEXT="it is a product of amazing quality, everything works and the price is quite affordable.";
    private String INCORRECT_TEXT="product is amazing.";
    private String ADMIN_LOGIN="root";
    private String ADMIN_PASSWORD="root";
    private String messageOfDeliveredReview="Thank you for your review. It has been submitted to the webmaster for approval.";
    private String messageOfUndeliveredReviewBecauseOfIncorrectName="Warning: Review Name must be between 3 and 25 characters!";
    private String messageOfUndeliveredReviewBecauseOfIncorrectText="Warning: Review Text must be between 25 and 1000 characters!";

    private void startMethod(){
        AdminLoginPage adminLoginPage= new AdminLoginPage(Driver.getAdminDriver());
        AdminHomePage adminHomePage=adminLoginPage.adminLogin(ADMIN_LOGIN,ADMIN_PASSWORD);
        adminReviewPage=adminHomePage.openReviewPage();
    }

    @BeforeMethod
    public void setUp(){
        driver=Driver.getDriver();
        productPage=new ProductPage(driver);
    }

    /*@AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
        }
        startMethod();
        adminReviewPage.deleteReview(NAME_OF_AUTHOR);
    }*/

    @Test(priority = 1)
    public void successfulReviewProcess() {
        WebElement product=driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        int startCount=productPage.getReviewCounter();
        productPage.writeReview(NAME_OF_AUTHOR,CORRECT_TEXT);
        String productUrl=driver.getCurrentUrl();
        startMethod();
        AdminReviewEditPage adminReviewEditPage=adminReviewPage.openReviewEditPage(NAME_OF_AUTHOR);
        adminReviewEditPage.submitReview();
        driver.navigate().to(productUrl);
        int currentCount=productPage.getReviewCounter();
        Assert.assertEquals(currentCount,startCount+1);
    }
    @Test(priority = 2)
    public void successfullyWritingReview(){
        WebElement product=driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(NAME_OF_AUTHOR,CORRECT_TEXT);
        String textOfDeliveredReview=productPage.getTextOfDeliveredReviewMessage();
        Assert.assertEquals(textOfDeliveredReview,this.messageOfDeliveredReview);
    }
    @Test(priority = 3)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectName(){
        WebElement product=driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(INCORRECT_NAME,CORRECT_TEXT);
        String textOfUndeliveredReview=productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview,this.messageOfUndeliveredReviewBecauseOfIncorrectName);
    }
    @Test(priority = 4)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectText(){
        WebElement product=driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(NAME_OF_AUTHOR,INCORRECT_TEXT);
        String textOfUndeliveredReview=productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview,this.messageOfUndeliveredReviewBecauseOfIncorrectText);
    }
    @Test(priority = 5)
    public void unsuccessfullyWritingReviewBecauseOfIncorrectTextAndName(){
        WebElement product=driver.findElement(By.cssSelector("img[alt='MacBook']"));
        product.click();
        productPage.writeReview(INCORRECT_NAME,INCORRECT_TEXT);
        String textOfUndeliveredReview=productPage.getTextOfUndeliveredReviewMessage();
        Assert.assertEquals(textOfUndeliveredReview,this.messageOfUndeliveredReviewBecauseOfIncorrectText);
    }
}

