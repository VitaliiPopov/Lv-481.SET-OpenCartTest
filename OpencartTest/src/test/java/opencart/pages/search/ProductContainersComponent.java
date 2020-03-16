package opencart.pages.search;

import org.openqa.selenium.WebElement;

public class ProductContainersComponent {


    private WebElement productContainerLayout;
    //
    private WebElement name;
    private WebElement addToCartButton;

    public ProductContainersComponent(WebElement productContainerLayout) {
        this.productContainerLayout = productContainerLayout;
        initElements();
    }

    private void initElements(){

    }


}
