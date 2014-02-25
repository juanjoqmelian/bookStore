package com.bookstore.junit;

import org.junit.Rule;

public abstract class IntegrationTest {

    @Rule
    public MongoClient mongoClient = new MongoClient();
}
