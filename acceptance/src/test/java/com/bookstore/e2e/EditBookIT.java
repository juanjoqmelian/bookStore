package com.bookstore.e2e;

import com.bookstore.e2e.page.EditBookPage;
import com.bookstore.e2e.page.HomePage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class EditBookIT extends AcceptanceTestEnvironment{

    private HomePage homePage;

    private EditBookPage editBookPage;

    @Before
    public void setUp() {

        this.homePage = webFactory.homePage();
        this.editBookPage = webFactory.editBookPage();
    }

    @Ignore
    @Test
    public void shouldEditBook() {

        final String name = "Effective Java";
        final String category = "Java";
        final String year = "2000";
        final String price = "22.90";

        homePage.open()
                .assertIsOpened()
                .editBook(0);

        editBookPage.assertIsOpened()
                .edit(name, category, year, price);

        homePage.assertIsOpened()
                .assertAddedBookSuccessMessage();
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
                .editBook(0);

        editBookPage.assertIsOpened()
                .edit(empty_name, category, year, price)
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
                .editBook(0);

        editBookPage.assertIsOpened()
                .edit(name, category, year, empty_price)
                .assertMandatoryPriceError();
    }

}
