package com.opencart.tools.test_runner;

import com.opencart.pages.HomePage;
import com.opencart.tools.Instance;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;

public class CompareTestRunner {

    @BeforeClass
    public void beforeClass() {
        Instance.getDriver();
    }

    @AfterClass
    public void afterClass() {
        Instance.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        Instance.clearCookies();
    }

    @Step("Initialize home page.")
    public HomePage getHomePage() {
        return new HomePage(Instance.getDriver());
    }
}