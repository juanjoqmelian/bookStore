package com.bookstore.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AddBookPage {

    private final WebDriver driver;

    private WebElement name;
    private WebElement category;
    private WebElement year;
    private WebElement price;

    private WebElement saveButton;
    private WebElement cancelButton;

    private final String url;

    public AddBookPage(WebDriver driver, int port) {

        this.driver = driver;
        this.url = String.format("http://localhost:%s/bookstore/add", port);
    }

    public AddBookPage open() {

        driver.navigate().to(url);
        initWebElements();
        return this;
    }

    private void initWebElements() {

        this.name = driver.findElement(By.id("name"));
        this.category = driver.findElement(By.id("category"));
        this.year = driver.findElement(By.id("year"));
        this.price = driver.findElement(By.id("price"));
        this.saveButton = driver.findElement(By.id("saveButton"));
        this.cancelButton = driver.findElement(By.id("cancelButton"));
    }

    public AddBookPage save(String name, String category, String year, String price) {
        this.name.sendKeys(name);
        this.category.sendKeys(category);
        this.year.sendKeys(year);
        this.price.sendKeys(price);
        saveButton.click();
        waitForSeconds(3);
        return this;
    }

    public AddBookPage cancel() {
        cancelButton.click();
        waitForSeconds(3);
        return this;
    }

    public AddBookPage assertIsOpened() {
        assertThat(driver.getCurrentUrl(), is(url));
        assertThat(driver.getTitle(), is("Book Store - Add new book"));
        return this;
    }

    public AddBookPage assertMandatoryNameError() {
        WebElement nameError = driver.findElement(By.id("name.errors"));
        nameError.getText().contains("Name field is mandatory!");
        return this;
    }

    public AddBookPage assertMandatoryPriceError() {
        WebElement categoryError = driver.findElement(By.id("price.errors"));
        categoryError.getText().contains("Price field is mandatory!");
        return this;
    }

    private void waitForSeconds(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
