package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsInCartButtonComponent {

    private WebElement productsInCartButtonLayout;
    //
    private List<ProductInCartButtonItemComponent> productInCartButtonItemComponents;


    public ProductsInCartButtonComponent(WebElement productsInCartButtonLayout) {
        this.productsInCartButtonLayout = productsInCartButtonLayout;
        initElements();
    }




    private void initElements(){
        productInCartButtonItemComponents = new ArrayList<ProductInCartButtonItemComponent>();
        for (WebElement current: productsInCartButtonLayout.findElements(By.xpath(""))) productInCartButtonItemComponents.add(new ProductInCartButtonItemComponent(current));
    }

}
