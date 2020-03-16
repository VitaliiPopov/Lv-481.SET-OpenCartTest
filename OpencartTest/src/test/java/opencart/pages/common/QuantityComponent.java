package opencart.pages.common;

import org.openqa.selenium.WebElement;

public class QuantityComponent {

    //
    private WebElement quantityLayout;
    //
    private WebElement removeButton;
    private WebElement refreshButton;
    private WebElement quantityInput;

    public QuantityComponent(WebElement quantityLayout) {
        this.quantityLayout = quantityLayout;
        initElements();
    }

    private void initElements() {

    }

}
