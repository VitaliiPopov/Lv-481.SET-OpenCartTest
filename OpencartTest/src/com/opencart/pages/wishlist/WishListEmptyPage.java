package com.opencart.pages.wishlist;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.account.MyAccountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishListEmptyPage extends AbstractPageWithHeader {

    private WebElement emptyMessageLabel;
    public static final String EMPTY_WISH_LIST_MESSAGE = "Your wish list is empty.";

    public WishListEmptyPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        emptyMessageLabel = driver.findElement(By.cssSelector("#content p"));
    }

    // label
    public String getLabelText() {
        return emptyMessageLabel.getText();
    }

}
