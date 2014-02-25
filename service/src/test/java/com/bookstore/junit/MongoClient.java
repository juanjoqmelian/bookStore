package com.bookstore.junit;

import com.mongodb.Mongo;
import org.junit.rules.ExternalResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.awt.print.Book;

public class MongoClient extends ExternalResource{

    private MongoTemplate mongoTemplate;

    private static final String DATABASE_NAME = "test";

    @Override
    protected void before() throws Throwable {

        this.mongoTemplate = new MongoTemplate(new Mongo(MongoServer.HOST, MongoServer.PORT), DATABASE_NAME);
        this.dropCollections();
    }

    public <T> T findOne(Query query, Class<T> entityClass) {
        return this.mongoTemplate.findOne(query, entityClass);
    }

    public void save(Object objectToSave) {
        this.mongoTemplate.save(objectToSave);
    }

    public void dropCollections() {
        this.mongoTemplate.dropCollection(Book.class);
    }
}
