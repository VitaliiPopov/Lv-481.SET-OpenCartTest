package pages.parts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.CartDropdownComponent;
import pages.components.DropdownComponent;
import pages.components.SearchCriteriaComponent;

public class ASearchPart extends AHeaderPart{

    //Components
    private SearchCriteriaComponent searchCriteriaComponent;

    public ASearchPart(WebDriver driver) {
        super(driver);
    }

    private void initElements() {
        searchCriteriaComponent = new SearchCriteriaComponent(driver.findElement(By.xpath("")));
    }


}
