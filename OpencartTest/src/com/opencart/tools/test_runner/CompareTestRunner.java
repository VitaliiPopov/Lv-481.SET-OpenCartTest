package com.opencart.tools.test_runner;

import com.opencart.pages.HomePage;
import com.opencart.tools.Driver;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;

public class CompareTestRunner {

    @BeforeClass
    public void beforeClass() {
        Driver.getDriver();
    }

    @AfterClass
    public void afterClass() {
        Driver.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        Driver.clearCookies();
    }

    @Step("Initialize home page.")
    public HomePage getHomePage() {
        return new HomePage(Driver.getDriver());
    }
}