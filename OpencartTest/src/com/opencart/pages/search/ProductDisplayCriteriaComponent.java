package com.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class ProductDisplayCriteriaComponent {

    private WebElement productDisplayCriteriaLayout;

    private WebElement sortDropdownComponent;
    private WebElement showDropdownComponent;

    private WebElement listButton;
    private WebElement gridButton;
    private WebElement productCountLabel;
    private WebElement productCompareLink;

    public ProductDisplayCriteriaComponent(WebElement ProductDisplayCriteriaLayout) {

        this.productDisplayCriteriaLayout = ProductDisplayCriteriaLayout;
        initElements();
    }

    private void initElements() {
        listButton = productDisplayCriteriaLayout.findElement(By.xpath("//button[@id='list-view']"));
        gridButton = productDisplayCriteriaLayout.findElement(By.xpath("//button[@id='grid-view']"));
        sortDropdownComponent = productDisplayCriteriaLayout.findElement(By.xpath("//select[@id='input-sort']"));
        showDropdownComponent = productDisplayCriteriaLayout.findElement(By.cssSelector("select#input-limit.form-control"));
        productCountLabel = productDisplayCriteriaLayout.findElement(By.xpath("//div[last()]/div[@class='col-sm-6 text-right']"));
        productCompareLink = productCountLabel.findElement(By.xpath("//a[@id='compare-total']"));
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
        } catch (StaleElementReferenceException ex) {
            //ignor StaleElementReferenceException exception
        }

    }

    public void clickProductCompareLink(){
        productCompareLink.click();
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

    public void clickSortDropdownComponent() {
        sortDropdownComponent.click();
    }

    public void clickShowDropdownComponent() {
        showDropdownComponent.click();
    }

    public Integer getProductCountFromLabel() {
        String productCount = productCountLabel.getText();
        productCount = productCount.replaceAll("[^0-9]+", " ");
        List<String> numberList = Arrays.asList(productCount.trim().split(" "));
        return new Integer(numberList.get(2));
    }

    public WebElement getSortByDropdown() {
        return sortDropdownComponent;
    }
}