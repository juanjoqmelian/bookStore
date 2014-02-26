package com.bookstore.e2e;

import com.bookstore.e2e.page.AddBookPage;
import com.bookstore.e2e.page.HomePage;
import com.bookstore.e2e.page.SuccessPage;
import org.junit.Before;
import org.junit.Test;

public class AddBookIT extends AcceptanceTestEnvironment{

    private static final String BOOK_NAME = "Effective Java";
    public static final String CATEGORY = "Java";
    public static final String YEAR = "2000";
    public static final String PRICE = "22.90";

    private HomePage homePage;

    private AddBookPage addBookPage;

    private SuccessPage successPage;

    @Before
    public void setUp() {

        this.homePage = webFactory.homePage();
        this.addBookPage = webFactory.addBookPage();
        this.successPage = webFactory.successPage();
    }

    @Test
    public void shouldOpenAddBookPage() {

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened();
    }


    @Test
    public void shouldCreateANewBook() {

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened()
                .save(BOOK_NAME, CATEGORY, YEAR, PRICE);

        successPage.assertIsOpened()
                .goAhead();

        homePage.assertIsOpened();
    }

    @Test
    public void shouldShowErrorIfNameIsEmpty() {

        final String empty_name = "";

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened()
                .save(empty_name, CATEGORY, YEAR, PRICE)
                .assertMandatoryNameError();
    }

    @Test
    public void shouldShowErrorIfPriceIsEmpty() {

        final String empty_price = "";

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened()
                .save(BOOK_NAME, CATEGORY, YEAR, empty_price)
                .assertMandatoryPriceError();
    }

    @Test
    public void shouldShowErrorIfPriceIsANegativeNumber() {

        final String name = "Effective Java";
        final String empty_price = "-1";

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened()
                .save(name, CATEGORY, YEAR, empty_price)
                .assertMandatoryPriceError();
    }

    @Test
    public void shouldShowErrorIfPriceIsNotANumber() {

        final String empty_price = "Zero";

        homePage.open()
                .assertIsOpened()
                .addBook();

        addBookPage.assertIsOpened()
                .save(BOOK_NAME, CATEGORY, YEAR, empty_price)
                .assertMandatoryPriceError();
    }
}
