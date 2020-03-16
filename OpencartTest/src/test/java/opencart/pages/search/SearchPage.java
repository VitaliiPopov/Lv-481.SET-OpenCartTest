package opencart.pages.search;

import opencart.pages.AbstractPageWithHeader;
import opencart.pages.product.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPageWithHeader {

    //Components
    private List<ProductContainersComponent> productContainersComponents;
    private SearchCriteriaComponent searchCriteriaComponent;
    private ProductDisplayCriteriaComponent productDisplayCriteriaComponent;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private void initElements() {

    }


}
