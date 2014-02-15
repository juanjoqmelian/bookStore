package com.bookstore.e2e.page;

import org.openqa.selenium.WebDriver;

public class WebNavigationFactory {

    private final WebDriver driver;

    private final int port;

    private final String contextPath;


    public WebNavigationFactory(WebDriver driver, int port, String contextPath) {

        this.driver = driver;
        this.port = port;
        this.contextPath = contextPath;
    }

    public HomePage homePage() {
        return new HomePage(driver, port);
    }

    public AddBookPage addBookPage() {
        return new AddBookPage(driver, port);
    }

    public EditBookPage editBookPage() {
        return new EditBookPage(driver, port);
    }
}
