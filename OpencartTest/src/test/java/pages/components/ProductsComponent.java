package pages.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsComponent {

    private WebElement productsLayout;
    //
    private List<ProductContainerComponent> productContainerComponents;

    public ProductsComponent(WebElement productsLayout) {
        this.productsLayout = productsLayout;
        initElements();
    }

    private void initElements(){
        productContainerComponents = new ArrayList<ProductContainerComponent>();
        for (WebElement current: productsLayout.findElements(By.xpath(""))) productContainerComponents.add(new ProductContainerComponent(current));
    }

}
