package com.bookstore.web.form.assembler;


import com.bookstore.model.Book;
import com.bookstore.web.form.BookForm;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BookAssemblerTest {

    private static final String NAME = "Effective Java";
    private static final String CATEGORY = "Java";
    private static final String YEAR = "2009";
    private static final String PRICE = "32.90";

    @Test
    public void toBook_shouldConvertBookFormToBook() {

        BookForm bookForm = initializeBookForm();

        Book book = BookAssembler.toBook(bookForm);

        assertThat(book, is(notNullValue()));
        assertThat(book.getName(), is(bookForm.getName()));
        assertThat(book.getCategory(), is(bookForm.getCategory()));
        assertThat(book.getYear(), is(bookForm.getYear()));
        assertThat(book.getPrice(), is(bookForm.getPrice()));
    }


    private BookForm initializeBookForm() {

        BookForm bookForm = new BookForm();
        bookForm.setName(NAME);
        bookForm.setCategory(CATEGORY);
        bookForm.setYear(YEAR);
        bookForm.setPrice(PRICE);
        return bookForm;
    }
}
