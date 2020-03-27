package com.opencart.pages.wishlist;

import com.opencart.pages.AbstractPageWithHeader;
import com.opencart.pages.account.MyAccountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishListEmptyPage extends AbstractPageWithHeader {

    private WebElement continueButton;
    private WebElement emptyMessageLabel;
    public static final String EMPTY_WISH_LIST_MESSAGE = "Your wish list is empty.";

    public WishListEmptyPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        continueButton = driver.findElement(By.xpath("//div[@class='pull-right']/a"));
        emptyMessageLabel = driver.findElement(By.cssSelector("#content p"));
    }

    // continue button
    public void clickContinueButton() {
        continueButton.click();
    }

    // label
    public String getLabelText() {
        return emptyMessageLabel.getText();
    }

    public MyAccountPage goToMyAccountPage() {
        clickContinueButton();
        return new MyAccountPage(driver);
    }
}
