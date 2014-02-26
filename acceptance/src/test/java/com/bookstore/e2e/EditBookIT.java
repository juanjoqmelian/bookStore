package com.bookstore.e2e;

import com.bookstore.e2e.page.EditBookPage;
import com.bookstore.e2e.page.HomePage;
import com.bookstore.e2e.page.SuccessPage;
import com.bookstore.model.Book;
import org.junit.Before;
import org.junit.Test;

public class EditBookIT extends AcceptanceTestEnvironment{

    private static final String NAME = "Effective Java";
    private static final String PRICE = "22.90";
    private static final String YEAR = "2000";
    private static final String CATEGORY = "Java";

    private HomePage homePage;

    private EditBookPage editBookPage;
    private SuccessPage successPage;

    @Before
    public void setUp() {

        this.homePage = webFactory.homePage();
        this.editBookPage = webFactory.editBookPage();
        this.successPage = webFactory.successPage();
    }

    @Test
    public void shouldOpenEditPage() {

        homePage.open()
                .assertIsOpened()
                .editBook(0);

        editBookPage.assertIsOpened();
    }

    @Test
    public void shouldEditBook() {


        Book book = new Book();
        book.setName(NAME);

        mongoClient.save(book);

        homePage.open()
                .assertIsOpened()
                .editBook(0);

        editBookPage.assertIsOpened()
                .edit(NAME, CATEGORY, YEAR, PRICE);

        successPage.assertIsOpened()
                .goAhead();

        homePage.assertIsOpened();
    }

    @Test
    public void shouldShowErrorIfNameIsEmpty() {

        final String empty_name = "";

        homePage.open()
                .assertIsOpened()
                .editBook(0);

        editBookPage.assertIsOpened()
                .edit(empty_name, CATEGORY, YEAR, PRICE)
                .assertMandatoryNameError();
    }


    @Test
    public void shouldShowErrorIfPriceIsEmpty() {

        final String empty_price = "";

        homePage.open()
                .assertIsOpened()
                .editBook(0);

        editBookPage.assertIsOpened()
                .edit(NAME, CATEGORY, YEAR, empty_price)
                .assertMandatoryPriceError();
    }

}
