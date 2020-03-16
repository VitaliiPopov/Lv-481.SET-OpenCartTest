package opencart.pages.common;

import org.openqa.selenium.WebElement;

public class ProductTableContainersComponent {

    private WebElement productTableItemLayout;
    //Components
    private QuantityComponent quantityComponent;
    private ActionComponent actionComponent;
    private CompariseComponent compariseComponent;
    //WebElements
    private WebElement productName;
    private WebElement productPrice;
    private WebElement totalProductPrice;


    public ProductTableContainersComponent(WebElement productTableItemLayout) {
        this.productTableItemLayout = productTableItemLayout;
        initElement();
    }

    private void initElement(){

    }


}
