package opencart.pages.product;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class AvailableOptionsComponent {

    private WebElement availableOptionsComponentLayout;
    //
    private List<Map<WebElement, List<WebElement>>> properties;
    private WebElement qty;
    private WebElement addToCartButton;

    public AvailableOptionsComponent(WebElement availableOptionsComponentLayout) {
        this.availableOptionsComponentLayout = availableOptionsComponentLayout;
        initElements();
    }

    private void initElements() {

    }


}
