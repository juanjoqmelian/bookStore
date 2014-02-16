package com.bookstore.e2e;

import com.bookstore.e2e.page.WebNavigationFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public abstract class AcceptanceTestEnvironment {

    private static final Logger logger = LoggerFactory.getLogger(AcceptanceTestEnvironment.class);

    private static final String WEBSITE_CONTEXT_PATH = "/bookstore";
    private static final int PORT = 8090;

    private static final String FIREBUG_PATH = "firebug-1.11.4.xpi";
    private static final String FIREBUG_EXTENSION_KEY = "extensions.firebug.currentVersion";
    private static final String FIREBUG_VERSION = "1.11.4";

    private static WebDriver driver;

    protected static WebNavigationFactory webFactory;

    @BeforeClass
    public static void beforeClass() throws Exception {
        //openFirefox(); //Remove comment to visualize test in FireFox
        openHtmlUnit();
        webFactory = new WebNavigationFactory(driver, PORT, WEBSITE_CONTEXT_PATH);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        closeDriver();
    }

    private static FirefoxProfile buildProfile() throws IOException {

        File file = new ClassPathResource(FIREBUG_PATH).getFile();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(file);
        firefoxProfile.setPreference(FIREBUG_EXTENSION_KEY, FIREBUG_VERSION);
        return firefoxProfile;
    }

    private static void openFirefox() throws InterruptedException {

        try {
            driver = new FirefoxDriver(buildProfile());
        } catch (IOException e) {
            logger.error("An exception occurred trying to open FireFox driver:", e);
            closeDriver();
        }
    }

    private static void openHtmlUnit() throws InterruptedException {
        driver = new HtmlUnitDriver();
    }

    private static void closeDriver() {

        if (driver != null) {
            driver.quit();
        }
    }
}