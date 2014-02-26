package com.bookstore.e2e.mongo;

import com.mongodb.Mongo;
import org.junit.rules.ExternalResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.awt.print.Book;

public class MongoClient extends ExternalResource {

    private MongoTemplate mongoTemplate;

    private static final String HOST = "localhost";

    private static final int PORT = 27017;

    private static final String DATABASE_NAME = "database";


    @Override
    protected void before() throws Throwable {

        this.mongoTemplate = new MongoTemplate(new Mongo(HOST, PORT), DATABASE_NAME);
        this.dropCollections();
    }

    public void dropCollections() {
        this.mongoTemplate.dropCollection(Book.class);
    }

    public void save(Object s) {
        this.mongoTemplate.save(s);
    }
}
