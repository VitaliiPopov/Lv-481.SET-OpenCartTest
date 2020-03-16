package opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import opencart.pages.AbstractPageWithHeader;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends AbstractPageWithHeader {

    //Components
    private List<ProductTableContainersComponent> productTableContainersComponents;

    public WishListPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {

    }

    //PAGE OBJECT


}
