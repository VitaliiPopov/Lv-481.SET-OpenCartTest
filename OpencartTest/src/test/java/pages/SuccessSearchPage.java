package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.components.ProductContainerComponent;
import pages.components.ProductDisplayCriteriaComponent;
import pages.components.ProductsComponent;
import pages.parts.ASearchPart;

import java.util.ArrayList;

public class SuccessSearchPage extends ASearchPart {

    private ProductDisplayCriteriaComponent productDisplayCriteriaComponent;
    private ProductsComponent productsComponent;


    public SuccessSearchPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        productDisplayCriteriaComponent = new ProductDisplayCriteriaComponent(driver.findElement(By.xpath("")));
        productsComponent = new ProductsComponent(driver.findElement(By.xpath("")));
    }

}
