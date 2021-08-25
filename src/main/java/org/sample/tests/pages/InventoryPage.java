package org.sample.tests.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.*;
import static utils.Constants.*;

/**
 * PageObject for HomePage
 */
public class InventoryPage {

    private WebDriver driver;

    @FindBy(id = "shopping_cart_container")
    WebElement shopCart;

    @FindBy(css = ".primary_header")
    WebElement header;

    @FindBy(css = "span.title")
    WebElement productsTitle;

    String sideMenu = "div.bm-menu";

    @FindBy(id = "react-burger-menu-btn")
    WebElement sideMenuBtn;

    @FindBy(id = "react-burger-cross-btn")
    WebElement closeMenuBtn;

    @FindBy(css = "menu-item")
    List <WebElement> menuItems;

    @FindBys(@FindBy(css =".inventory_item_name"))
    private List<WebElement> inventories;

    @FindBy(css = ".footer_copy")
    public WebElement footerText;

    @FindBy(xpath="//*[@id=\"page_wrapper\"]/footer/ul/li")
    private List<WebElement> socialIcons;

    ArrayList<String> expectedSocialIcons = new ArrayList<>(Arrays.asList("Twitter", "Facebook", "LinkedIn"));
    ArrayList<String> expectedMenuItems = new ArrayList<>(Arrays.asList("ALL ITEMS", "ABOUT", "LOGOUT", "RESET APP STATE"));

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void verifyHeader() {
        assertTrue(header.isDisplayed());
        assertTrue(shopCart.isDisplayed());
    }

    public void verifyFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", footerText);
        assertEquals(footerText.getText(), FOOTER_TEXT, "incorrect footer text");
        for (WebElement icon : socialIcons) {
            assertTrue(expectedSocialIcons.contains(icon.getText()));
        }
    }

    public void checkAllInventoriesPresent(){
        List<String> actualItems = new ArrayList<>();
        List<String> expectedItems = Stream.of(SPACE_SEPARATED_ITEMS.split(","))
                .map(String::trim)
                .collect(toList());
        inventories.forEach(inventory -> actualItems.add(inventory.getText()));
        assertEquals(actualItems, expectedItems, "Lists do not match");
        }

    public void menuOpens() {
        sideMenuBtn.click();
        assertEquals(driver.findElements(By.cssSelector(sideMenu)).size(), 1);
        for (WebElement item : menuItems) {
            System.out.println(item.getText());
            assertTrue(expectedMenuItems.contains(item.getText()));
        }
    }
  }
