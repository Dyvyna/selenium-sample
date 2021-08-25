package org.sample.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.Constants.*;

/**
 * PageObject for LoginPage
 */
public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    public WebElement submitBtn;

    @FindBy(xpath = "//div[@class='login_logo']")
    WebElement loginLogo;

    @FindBy(css = ".login_credentials > h4")
    WebElement possibleNames;

    @FindBy(css = ".login_password > h4")
    WebElement possiblePass;

    @FindBy(css = "div.error-message-container.error > h3")
    WebElement errorContainer;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void checkLogoAndPossiblePasswords() {
        loginLogo.isDisplayed();
        assertEquals(possibleNames.getText(), "Accepted usernames are:", "Incorrect possible usernames");
        assertEquals(possiblePass.getText(), "Password for all users:", "Incorrect possible passwords");
    }

    public void login(String name, String pass) {
        usernameField.sendKeys(name);
        passwordField.sendKeys(pass);
        submitBtn.click();
    }

    public void rightErrorDisplayed(String userType) {
        if (userType.equals("invalid")) {
            assertEquals(errorContainer.getText(), INVALID_USER_ERROR, "Wrong error for invalid user login");
        } else if (userType.equals("locked")) {
            assertEquals(errorContainer.getText(), LOCKED_USER_ERROR, "Wrong error for locked user login");
        } else if (userType.equals("empty")) {
            assertEquals(errorContainer.getText(), EMPTY_USER_ERROR, "Wrong error for empty user login");
        } else {
            fail("Neither invalid, nor locked user error notification");
        }
    }
}