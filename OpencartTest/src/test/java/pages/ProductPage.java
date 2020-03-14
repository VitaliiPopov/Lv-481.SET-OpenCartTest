package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.AvailableOptionsComponent;
import pages.parts.AHeaderPart;

public class ProductPage extends AHeaderPart {

    private AvailableOptionsComponent availableOptionsComponent;

    public ProductPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        availableOptionsComponent = new AvailableOptionsComponent(driver.findElement(By.xpath("")));
    }

}
