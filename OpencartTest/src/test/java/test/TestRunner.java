package test;

import opencart.data.ConstantVariables;
import opencart.pages.HomePage;
import opencart.pages.account.LoginPage;
import opencart.tools.Driver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestRunner {

    @BeforeClass
    public void beforeClass() throws Exception {
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().get(ConstantVariables.URL);
    }
    @AfterClass
    public void afterClass() throws Exception {
        Driver.getDriver().quit();
    }

    public HomePage getHomePage(){
        return new HomePage(Driver.getDriver());
    }

    public LoginPage getloginPage(){
        return new LoginPage(Driver.getDriver());
    }

}
