package opencart.pages.common;

import opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductComparisonPage extends AbstractPageWithHeader {

    //Components
    private List<ProductTableContainersComponent> productTableContainersComponents;

    public ProductComparisonPage(WebDriver driver) {
        super(driver);
    }

    private void initElements() {

    }


}
