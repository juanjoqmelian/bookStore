package com.bookstore.e2e;

import com.bookstore.e2e.page.AddBookPage;
import com.bookstore.e2e.page.HomePage;
import com.bookstore.e2e.page.SuccessPage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AddBookIT extends AcceptanceTestEnvironment{

    private HomePage homePage;

    private AddBookPage addBookPage;

    private SuccessPage successPage;

    @Before
    public void setUp() {

        this.homePage = webFactory.homePage();
        this.addBookPage = webFactory.addBookPage();
        this.successPage = webFactory.successPage();
    }

    @Ignore
    @Test
    public void shouldOpenAddBookPage() {

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened();
    }

    @Ignore
    @Test
    public void shouldCreateANewBook() {

        final String name = "Effective Java";
        final String category = "Java";
        final String year = "2000";
        final String price = "22.90";

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened()
                .save(name, category, year, price);

        successPage.assertIsOpened()
                .goAhead();

        homePage.assertIsOpened();
    }

    @Ignore
    @Test
    public void shouldShowErrorIfNameIsEmpty() {

        final String empty_name = "";
        final String category = "Java";
        final String year = "2000";
        final String price = "22.90";

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened()
                .save(empty_name, category, year, price)
                .assertMandatoryNameError();
    }

    @Ignore
    @Test
    public void shouldShowErrorIfPriceIsEmpty() {

        final String name = "Effective Java";
        final String category = "Java";
        final String year = "2000";
        final String empty_price = "";

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened()
                .save(name, category, year, empty_price)
                .assertMandatoryPriceError();
    }
}
