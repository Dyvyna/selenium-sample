package org.sample.tests;

import org.openqa.selenium.WebDriver;
import org.sample.tests.pages.InventoryPage;
import org.sample.tests.pages.LoginPage;
import org.testng.annotations.*;

import java.io.IOException;

//TODO:
//- check closing menu works (as button not clickable)
//- check inventories sorting works
//- addToCart

/**
 * tests from Inventories page
 */
public class InventoryPageTest extends BaseTest {

    WebDriver driver;
    InventoryPage inventoryPage;
    LoginPage loginPage;

    String userLogin = getUserLogin()[0][0];
    String password = getUserLogin()[0][1];

    @BeforeClass
    public void setup() throws IOException {
        driver = initializeDriver();
        inventoryPage = new InventoryPage(driver);
        loginPage = new LoginPage(driver);

    }

    @BeforeMethod
    public void loginBeforeTest(){
        loginPage.login(userLogin, password);
    }

    @Test
    public void verifyInventoryPageLook() {
        inventoryPage.verifyHeader();
        inventoryPage.checkAllInventoriesPresent();
        inventoryPage.verifyFooter();
    }
    @Test
    public void verifySidebarMenu() {
        inventoryPage.menuOpens();
    }

    @AfterTest
    public void close() {
        driver.close();
    }

}
