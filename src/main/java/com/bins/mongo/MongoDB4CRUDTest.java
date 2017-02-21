package com.bins.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by songrongbin on 2016/9/30.
 */
public class MongoDB4CRUDTest {
    private Mongo mg = null;
    private MongoDatabase db;
    private MongoCollection<Document> users;
    MongoClient mongoClient = null;

    @Before
    public void init() {
        try {
            mongoClient = new MongoClient("192.168.17.129", 27017);
        } catch (MongoException e) {
            e.printStackTrace();
        }
        //获取temp DB；如果默认没有创建，mongodb会自动创建
        db = mongoClient.getDatabase("space");
        //获取users DBCollection；如果默认没有创建，mongodb会自动创建
        users = db.getCollection("users");
    }

    @After
    public void destory() {
        if (mg != null)
            mg.close();
        mg = null;
        db = null;
        users = null;
        System.gc();
    }

    @Test
    public void print() {
        System.out.println();
    }

}
