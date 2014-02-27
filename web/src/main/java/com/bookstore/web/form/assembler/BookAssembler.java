package com.bookstore.web.form.assembler;


import com.bookstore.model.Book;
import com.bookstore.web.form.BookForm;

public class BookAssembler {

    public static final Book toBook(BookForm bookForm) {

        Book book = new Book();
        book.setName(bookForm.getName());
        book.setCategory(bookForm.getCategory());
        book.setYear(bookForm.getYear());
        book.setPrice(bookForm.getPrice());
        return book;
    }
}
