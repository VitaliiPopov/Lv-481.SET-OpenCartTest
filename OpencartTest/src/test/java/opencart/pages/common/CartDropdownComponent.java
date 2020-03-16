package opencart.pages.common;

import org.openqa.selenium.WebElement;

import java.util.List;

public class CartDropdownComponent {

    //WebElement
    private WebElement cartDropdownLayout;
    //Components
    private List<ProductTableContainersComponent> productTableContainersComponents;

    public CartDropdownComponent(WebElement cartDropdownLayout) {
        this.cartDropdownLayout = cartDropdownLayout;
        initElements();
    }

    private void initElements() {

    }


}
