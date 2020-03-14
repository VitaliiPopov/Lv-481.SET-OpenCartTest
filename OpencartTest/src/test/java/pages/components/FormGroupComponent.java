package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FormGroupComponent {

    private WebElement formGroupComponentLayout;
    //
    private WebElement name;
    //
    private List<PropertiesComponent> propertiesComponent;

    public FormGroupComponent(WebElement formGroupComponentLayout) {
        this.formGroupComponentLayout = formGroupComponentLayout;
        initElements();
    }

    private void initElements() {
        propertiesComponent = new ArrayList<PropertiesComponent>();
        for (WebElement current: formGroupComponentLayout.findElements(By.xpath(""))) propertiesComponent.add(new PropertiesComponent(current));
        name = formGroupComponentLayout.findElement(By.xpath(""));
    }

}
