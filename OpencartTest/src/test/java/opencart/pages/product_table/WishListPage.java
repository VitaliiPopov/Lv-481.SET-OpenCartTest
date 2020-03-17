package opencart.pages.product_table;

import org.openqa.selenium.WebDriver;
import opencart.pages.AbstractPageWithHeader;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends AbstractPageWithHeader {

    //Components
    private List<ProductInWishListContainerComponent> productInWishListContainerComponents;

    public WishListPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        productInWishListContainerComponents = new ArrayList<>();
    }

    //PAGE OBJECT


}
