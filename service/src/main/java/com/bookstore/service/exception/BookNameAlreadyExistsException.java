package com.bookstore.service.exception;

public class BookNameAlreadyExistsException extends Exception {

    public BookNameAlreadyExistsException(String message) {
        super(message);
    }
}
