package com.bookstore.web;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ContextConfiguration(locations = {"classpath:context/servlet-context.xml","classpath:context/common-context.xml"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest {

    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        mockMvc =  webAppContextSetup(ctx).build();
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

    @Autowired
    public void setCtx(WebApplicationContext ctx) {
        this.ctx = ctx;
    }
}
