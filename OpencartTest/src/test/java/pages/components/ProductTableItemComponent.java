package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductTableItemComponent {

    private WebElement productTableItemLayout;
    //
    private WebElement actionComponent;
    private WebElement quantityComponent;

    public ProductTableItemComponent(WebElement productTableItemLayout) {
        this.productTableItemLayout = productTableItemLayout;
        initElements();
    }

    private void initElements(){
        if(true) actionComponent = productTableItemLayout.findElement(By.xpath(""));
        else quantityComponent = productTableItemLayout.findElement(By.xpath(""));
    }

}
