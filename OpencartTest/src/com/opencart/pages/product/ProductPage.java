package com.opencart.pages.product;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.AlertComponent;
import com.opencart.pages.cart.CartPage;
import com.opencart.pages.comparison.ComparisonPage;
import com.opencart.pages.wishlist.WishListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class ProductPage extends AbstractPageWithHeader {

    @FindBy(how = How.XPATH, xpath = ".//button/i[contains(@class,'fa-heart')]/..")
    private WebElement addToWishListButton;

    @FindBy(how = How.CSS, css = "#content h1")
    private WebElement productName;

    @FindBy(how = How.XPATH, xpath = "//div[@class='btn-group']//i[@class='fa fa-exchange']") //TODO methods for click
    private WebElement compareButton;

    @FindBy(how = How.CSS, css = "a[href='#tab-review']")
    private WebElement tabReviews;

    @FindBy(how = How.XPATH, xpath = "//input[contains(@id,'input-name')]")
    private WebElement inputFieldName;

    @FindBy(how = How.XPATH, xpath = "//textarea[contains(@id,'input-review')]")
    private WebElement inputFieldReview;

    @FindBy(how = How.XPATH, xpath = "//input[@type='radio'][contains(@value,'1')]")
    private WebElement radioButtonUnderLow;

    @FindBy(how = How.XPATH, xpath = "//input[@type='radio'][contains(@value,'2')]")
    private WebElement radioButtonLow;

    @FindBy(how = How.XPATH, xpath = "//input[@type='radio'][contains(@value,'3')]")
    private WebElement radioButtonOk;

    @FindBy(how = How.XPATH, xpath = "//input[@type='radio'][contains(@value,'4')]")
    private WebElement radioButtonHigh;

    @FindBy(how = How.XPATH, xpath = "//input[@type='radio'][contains(@value,'5')]")
    private WebElement radioButtonWell;

    @FindBy(how = How.XPATH, xpath = "//button[contains(@id,'button-review')]")
    private WebElement buttonAddReview;

    @FindBy(how = How.XPATH, xpath = "//div[contains(@id,'review')]/child::p")
    private WebElement informationOfReviews;

    @FindBy(how = How.XPATH, xpath = "//div[contains(@id,'review')]/following-sibling::h2")
    private WebElement descriptionOfTabReviews;

    @FindBy(how = How.CSS, css = "div.alert.alert-success")
    private WebElement deliveredReviewMessage;

    @FindBy(how = How.CSS, css = "div.alert.alert-danger")
    private WebElement undeliveredReviewMessage;

    //Components
    private AvailableOptionsComponent availableOptionsComponent;
    private AlertComponent alertComponent;

    //initialization of product page
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    ///region ATOMIC_OPERATIONS

    //AvailableOptionsComponent
    public AvailableOptionsComponent getAvailableOptionsComponent() {
        return new AvailableOptionsComponent(driver.findElement(By.cssSelector("div#product")));
    }

    //productName
    public String getProductName() {
        return productName.getText();
    }

    public String getInformationOfReviews() {
        return informationOfReviews.getText();
    }

    public String getDescriptionOfTabReviews() {
        return descriptionOfTabReviews.getText();
    }

    public String getTextOfDeliveredReviewMessage() {
        return deliveredReviewMessage.getText();
    }

    public String getTextOfUndeliveredReviewMessage() {
        return undeliveredReviewMessage.getText();
    }

    public void clickOnInputFieldName() {
        inputFieldName.click();
    }

    public void clearOnInputFieldName() {
        inputFieldName.clear();
    }

    public void inputDataInFieldName(String name) {
        inputFieldName.sendKeys(name);
    }

    public void clickOnInputFieldReview() {
        inputFieldReview.click();
    }

    public void clearOnInputFieldReview() {
        inputFieldReview.clear();
    }

    public void inputDataInFieldReview(String text) {
        inputFieldReview.sendKeys(text);
    }

    public void clickOnRandomRadioButton(WebElement randomRadioButton) {
        randomRadioButton.click();
    }

    public void clickOnButtonAddReview() {
        buttonAddReview.click();
    }

    public void openTabReviews() {
        tabReviews.click();
    }

    public void clickAddToWishListButton() {
        addToWishListButton.click();
    }

    ///endregion

    ///region FUNCTIONALITY

    //compareButton
    public ProductPage clickCompareButton() {
        compareButton.click();
        return this;
    }

    //addProductToCartAlertComponent
    public AlertComponent getAlertComponent() {
        alertComponent = new AlertComponent(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
        return alertComponent;
    }

    //methods for review
    public WebElement[] getRadioButtons() {
        WebElement[] radioButtons = {this.radioButtonUnderLow, this.radioButtonLow, this.radioButtonOk, this.radioButtonHigh, this.radioButtonWell};
        return radioButtons;
    }

    public int getReviewCounter() {
        String text = this.tabReviews.getText();
        String count = text.substring(text.indexOf('(') + 1, text.indexOf(')'));
        return Integer.parseInt(count);
    }

    //inputNameInNameField
    public void inputNameInNameField(String name) {
        clickOnInputFieldName();
        clearOnInputFieldName();
        inputDataInFieldName(name);
    }

    //inputReviewInReviewField
    public void inputTextInReviewField(String text) {
        clickOnInputFieldReview();
        clearOnInputFieldReview();
        inputDataInFieldReview(text);
    }

    public static int generateRandomIntegerDigit(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public WebElement getSpecificRadioButton(int value) {
        for (WebElement item : getRadioButtons()) {
            String specificRadioButton = item.getAttribute("value");
            if (specificRadioButton.equals(Integer.toString(value))) {
                return item;
            }
        }
        return null;
    }

    //full method
    public void writeReview(String name, String text) {
        openTabReviews();
        inputNameInNameField(name);
        inputTextInReviewField(text);
        int randomDigit = generateRandomIntegerDigit(1, 5);
        WebElement randomRadioButton = getSpecificRadioButton(randomDigit);
        clickOnRandomRadioButton(randomRadioButton);
        clickOnButtonAddReview();
    }

    ///endregion

    ///region LOGIC

    public ProductPage goToProductPageAfterAddToCart(int value) {
        getAvailableOptionsComponent().setTextQty(value);
        getAvailableOptionsComponent().clickOnAddToCartButton();
        return new ProductPage(driver);
    }

    //alert after add to cart
    public CartPage goToShoppingCartFromAlert() {
        getAlertComponent().clickOnCartLink();
        return new CartPage(driver);
    }

    public ProductPage goToProductPageFromAlert() {
        getAlertComponent().clickOnProductLink();
        return new ProductPage(driver);
    }

    public ComparisonPage goToComparisonPageFromAlert() {
        getAlertComponent().clickOnCompareLink();
        return new ComparisonPage(driver);
    }

    public WishListPage addProductToWishList() {
        clickAddToWishListButton();
        return new WishListPage(driver);
    }

    ///endregion
}