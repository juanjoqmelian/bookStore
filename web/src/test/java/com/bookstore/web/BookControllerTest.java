package com.bookstore.web;


import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.exception.BookNameAlreadyExistsException;
import com.bookstore.web.form.BookForm;
import com.bookstore.web.form.assembler.BookAssembler;
import com.google.gson.Gson;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration(locations = {"classpath:context/servlet-context.xml","classpath:context/common-context.xml"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest {

    private static final String NAME = "Effective Java";
    private static final String CATEGORY = "Java";
    private static final String YEAR = "2008";
    private static final String PRICE = "32.90";

    private MockMvc mockMvc;
    private final Mockery mockery = new JUnit4Mockery();

    private final BookController bookController;
    private BookService mockDefaultBookService;

    public BookControllerTest() {

        bookController = new BookController();
        mockDefaultBookService = mockery.mock(BookService.class);
        bookController.setDefaultBookService(mockDefaultBookService);
    }

    @Before
    public void setUp() throws BookNameAlreadyExistsException {

        mockMvc =  MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void showAdd_shouldRedirectToAddUrl() throws Exception {

        mockMvc.perform(post("/book/showAdd"))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("add"));
    }

    @Test
    public void add_shouldRedirectToShowAddView() throws Exception {

        mockMvc.perform(post("/book/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bookForm"))
                .andExpect(MockMvcResultMatchers.view().name("showAdd"));
    }

    @Ignore
    @Test
    public void create_shouldCreateANewBookAndShowSuccessView() throws Exception {

        final BookForm bookForm = getBookForm();

        final Book book = BookAssembler.toBook(bookForm);

        String bookJson = new Gson().toJson(bookForm);

        mockery.checking(new Expectations() {
            {
                oneOf(mockDefaultBookService).insert(book);
                will(returnValue(book));
            }
        });

        mockMvc.perform(post("/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("bookForm", bookForm))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("success"));
    }

    @Test
    public void create_shouldDetectErrorInFormAndRedirectToShowAddView() throws Exception {

        final BookForm bookForm = new BookForm();

        String bookJson = new Gson().toJson(bookForm);

        mockMvc.perform(post("/book/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("bookForm"))
                .andExpect(MockMvcResultMatchers.view().name("showAdd"));
    }

    @Test
    public void showEdit_shouldRedirectToEditUrl() throws Exception {

        String id = "3423423";

        String showEditUrl = String.format("/book/showEdit/%s", id);

        String editRedirectUrl = String.format("/book/edit/%s/", id);

        mockMvc.perform(post(showEditUrl))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl(editRedirectUrl));
    }

    @Test
    public void edit_shouldForwardToShowEditView() throws Exception {

        String id = "3423423";

        String editUrl = String.format("/book/edit/%s/", id);

        mockMvc.perform(post(editUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bookForm"))
                .andExpect(MockMvcResultMatchers.view().name("showEdit"));
    }

    @Ignore
    @Test
    public void update_shouldUpdateABookAndShowSuccessView() throws Exception {

        final BookForm bookForm = getBookForm();

        final Book book = BookAssembler.toBook(bookForm);

        String bookJson = new Gson().toJson(bookForm);

        mockery.checking(new Expectations() {
            {
                oneOf(mockDefaultBookService).update(book);
            }
        });

        mockMvc.perform(post("/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("success"));
    }

    @Test
    public void update_shouldDetectErrorAndRedirectToShowEditView() throws Exception {

        final BookForm bookForm = new BookForm();

        String bookJson = new Gson().toJson(bookForm);

        mockMvc.perform(post("/book/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("bookForm"))
                .andExpect(MockMvcResultMatchers.view().name("showEdit"));
    }

    private BookForm getBookForm() {

        final BookForm bookForm = new BookForm();
        bookForm.setName(NAME);
        bookForm.setCategory(CATEGORY);
        bookForm.setYear(YEAR);
        bookForm.setPrice(PRICE);
        return bookForm;
    }
}
