package com.bookstore.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class HomeControllerTest {

    private final HomeController homeController;

    private MockMvc mockMvc;

    public HomeControllerTest() {

        homeController = new HomeController();
    }

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void home_shouldReturnHomeView() throws Exception {

        mockMvc.perform(post("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }
}
