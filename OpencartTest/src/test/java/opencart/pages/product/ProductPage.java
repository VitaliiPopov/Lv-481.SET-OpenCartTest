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

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    //private void initElements(){
        //availableOptionsComponent = new AvailableOptionsComponent(driver);
    //}

}
