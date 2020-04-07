package com.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchCriteriaComponent {

    private WebElement searchCriteriaLayout;
    private WebElement searchElementLable;
    private WebElement keywordsField;
    private WebElement categoriesDropdown;
    private WebElement subcategoriesCheckbox;
    private WebElement descriptionsCheckbox;
    private WebElement searchButton;

    public SearchCriteriaComponent(WebElement searchCriteriaLayout) {
        this.searchCriteriaLayout = searchCriteriaLayout;
        initElements();
    }

    private void initElements() {
        searchElementLable = searchCriteriaLayout.findElement(By.xpath("//div[@id='content']/h1"));
        keywordsField = searchCriteriaLayout.findElement(By.cssSelector("#input-search.form-control"));
        categoriesDropdown = searchCriteriaLayout.findElement(By.cssSelector("#content select:first-child"));
        subcategoriesCheckbox = searchCriteriaLayout.findElement(By.xpath("//input[@name='sub_category']"));
        descriptionsCheckbox = searchCriteriaLayout.findElement(By.xpath("//input[@name='description']"));
        searchButton = searchCriteriaLayout.findElement(By.xpath("//input[@id='button-search']"));
    }

    //check if search label contains text from searсh field
    public boolean searchLabelContainSearchText(String searchText) {
        return getSearchLableText().contains(searchText);
    }

    private String getSearchLableText() {
        return searchElementLable.getText();
    }

    public void clickCategoriesDropdown(String optionText) {
        List<WebElement> options = categoriesDropdown.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if ((option.getText().trim()).contains(optionText))
                option.click();
        }
    }

    public void clickSubcategoriesCheckbox() {
        subcategoriesCheckbox.click();
    }

    public void clickDescriptionsCheckbox() {
        if (!isDescriptionCheckboxSelected()) {
            descriptionsCheckbox.click();
        }
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    //check if Subcategories checkbox is enabled to click
    public boolean isSubcategoriesCheckboxEnabled() {
        return subcategoriesCheckbox.isEnabled();
    }

    //check if description checkbox is selected
    public boolean isDescriptionCheckboxSelected() {
        return descriptionsCheckbox.isSelected();
    }

}