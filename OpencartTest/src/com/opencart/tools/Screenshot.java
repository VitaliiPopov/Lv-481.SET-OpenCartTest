package com.opencart.tools;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Screenshot  {
    public static void run(ITestResult testResult, WebDriver driver) {

        if (testResult.getThrowable() != null) {
            try {
                takeScreenShot(testResult.getMethod().getMethodName(), driver);
                byte[] takeScreenShot= takeScreenShot(testResult.getMethod().getMethodName(),driver);
                Allure.addAttachment(testResult.getMethod().getMethodName(), new ByteArrayInputStream(takeScreenShot));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static byte[] takeScreenShot(String methodName,WebDriver driver) throws IOException {

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}