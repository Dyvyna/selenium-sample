package org.sample.tests;

import org.openqa.selenium.WebDriver;
import org.sample.tests.pages.LoginPage;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Constants.INVENTORY_URL;
import static utils.Constants.LOGIN_PAGE_TITLE;

public class LoginTest extends BaseTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() throws IOException {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
    }

    @DataProvider
    public static Object[][] getLoginData() {
        return new Object[][]{
                // email, password, userType, shouldPass
                {"standard_user", "secret_sauce", "valid", true},
                {"locked_out_user", "secret_sauce", "locked", false},
                {"incorrect", "admin", "invalid", false},
                {"", "", "empty", false},
                {"!~!@#$%^&*()", "#$@%^$$*", "invalid", false},
        };
    }

    @Test
    public void verifyLoginPageLook() {
        assertEquals(LOGIN_PAGE_TITLE, driver.getTitle(), "Incorrect Login page title");
        loginPage.checkLogoAndPossiblePasswords();
    }

    @Test(dataProvider = "getLoginData")
    public void verifyLogin(String name, String pass, String userType, boolean shouldPass) {
        loginPage.login(name, pass);
        if (shouldPass) {
            assertEquals(INVENTORY_URL, driver.getCurrentUrl(), "Wrong Inventory page URL");
        } else {
            loginPage.rightErrorDisplayed(userType);
        }
    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}
