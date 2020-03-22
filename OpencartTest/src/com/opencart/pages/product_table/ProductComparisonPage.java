package com.opencart.pages.product_table;

import com.opencart.pages.AbstractPageWithHeader;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ProductComparisonPage extends AbstractPageWithHeader {

    //Components
    private List<ProductInComapreContainerComponent> productInComapreContainerComponents;

    public ProductComparisonPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        productInComapreContainerComponents = new ArrayList<>(4);
    }


}
