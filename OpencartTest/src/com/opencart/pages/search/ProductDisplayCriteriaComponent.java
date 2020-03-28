package com.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class ProductDisplayCriteriaComponent {

    private WebElement ProductDisplayCriteriaLayout;

    private WebElement sortDropdownComponent;
    private WebElement showDropdownComponent;
    private WebElement listButton;
    private WebElement gridButton;
    private WebElement productCountLable;

    public ProductDisplayCriteriaComponent(WebElement ProductDisplayCriteriaLayout) {
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

    //click dropdown by WebElement and option text
    private void clickDropdown(WebElement dropdownComponent, String optionText) {
        try {
            Select sel = new Select(dropdownComponent);
            List<WebElement> options = sel.getOptions();
            for (WebElement option : options) {
                if ((option.getText()).contains(optionText)) {
                    option.click();
                }
            }
        } catch (StaleElementReferenceException ex) {
            //ignor StaleElementReferenceException exception
        }
    }

    //click Sort By dropdown by option text
    public void clickSortByDropdown(String optionText) {
        clickDropdown(sortDropdownComponent, optionText);
    }

    //click Showdropdown by option text with count of product to display
    public void clickShowDropdown(String optionText) {
        clickDropdown(showDropdownComponent, optionText);
    }

    public void clickListButton() {
        listButton.click();
    }

    public void clickGridButton() {
        gridButton.click();
    }

    //get number values from label by index
    private int getProductCountFromLabel(int index) {
        String productCount = productCountLable.getText();
        productCount = productCount.replaceAll("[^0-9]+", " ");
        List<String> numberList = Arrays.asList(productCount.trim().split(" "));
        return new Integer(numberList.get(index));
    }

    public int getPagesCountFromLabel() {
        return getProductCountFromLabel(3);
    }

    public int getListSizeCountFromLabel() {
        return getProductCountFromLabel(2);
    }

    //get max count of products on page according to Show dropdown
    public int getShowCountFromLabel() {
        return getProductCountFromLabel(1);
    }
}