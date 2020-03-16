package opencart.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import opencart.pages.AbstractPageWithHeader;

import java.util.List;

public class CartPage extends AbstractPageWithHeader {

    //Components
    private List<ProductTableContainersComponent> productTableContainersComponents;
    //WebElements
    private WebElement totalPrice;
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){

    }


}
