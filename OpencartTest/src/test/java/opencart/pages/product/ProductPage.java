package opencart.pages.product;

import opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractPageWithHeader {

    private AvailableOptionsComponent availableOptionsComponent;
    @FindBy(how = How.CSS, css = "a[href='#tab-review']")
    private WebElement tabReviews;
    @FindBy(how = How.XPATH, xpath = "//input[contains(@id,'input-name')]")
    private WebElement inputFieldName;
    @FindBy(how = How.XPATH, xpath = "//textarea[contains(@id,'input-review')]")
    private WebElement inputFieldReview;
    @FindBy(how=How.XPATH,xpath="//input[contains(@value,'1')]")
    private WebElement checkboxUnderLow;
    @FindBy(how=How.XPATH,xpath="//input[contains(@value,'2')]")
    private WebElement checkboxLow;
    @FindBy(how=How.XPATH,xpath="//input[contains(@value,'3')]")
    private WebElement checkboxOk;
    @FindBy(how=How.XPATH,xpath="//input[contains(@value,'4')]")
    private WebElement checkboxHigh;
    @FindBy(how=How.XPATH,xpath="//input[contains(@value,'5')]")
    private WebElement checkboxWell;
    @FindBy(how=How.XPATH,xpath="//button[contains(@id,'button-review')]")
    private WebElement buttonAddReview;
    @FindBy(how=How.XPATH,xpath="//div[contains(@id,'review')]")
    private WebElement informationOfReviews;
    @FindBy(how=How.XPATH,xpath="//div[contains(@id,'review')]/following-sibling::h2")
    private WebElement descriptionOfTabReviews;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    //private void initElements(){
        //availableOptionsComponent = new AvailableOptionsComponent(driver);
    //}

    public void openTabReviews(){
        tabReviews.click();
    }

    public String getInformationOfReviews(){
        return informationOfReviews.getText();
    }

    public String getDescriptionOfTabReviews(){
        return descriptionOfTabReviews.getText();
    }
    public void clickOnInputFieldName(){
        inputFieldName.click();
    }

    public void clearOnInputFieldName(){
        inputFieldName.clear();
    }

    public void inputDataInFieldName(String name){
        inputFieldName.sendKeys(name);
    }

    public void clickOnInputFieldReview(){
        inputFieldReview.click();
    }

    public void clearOnInputFieldReview(){
        inputFieldReview.clear();
    }

    public void inputDataInFieldReview(String text){
        inputFieldReview.sendKeys(text);
    }

    public void clickOnCheckboxUnderLow(){
        checkboxUnderLow.click();
    }

    public void clickOnCheckboxLow(){
        checkboxLow.click();
    }

    public void clickOnCheckboxOk(){
        checkboxOk.click();
    }

    public void clickOnCheckboxHigh(){
        checkboxHigh.click();
    }

    public void clickOnCheckboxWell(){
        checkboxWell.click();
    }

    public void clickOnButtonAddReview(){
        buttonAddReview.click();
    }

    //functionality

    //inputNameInNameField
    public void inputNameInNameField(String name){
        clickOnInputFieldName();
        clearOnInputFieldName();
        inputDataInFieldName(name);
    }

    //inputReviewInReviewField
    public void inputTextInReviewField(String text){
        clickOnInputFieldReview();
        clearOnInputFieldReview();
        inputDataInFieldReview(text);
    }

    //business logic

    public void writeReview(String name, String text){
        openTabReviews();
        inputNameInNameField(name);
        inputTextInReviewField(text);
        clickOnCheckboxLow();
        clickOnButtonAddReview();
    }

}
