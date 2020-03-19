package com.opencart.pages.product_table;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class CartDropdownComponent {

    //Components
    private List<ProductInCartButtonContainerComponent> productInCartButtonContainerComponents;

    public CartDropdownComponent(WebDriver driver) {
        initElements();
    }

    private void initElements() {
        productInCartButtonContainerComponents = new ArrayList<>();
    }


}
