package com.bookstore.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HomePage {

    private final WebDriver driver;

    private WebElement searchText;
    private WebElement searchButton;
    private List<WebElement> editLinks;
    private WebElement addButton;

    private final String url;

    public HomePage(WebDriver driver, int port) {
        this.driver = driver;
        url = String.format("http://localhost:%s/bookstore/", port);
    }

    public HomePage open() {

        driver.navigate().to(url);
        return this;
    }

    public HomePage addBook() {

        this.addButton.click();
        return this;
    }

    public HomePage editBook(int position) {

        this.editLinks.get(position).click();
        return this;
    }

    public HomePage search(String text) {

        this.searchText.sendKeys(text);
        this.searchButton.click();
        return this;
    }

    private void initWebElements() {

        this.searchText = driver.findElement(By.id("searchText"));
        this.searchButton = driver.findElement(By.id("searchButton"));
        this.editLinks = getEditLinks();
        this.addButton = driver.findElement(By.id("addButton"));
    }

    private List<WebElement> getEditLinks() {

        return driver.findElements(By.className("link"));
    }

    public HomePage assertIsOpened() {

        assertThat(driver.getCurrentUrl(), is(url));
        assertThat(driver.getTitle(), is("Book Store Home"));
        initWebElements();
        return this;
    }
}
