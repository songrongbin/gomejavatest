package com.bins.mongo;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.util.JSON;
import org.bson.Document;

import java.rmi.UnknownHostException;
import java.util.Collection;

/**
 * Created by songrongbin on 2016/9/30.
 */
public class SimpleTest {
    public static void main(String[] args) throws UnknownHostException, MongoException {
        MongoClient mongoClient = new MongoClient("192.168.17.129", 27017);
        //查询所有的Database
        for (String db : mongoClient.listDatabaseNames()) {
            System.out.println("dbName: " + db);
        }

        MongoDatabase db = mongoClient.getDatabase("space");
        //查询所有的聚集集合
        for (String name : db.listCollectionNames()) {
            System.out.println("collectionName: " + name);
        }

        MongoCollection<Document> users = db.getCollection("users");

        //查询所有的数据
        ListIndexesIterable<Document> documents = users.listIndexes();

        FindIterable<Document> document = users.find();
        System.out.println(document.first());


    }

}
