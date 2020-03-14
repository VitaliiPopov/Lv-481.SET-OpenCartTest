package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AvailableOptionsComponent {

    private WebElement availableOptionsComponentLayout;
    //
    private List<FormGroupComponent> formGroupComponents;

    public AvailableOptionsComponent(WebElement availableOptionsComponentLayout) {
        this.availableOptionsComponentLayout = availableOptionsComponentLayout;
        initElements();
    }

    private void initElements() {
        formGroupComponents = new ArrayList<FormGroupComponent>();
        for (WebElement current: availableOptionsComponentLayout.findElements(By.xpath(""))) formGroupComponents.add(new FormGroupComponent(current));
    }

}
