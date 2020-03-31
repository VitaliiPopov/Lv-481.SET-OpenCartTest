package com.opencart.tools;

import com.opencart.tools.test_runner.AccountTestRunner;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AccountListener implements ITestListener {

    JsonDataConfig jsonDataConfig = new JsonDataConfig("TestData.json");
    AdminManager admin = new AdminManager();
    AccountTestRunner runner = new AccountTestRunner();

    @Override
    public void onTestFailure(ITestResult Result)
    {
        String email = jsonDataConfig.getEmailFromJson(0);
        try {
            admin.deleteCustomerFromAdmin(email);
            runner.primaryRegistration();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
