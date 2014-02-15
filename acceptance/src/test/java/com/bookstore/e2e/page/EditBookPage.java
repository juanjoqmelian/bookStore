package com.bookstore.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EditBookPage {

    private final WebDriver driver;

    private WebElement name;
    private WebElement category;
    private WebElement year;
    private WebElement price;

    private WebElement editButton;
    private WebElement cancelButton;

    private final String url;

    public EditBookPage(WebDriver driver, int port) {

        this.driver = driver;
        this.url = String.format("http://localhost:%s/bookStore/edit", port);
    }

    public EditBookPage open() {

        driver.navigate().to(url);
        initWebElements();
        return this;
    }

    private void initWebElements() {

        this.name = driver.findElement(By.id("name"));
        this.category = driver.findElement(By.id("category"));
        this.year = driver.findElement(By.id("year"));
        this.price = driver.findElement(By.id("price"));
        this.editButton = driver.findElement(By.id("editButton"));
        this.cancelButton = driver.findElement(By.id("cancelButton"));
    }

    public EditBookPage edit(String name, String category, String year, String price) {

        this.name.sendKeys(name);
        this.category.sendKeys(category);
        this.year.sendKeys(year);
        this.price.sendKeys(price);
        this.editButton.click();
        waitForSeconds(3);
        return this;
    }

    public EditBookPage cancel() {

        this.cancelButton.click();
        waitForSeconds(3);
        return this;
    }

    public EditBookPage assertIsOpened() {

        assertThat(driver.getCurrentUrl(), is(url));
        assertThat(driver.getTitle(), is("Book Store - Edit Book"));
        return this;
    }

    public EditBookPage assertMandatoryNameError() {
        WebElement nameError = driver.findElement(By.id("name.errors"));
        nameError.getText().contains("Name field is mandatory!");
        return this;
    }

    public EditBookPage assertMandatoryPriceError() {
        WebElement categoryError = driver.findElement(By.id("price.errors"));
        categoryError.getText().contains("Price field is mandatory!");
        return this;
    }

    private void waitForSeconds(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
