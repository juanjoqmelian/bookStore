package com.bookstore.web;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class HomeControllerTest {

    private final HomeController homeController;

    private MockMvc mockMvc;
    private final Mockery mockery = new JUnit4Mockery();

    private BookService mockDefaultBookService;

    public HomeControllerTest() {

        homeController = new HomeController();
        mockDefaultBookService = mockery.mock(BookService.class);
        homeController.setDefaultBookService(mockDefaultBookService);
    }

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void home_shouldReturnHomeView() throws Exception {

        final List<Book> books = initializeBooksList();

        mockery.checking(new Expectations() {
            {
                oneOf(mockDefaultBookService).findAll();
                will(returnValue(books));
            }
        });

        mockMvc.perform(post("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
                .andExpect(MockMvcResultMatchers.model().attribute("books", hasSize(2)))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }


    private List<Book> initializeBooksList() {

        final List<Book> books = new ArrayList<Book>();
        Book book1 = new Book();
        book1.setName("Effective Java");
        book1.setCategory("Java");
        book1.setYear("2008");
        book1.setPrice("22.90");
        books.add(book1);

        Book book2 = new Book();
        book2.setName("Clean Code");
        book2.setCategory("Java");
        book2.setYear("2009");
        book2.setPrice("27.95");
        books.add(book2);
        return books;
    }
}
