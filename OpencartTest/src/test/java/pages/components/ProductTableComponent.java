package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductTableComponent {

    private WebElement productTableLayout;

    private List<ProductTableItemComponent> productTableItemComponents;

    public ProductTableComponent(WebElement productTableLayout) {
        this.productTableLayout = productTableLayout;
        initElements();
    }

    private void initElements(){
        productTableItemComponents = new ArrayList<ProductTableItemComponent>();
        for (WebElement current: productTableLayout.findElements(By.xpath(""))) productTableItemComponents.add(new ProductTableItemComponent(current));
    }
}
