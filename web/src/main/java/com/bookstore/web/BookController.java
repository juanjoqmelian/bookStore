package com.bookstore.web;

import com.bookstore.web.form.BookForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @RequestMapping(value = "/showAdd")
    public String showAdd() {

        logger.debug("Redirecting to new book url...");

        return "redirect:add";
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {

        logger.debug("Redirecting to new book view...");

        model.addAttribute("bookForm", new BookForm());

        return "showAdd";
    }

    @RequestMapping(value = "/create")
    public String create(@ModelAttribute(value = "bookForm")BookForm bookForm, Model model) {

        logger.debug("Saving new book...");

        return "success";
    }
}
