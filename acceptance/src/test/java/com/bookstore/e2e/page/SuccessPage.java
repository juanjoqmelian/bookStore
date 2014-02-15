package com.bookstore.e2e.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SuccessPage {

    private WebDriver driver;

    private WebElement continueButton;

    private String url;

    public SuccessPage(WebDriver driver, int port) {

        this.driver = driver;
        this.url = String.format("http://localhost:%s/bookstore/success", port);
    }

    public SuccessPage assertIsOpened() {

        assertThat(driver.getCurrentUrl(), is(url));
        assertThat(driver.getTitle(), is("Success Page"));
        return this;
    }

    public SuccessPage goAhead() {

        this.continueButton.click();
        waitForSeconds(3);
        return this;
    }

    private void waitForSeconds(int seconds) {

        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
