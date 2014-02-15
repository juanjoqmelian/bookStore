package com.bookstore.e2e;

import com.bookstore.e2e.page.HomePage;
import org.junit.Before;
import org.junit.Test;

public class InitialLoadIT extends AcceptanceTestEnvironment{

    private HomePage homePage;

    @Before
    public void setUp() {

        this.homePage = webFactory.homePage();
    }

    @Test
    public void shouldOpenMainPage() {

        homePage.open()
                .assertIsOpened();
    }

}
