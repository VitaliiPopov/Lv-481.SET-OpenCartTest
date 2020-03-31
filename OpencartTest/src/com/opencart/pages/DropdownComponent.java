package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DropdownComponent {

    //DATA
    private final String WEBELEMENT_NOT_FOUND = "WebElement not Found";
    private WebDriver driver;
    //
    private List<WebElement> listOptions;

    public DropdownComponent(WebDriver driver, By searchLocator) {
        this.driver = driver;
        initElements(searchLocator);
    }

    private void initElements(By searchLocator) {
        listOptions = driver.findElements(searchLocator);
    }


    //FUNCTIONAL

    //listOptions
    public WebElement getDropdownOptionByPartialName(String optionName) {
        WebElement result = null;
        for (WebElement current : listOptions) {
            if (current.getText().toLowerCase().contains(optionName.toLowerCase())) {
                result = current;
                break;
            }
        }
        if (result == null) {
            throw new RuntimeException(WEBELEMENT_NOT_FOUND);
        }
        return result;
    }

    public List<String> getListOptionsText() {
        List<String> result = new ArrayList<>();
        for (WebElement currency : listOptions) {
            result.add(currency.getText());
        }
        if (result == null) {
            throw new RuntimeException(WEBELEMENT_NOT_FOUND);
        }
        return result;
    }

    public WebElement dropdownOptionByPartialName(String optionName) {
        WebElement result = null;
        for (WebElement current : listOptions) {
            if (current.getText().toLowerCase().contains(optionName.toLowerCase())) {
                result = current;
                break;
            }
        }
        if (result == null) {
            throw new RuntimeException(WEBELEMENT_NOT_FOUND);
        }
        return result;
    }

    public boolean isExistDropdownOptionByPartialName(String optionName) {
        boolean isFound = false;
        for (String current : getListOptionsText()) {
            if (current.toLowerCase().contains(optionName.toLowerCase())) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new RuntimeException(WEBELEMENT_NOT_FOUND);
        }
        return isFound;
    }

    public void clickDropdownOptionByPartialName(String optionName) {
        if (isExistDropdownOptionByPartialName(optionName)) getDropdownOptionByPartialName(optionName).click();
        else throw new RuntimeException(WEBELEMENT_NOT_FOUND);
    }
}
