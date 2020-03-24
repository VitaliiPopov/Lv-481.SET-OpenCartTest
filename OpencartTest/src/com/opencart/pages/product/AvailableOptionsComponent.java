package com.opencart.pages.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvailableOptionsComponent {

    private WebElement availableOptionsComponentLayout;
    //
    private List<Map<WebElement, List<WebElement>>> radioButtons;
    private List<Map<WebElement, List<WebElement>>> checkBoxes;
    private List<Map<WebElement, Select>> selects;
    private WebElement qty;
    private WebElement addToCartButton;


    public AvailableOptionsComponent(WebElement availableOptionsComponentLayout) {
        this.availableOptionsComponentLayout = availableOptionsComponentLayout;
    }

    //PAGE OBJECT

    //radioButtons
    public List<Map<WebElement, List<WebElement>>> getRadioButtons() {
        List<WebElement> propertiesValue = new ArrayList<>();
        Map<WebElement, List<WebElement>> propertiesComponent = new HashMap<>();
        radioButtons = new ArrayList<>();
        for (WebElement currentName : availableOptionsComponentLayout.findElements(By.xpath("//div[@class='radio']/label/../../../label"))) {
            propertiesComponent.clear();
            propertiesValue.clear();
            for (WebElement currentValue : availableOptionsComponentLayout.findElements(By.xpath("//div[@class='radio']/label"))) {
                propertiesValue.add(currentValue);
            }
            propertiesComponent.put(currentName, propertiesValue);
            radioButtons.add(propertiesComponent);
        }
        return radioButtons;
    }

    //checkBoxes
    public List<Map<WebElement, List<WebElement>>> getCheckBoxes() {
        List<WebElement> propertiesValue = new ArrayList<>();
        Map<WebElement, List<WebElement>> propertiesComponent = new HashMap<>();
        checkBoxes = new ArrayList<>();
        for (WebElement currentName : availableOptionsComponentLayout.findElements(By.xpath("//div[@class='checkbox']/label/../../../label"))) {
            propertiesComponent.clear();
            propertiesValue.clear();
            for (WebElement currentValue : availableOptionsComponentLayout.findElements(By.xpath("//div[@class='checkbox']/label"))) {
                propertiesValue.add(currentValue);
            }
            propertiesComponent.put(currentName, propertiesValue);
            checkBoxes.add(propertiesComponent);
        }
        return checkBoxes;
    }

    //selects
    public List<Map<WebElement, Select>> getSelects() {
        Map<WebElement, Select> propertiesComponent = new HashMap<>();
        selects = new ArrayList<>();
        for (WebElement currentName : availableOptionsComponentLayout.findElements(By.xpath("//select/../label"))) {
            propertiesComponent.clear();
            WebElement currentValues = availableOptionsComponentLayout.findElement(By.tagName("select"));
            Select select = new Select(currentValues);
            propertiesComponent.put(currentName, select);
            selects.add(propertiesComponent);
        }
        return selects;
    }

    //qty
    public WebElement getQty() {
        qty = availableOptionsComponentLayout.findElement(By.xpath("//div[@class='form-group']/input[@type='text']"));
        return qty;
    }

    public void setTextQty(int value) {
        getQty().clear();
        getQty().sendKeys(String.valueOf(value));
    }

    //addToCartButton
    public WebElement getAddToCartButton() {
        addToCartButton = availableOptionsComponentLayout.findElement(By.xpath("//div[@class='form-group']/button"));
        return addToCartButton;
    }

    public void clickOnAddToCartButton() {
        getAddToCartButton().click();
    }

    //FUNCTIONAL

    //label

    //radioButtons
    public List<WebElement> getRadioButtonByLabelName(String lableName) {
        List<WebElement> result = null;
        for (Map<WebElement, List<WebElement>> current : getRadioButtons())
            for (Map.Entry<WebElement, List<WebElement>> entry : current.entrySet())
                if (entry.getKey().getText().contains(lableName)) {
                    result = entry.getValue();
                    break;
                }
        if (result == null) throw new RuntimeException("Element not found");
        return result;
    }

    //checkBoxes
    public List<WebElement> getCheckBoxByLabelName(String lableName) {
        List<WebElement> result = null;
        for (Map<WebElement, List<WebElement>> current : getCheckBoxes())
            for (Map.Entry<WebElement, List<WebElement>> entry : current.entrySet())
                if (entry.getKey().getText().contains(lableName)) {
                    result = entry.getValue();
                    break;
                }
        if (result == null) throw new RuntimeException("Element not found");
        return result;
    }

    //selects
    public Select getSelectByLabelName(String lableName) {
        Select result = null;
        for (Map<WebElement, Select> current : getSelects())
            for (Map.Entry<WebElement, Select> entry : current.entrySet())
                if (entry.getKey().getText().contains(lableName)) {
                    result = entry.getValue();
                    break;
                }
        if (result == null) throw new RuntimeException("Element not found");
        return result;
    }

    //options

    //radioButton
    public void chooseRadioButtonOptionByPartialName(String lableName, String optionName) {
        WebElement result = null;
        for (WebElement current : getRadioButtonByLabelName(lableName)) {
            if (current.getText().contains(optionName)) {
                result = current;
                break;
            }
        }
        if (result == null) throw new RuntimeException("Element not found");
        result.click();
    }

    //checkBox
    public void chooseCheckBoxOptionByPartialName(String lableName, String optionName) {
        WebElement result = null;
        for (WebElement current : getCheckBoxByLabelName(lableName)) {
            if (current.getText().contains(optionName)) {
                result = current;
                break;
            }
        }
        if (result == null) throw new RuntimeException("Element not found");
        result.click();
    }

    //selects
    public void chooseSelectsOptionByName(String lableName, String optionName) {
        getSelectByLabelName(lableName).selectByVisibleText(optionName);
    }

//    public void chooseSelectsOptionByIndex(String lableName, int index){
//        getSelectByLabelName(lableName).selectByIndex(index);
//    }

    //setOptions
    public void setOptionsForAppleCinema(String radioButtonLableName, String radioButtonOptionName,
                                         String checkBoxLableName, String checkBoxOptionName,
                                         String selectLableName, String selectOptionsOptionName) {
        chooseRadioButtonOptionByPartialName(radioButtonLableName, radioButtonOptionName);
        chooseCheckBoxOptionByPartialName(checkBoxLableName, checkBoxOptionName);
        chooseSelectsOptionByName(selectLableName, selectOptionsOptionName);
    }
}