package opencart.pages.product;

import org.openqa.selenium.WebDriver;
import opencart.pages.AbstractPageWithHeader;

public class ProductPage extends AbstractPageWithHeader {

    private AvailableOptionsComponent availableOptionsComponent;

    public ProductPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        availableOptionsComponent = new AvailableOptionsComponent(driver);
    }

}
