package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.exception.BookNameAlreadyExistsException;
import com.bookstore.service.exception.BookNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("defaultBookService")
public class DefaultBookService implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultBookService.class);


    private BookRepository bookRepository;


    @Override
    public Book insert(Book book) throws BookNameAlreadyExistsException {

        Book existingBook = bookRepository.findByName(book.getName());

        if (existingBook != null) {

            throw new BookNameAlreadyExistsException("Book name already exists!");
        }

        Book newBook = bookRepository.save(book);

        return newBook;
    }

    @Override
    public Book findByName(String name) throws BookNotFoundException {

        Book book = bookRepository.findByName(name);

        if (book == null) {
            throw new BookNotFoundException("Book does not exist!");
        }

        return book;
    }

    @Override
    public void update(Book book) throws BookNotFoundException {

        Book retrievedBook = bookRepository.findByName(book.getName());

        if (retrievedBook == null) {

            throw new BookNotFoundException("Book does nor exist!");
        }

        retrievedBook.setPrice(book.getPrice());
        retrievedBook.setCategory(book.getCategory());
        retrievedBook.setYear(book.getYear());

        bookRepository.save(retrievedBook);

        logger.debug("Book has been updated!");
    }


    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
