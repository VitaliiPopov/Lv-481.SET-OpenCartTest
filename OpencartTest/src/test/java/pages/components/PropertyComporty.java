package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PropertyComporty {

    private WebElement propertyComportyLayout;
    //
    private WebElement name;

    public PropertyComporty(WebElement propertyComportyLayout) {
        this.propertyComportyLayout = propertyComportyLayout;
        initElements();
    }

    private void initElements() {
        name = propertyComportyLayout.findElement(By.xpath(""));
    }


}
