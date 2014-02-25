package com.bookstore.service;


import com.bookstore.model.Book;
import com.bookstore.service.exception.BookNameAlreadyExistsException;
import com.bookstore.service.exception.BookNotFoundException;


public interface BookService {

    Book insert(Book book) throws BookNameAlreadyExistsException;

    Book findByName(String name) throws BookNotFoundException;

    void update(Book book) throws BookNotFoundException;
}
