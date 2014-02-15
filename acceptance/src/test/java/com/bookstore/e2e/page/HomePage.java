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
    private List<WebElement> books;
    private WebElement addButton;

    private WebElement message;

    private final String url;

    public HomePage(WebDriver driver, int port) {
        this.driver = driver;
        url = String.format("http://localhost:%s/bookStore", port);
    }

    public HomePage open() {

        driver.navigate().to(url);
        initWebElements();
        return this;
    }

    public HomePage addBook() {

        this.addButton.click();
        return this;
    }

    public HomePage editBook(int position) {

        this.books.get(position).findElement(By.xpath("a")).click();
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
        this.books = getBooksTableRecords();
        this.addButton = driver.findElement(By.id("addButton"));
        this.message = driver.findElement(By.id("message"));
    }

    private List<WebElement> getBooksTableRecords() {

        WebElement table = driver.findElement(By.id("books"));

        return table.findElements(By.xpath("tr"));
    }

    public HomePage assertIsOpened() {

        assertThat(driver.getCurrentUrl(), is(url));
        assertThat(driver.getTitle(), is("Book Store Home"));
        return this;
    }

    public HomePage assertAddedBookSuccessMessage() {

        assertThat(this.message.getText(), is("Book added successfully."));
        return this;
    }
}
