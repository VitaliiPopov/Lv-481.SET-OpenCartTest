package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PropertiesComponent {

    private WebElement propertiesComponentLayout;
    //
    private List<PropertyComporty> propertyComporties;

    public PropertiesComponent(WebElement propertiesComponentLayout) {
        this.propertiesComponentLayout = propertiesComponentLayout;
        initElements();
    }

    private void initElements() {
        propertyComporties = new ArrayList<PropertyComporty>();
        for (WebElement current: propertiesComponentLayout.findElements(By.xpath(""))) propertyComporties.add(new PropertyComporty(current));
    }


}
