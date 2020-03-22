package com.opencart.pages;

import com.opencart.pages.search.ProductContainersComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AbstractPageWithHeader {

    private List<ProductContainersComponent> productContainersComponents;

    // CompareButtonMainPage
    // "//a[text()='MacBook']/../../following-sibling::div/button[@data-original-title='Add to Wish List']"
    public HomePage(WebDriver driver) {

        super(driver);
        InitializeProductContainers();
    }

    private void InitializeProductContainers() {

        List<WebElement> productContainers = driver.findElements(By.xpath("//div[contains(@class,'product-thumb')]"));
        productContainersComponents = new ArrayList<>();
        for (WebElement current : productContainers) {
            productContainersComponents.add(new ProductContainersComponent(current));
        }
    }

}
