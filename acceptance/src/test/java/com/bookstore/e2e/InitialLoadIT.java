package com.bookstore.e2e;

import com.bookstore.e2e.page.HomePage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class InitialLoadIT extends AcceptanceTestEnvironment{

    private HomePage homePage;

    @Before
    public void setUp() {

        this.homePage = webFactory.homePage();
    }

    @Ignore
    @Test
    public void shouldOpenMainPage() {

        homePage.open()
                .assertIsOpened();
    }

}
