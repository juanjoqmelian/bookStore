package com.bookstore.web;

import com.bookstore.service.BookService;
import com.bookstore.service.exception.BookNameAlreadyExistsException;
import com.bookstore.service.exception.BookNotFoundException;
import com.bookstore.web.form.BookForm;
import com.bookstore.web.form.assembler.BookAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private BookService defaultBookService;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "bookForm") @Valid BookForm bookForm, BindingResult result) throws BookNameAlreadyExistsException {

        logger.debug("Saving new book...");

        if (result.hasErrors()) {
           return "showAdd";
        }

        defaultBookService.insert(BookAssembler.toBook(bookForm));

        return "success";
    }

    @RequestMapping(value = "/showEdit/{id}")
    public String showEdit(@PathVariable(value = "id") String id) {

        logger.debug("Redirecting to edit url...");

        return "redirect:/book/edit/"+id+"/";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") String id, Model model) {

        logger.debug("Redirecting to edit view...");

        BookForm bookForm = new BookForm();
        bookForm.setId(id);
        model.addAttribute(bookForm);

        return "showEdit";
    }

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute(value = "bookForm") @Valid BookForm bookForm, BindingResult result) throws BookNotFoundException {

        logger.debug("Updating book...");

        if (result.hasErrors()) {
            return "showEdit";
        }

        defaultBookService.update(BookAssembler.toBook(bookForm));

        return "success";
    }


    @Autowired
    public void setDefaultBookService(BookService defaultBookService) {
        this.defaultBookService = defaultBookService;
    }
}
