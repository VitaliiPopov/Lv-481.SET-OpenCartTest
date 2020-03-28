package com.opencart.tools;

import com.opencart.pages.account.RegisterPage;
import com.opencart.pages.account.SuccessRegisterPage;
import com.opencart.pages.admin.AdminCustomerPage;
import com.opencart.pages.admin.AdminHomePage;
import com.opencart.pages.admin.AdminLoginPage;
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
