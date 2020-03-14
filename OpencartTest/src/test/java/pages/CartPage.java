package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.ProductTableComponent;
import pages.parts.AHeaderPart;

public class CartPage extends AHeaderPart {

    private ProductTableComponent productTableComponent;

    public CartPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        productTableComponent = new ProductTableComponent(driver.findElement(By.xpath("")));
    }
}
