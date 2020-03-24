package com.opencart.pages.search;

import com.opencart.pages.DropdownComponent;
import com.opencart.tools.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDisplayCriteriaComponent {

    WebDriver driver;
    private WebElement ProductDisplayCriteriaLayout;

    private WebElement sortDropdownComponent;
    private WebElement showDropdownComponent;

    private WebElement listButton;
    private WebElement gridButton;
    private WebElement productCountLable;


    public ProductDisplayCriteriaComponent(WebDriver driver, WebElement ProductDisplayCriteriaLayout) {
        this.driver = driver;
        this.ProductDisplayCriteriaLayout = ProductDisplayCriteriaLayout;
        initElements();
    }

    private void initElements() {
        listButton = ProductDisplayCriteriaLayout.findElement(By.xpath("//button[@id='list-view']"));
        gridButton = ProductDisplayCriteriaLayout.findElement(By.xpath("//button[@id='grid-view']"));
        sortDropdownComponent = ProductDisplayCriteriaLayout.findElement(By.xpath("//select[@id='input-sort']"));
        showDropdownComponent = ProductDisplayCriteriaLayout.findElement(By.cssSelector("select#input-limit.form-control"));
        productCountLable = ProductDisplayCriteriaLayout.findElement(By.xpath("//div[last()]/div[@class='col-sm-6 text-right']"));
    }

    private void clickDropdown(WebElement dropdownComponent, String optionText) {
        try {
            Select sel = new Select(dropdownComponent);
            List<WebElement> options = sel.getOptions();

            for (WebElement option : options) {
                if ((option.getText()).contains(optionText)) {
                    System.out.println(option.getText());
                    option.click();
                }
            }
        } catch (StaleElementReferenceException ex){
            //ignor StaleElementReferenceException exception
        }

    }

    public void clickSortByDropdown(String optionText) {
        clickDropdown(sortDropdownComponent, optionText);
    }

    public void clickShowDropdown(String optionText) {
        clickDropdown(showDropdownComponent, optionText);
    }

    //public void productCountLable
    public void clickListButton() {
        listButton.click();
    }

    public void clickGridButton() {
        gridButton.click();
    }

    public String ProductCountText() {
        return productCountLable.getText();
    }

    public void clickSortDropdownComponent() {
        sortDropdownComponent.click();
    }

    public void clickShowDropdownComponent() {
        showDropdownComponent.click();
    }

    public Integer getProductCountFromLable() {
        String productCount = productCountLable.getText();
        productCount = productCount.replaceAll("[^0-9]+", " ");
        List<String> numberList = Arrays.asList(productCount.trim().split(" "));
        return new Integer(numberList.get(2));
    }

    public WebElement getSortByDropdown() {
        return sortDropdownComponent;
    }

}
