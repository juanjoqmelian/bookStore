package com.bookstore.service;


import com.bookstore.junit.IntegrationTest;
import com.bookstore.model.Book;
import com.bookstore.service.exception.BookNameAlreadyExistsException;
import com.bookstore.service.exception.BookNotFoundException;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
       "classpath:service-context.xml",
        "classpath:test-mongodb-context.xml"
})
public class DefaultBookServiceIT extends IntegrationTest {

    private BookService bookService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void create_shouldCreateANewBook() throws BookNameAlreadyExistsException {

        Book book = initializeBook();

        Book newBook = bookService.insert(book);

        assertThat(newBook, is(notNullValue()));
    }

    @Test
    public void create_shouldThrowBookNameAlreadyExistsException() throws BookNameAlreadyExistsException {

        Book book = initializeBook();

        mongoClient.save(book);

        Book book2 = initializeBook();

        expectedException.expect(BookNameAlreadyExistsException.class);

        Book newBook = bookService.insert(book2);
    }

    @Test
    public void findAll_shouldReturnAnEmptyListOfBooks() {

        List<Book> books = bookService.findAll();

        assertThat(books.isEmpty(), is(true));
    }

    @Test
    public void findAll_shouldReturnAListWithABook() {

        Book book = initializeBook();

        mongoClient.save(book);

        List<Book> books = bookService.findAll();

        assertThat(books.size(), is(1));
    }

    @Test
    public void findAll_shouldReturnAListWithTwoBooks() {

        Book book = initializeBook();

        mongoClient.save(book);

        Book book2 = initializeBook();
        book2.setName("Different book");

        mongoClient.save(book2);

        List<Book> books = bookService.findAll();

        assertThat(books.size(), is(2));
    }

    @Test
    public void findByName_shouldRetrieveABook() throws BookNotFoundException {

        Book book = initializeBook();

        mongoClient.save(book);

        Book retrievedBook = bookService.findByName(book.getName());

        assertThat(retrievedBook.getName(), is(book.getName()));
        assertThat(retrievedBook, is(notNullValue()));
    }

    @Test
    public void findByName_shouldThrowBookNotFoundException() throws BookNotFoundException {

        final String fakeName = "Fake book";

        expectedException.expect(BookNotFoundException.class);

        Book retrievedBook = bookService.findByName(fakeName);
    }

    @Ignore
    @Test
    public void update_shouldUpdateABook() throws BookNotFoundException {

        final String newPrice = "35.90";
        final String newYear = "2013";

        Book book = initializeBook();

        mongoClient.save(book);

        Book retrievedBook = bookService.findByName(book.getName());

        retrievedBook.setPrice(newPrice);
        retrievedBook.setYear(newYear);

        bookService.update(retrievedBook);

        Book updatedBook = bookService.findByName(retrievedBook.getName());

        assertThat(updatedBook.getName(), is(retrievedBook.getName()));
        assertThat(updatedBook.getPrice(), is(newPrice));
        assertThat(updatedBook.getYear(), is(newYear));
    }

    @Test
    public void update_shouldThrowBookNotFoundException() throws BookNotFoundException {

        final String newPrice = "35.90";
        final String newYear = "2013";

        expectedException.expect(BookNotFoundException.class);

        Book book = initializeBook();

        book.setPrice(newPrice);
        book.setYear(newYear);

        bookService.update(book);
    }

    private Book initializeBook() {
        Book book = new Book();
        book.setName("Effective Java");
        book.setCategory("Java");
        book.setYear("2012");
        book.setPrice("21.90");
        return book;
    }



    @Autowired
    public void setBookService(DefaultBookService bookService) {
        this.bookService = bookService;
    }
}
