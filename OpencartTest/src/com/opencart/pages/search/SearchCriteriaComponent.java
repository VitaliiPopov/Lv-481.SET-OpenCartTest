package com.opencart.pages.search;

import com.opencart.pages.DropdownComponent;
import com.opencart.tools.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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

    public boolean searchLableContainSearchText(String searchText) {
        if (getSearchLableText().contains(searchText)) {
            return true;
        }
        return false;
    }

    private String getSearchLableText() {
        return searchElementLable.getText();
    }

    public void cleanKeywordsField() {
        keywordsField.clear();
    }

    public void clickKeywordsField() {
        keywordsField.click();
    }

    public void inputKeywordsField(String searchText) {
        keywordsField.sendKeys(searchText);
    }

    public void fillKeywordsField(String searchText) {
        clickKeywordsField();
        cleanKeywordsField();
        inputKeywordsField(searchText);
    }

    public void clickCategoriesDropdown(String optionText) {
        List<WebElement> options = categoriesDropdown.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if ((option.getText().trim()).contains(optionText))
                option.click();
        }
    }

    public WebElement GetCategoriesDropdown() {
        return categoriesDropdown;
    }

    public boolean isDropdownSelected(WebElement dropdown, String optionText) {
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        for (WebElement option : options) {
            String TempOption = option.getText().trim();
            if (TempOption.contains(optionText)) {
                if (option.isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clickSubcategoriesCheckbox() {
        subcategoriesCheckbox.click();
    }

    public void clickDescriptionsCheckbox() {
        descriptionsCheckbox.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public boolean isSubcategoriesCheckboxEnabled() {
        if (subcategoriesCheckbox.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

}