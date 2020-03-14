package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.ProductTableComponent;
import pages.parts.AHeaderPart;

public class WishListPage extends AHeaderPart {

    private ProductTableComponent productTableComponent;

    public WishListPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        productTableComponent = new ProductTableComponent(driver.findElement(By.xpath("")));
    }
}
