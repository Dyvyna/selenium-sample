package org.sample.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * base class for tests, where WebDriver initialization is done
 */
public class BaseTest {

    public WebDriver driver;

    public static String[][] getUserLogin() {
        return new String[][]{
                {"standard_user", "secret_sauce"}};
    }

    /**
     * WebDriver initialization, setting the properties file, implicit wait globally
     *
     * @return set WebDriver
     * @throws IOException
     */
    public WebDriver initializeDriver() throws IOException {

        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("test.properties");
        java.util.Properties prop = new Properties();
        prop.load(inputStream);
        String browserName = prop.getProperty("browser");
        String driverPath = prop.getProperty("driver.path");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPath + "//chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();

        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverPath + "//geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().deleteAllCookies();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }
}
