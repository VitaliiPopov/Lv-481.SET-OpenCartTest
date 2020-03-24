package com.opencart.tools;

import com.opencart.data.ConstantVariables;
import com.opencart.pages.HomePage;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestRunner {

    @BeforeClass
    public void beforeClass() {
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().get(ConstantVariables.URL);
    }

    @AfterClass
    public void afterClass() {
        Driver.getDriver().quit();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.getScreenshot(Driver.getDriver());
            Driver.getDriver().get("https://137.116.222.54/index.php?route=account/logout");
        }
    }

    public HomePage getHomePage() {
        return new HomePage(Driver.getDriver());
    }

}
