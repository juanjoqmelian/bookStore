package com.bookstore.service;


import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.exception.BookNameAlreadyExistsException;
import com.bookstore.service.exception.BookNotFoundException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DefaultBookServiceTest {

    private static final String BOOK_NAME = "Effective Java";

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private final Mockery mockery = new JUnit4Mockery();

    private final DefaultBookService defaultBookService;

    private final BookRepository mockBookRepository;


    public DefaultBookServiceTest() {

        defaultBookService = new DefaultBookService();
        mockBookRepository = mockery.mock(BookRepository.class);
        defaultBookService.setBookRepository(mockBookRepository);
    }

    @Test
    public void insert_shouldCreateANewBook() throws BookNameAlreadyExistsException {

        final Book book = getBook();

        mockery.checking(new Expectations() {
            {
                oneOf(mockBookRepository).findByName(BOOK_NAME);
                will(returnValue(null));

                oneOf(mockBookRepository).save(book);
                will(returnValue(book));
            }
        });


        Book newBook = defaultBookService.insert(book);
        assertThat(newBook, is(notNullValue()));
        assertThat(newBook.getName(), is(book.getName()));
    }


    @Test
    public void insert_shouldTrowBookNameAlreadyExistsException() throws BookNameAlreadyExistsException {

        final Book book = getBook();

        mockery.checking(new Expectations() {
            {
                oneOf(mockBookRepository).findByName(BOOK_NAME);
                will(returnValue(book));
            }
        });

        expectedException.expect(BookNameAlreadyExistsException.class);

        defaultBookService.insert(book);
    }

    @Test
    public void findAll_shouldReturnAnEmptyList() {

        mockery.checking(new Expectations() {
            {
                oneOf(mockBookRepository).findAll();
                will(returnValue(Collections.emptyList()));
            }
        });

        List<Book> books = defaultBookService.findAll();
        assertThat(books.isEmpty(), is(true));
    }

    @Test
    public void findAll_shouldReturnAListWithOneElement() {

        final List<Book> books = new ArrayList<Book>();
        books.add(getBook());

        mockery.checking(new Expectations() {
            {
                oneOf(mockBookRepository).findAll();
                will(returnValue(books));
            }
        });

        List<Book> returnedBooks = defaultBookService.findAll();
        assertThat(returnedBooks.size(), is(1));
    }

    @Test
    public void findByName_shouldReturnABook() throws BookNotFoundException {

        final Book book = getBook();

        mockery.checking(new Expectations() {
            {
                oneOf(mockBookRepository).findByName(BOOK_NAME);
                will(returnValue(book));
            }
        });

        Book returnedBook = defaultBookService.findByName(BOOK_NAME);
        assertThat(returnedBook, is(notNullValue()));
        assertThat(returnedBook.getName(), is(book.getName()));
    }

    @Test
    public void findByName_shouldTrowBookNotFoundException() throws BookNotFoundException {

        mockery.checking(new Expectations() {
            {
                oneOf(mockBookRepository).findByName(BOOK_NAME);
                will(returnValue(null));
            }
        });

        expectedException.expect(BookNotFoundException.class);

        defaultBookService.findByName(BOOK_NAME);
    }

    @Test
    public void update_shouldUpdateABook() throws BookNotFoundException {

        final Book book = getBook();


        mockery.checking(new Expectations() {
            {
                oneOf(mockBookRepository).findByName(BOOK_NAME);
                will(returnValue(book));

                oneOf(mockBookRepository).save(book);
            }
        });

        defaultBookService.update(book);
    }

    @Test
    public void update_shouldThrowBookNotFoundException() throws BookNotFoundException {

        final Book book = getBook();

        mockery.checking(new Expectations() {
            {
                oneOf(mockBookRepository).findByName(BOOK_NAME);
                will(returnValue(null));
            }
        });

        expectedException.expect(BookNotFoundException.class);

        defaultBookService.update(book);
    }


    private Book getBook() {

        final Book book = new Book();
        book.setName(BOOK_NAME);
        return book;
    }
}
