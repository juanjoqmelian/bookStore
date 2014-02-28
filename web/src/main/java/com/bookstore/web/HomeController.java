package com.bookstore.web;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private BookService defaultBookService;

    @RequestMapping
    public String home(Model model) {

        logger.debug("Entering home page...");

        List<Book> books = defaultBookService.findAll();

        model.addAttribute("books", books);

        return "home";
    }


    @Autowired
    public void setDefaultBookService(BookService defaultBookService) {
        this.defaultBookService = defaultBookService;
    }
}
