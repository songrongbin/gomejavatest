package com.bins.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by songrongbin on 2016/9/30.
 */
public class MongoDbConnection {
    public static void main(String[] args) {
        String myUserName = "admin";
        String myPassword = "admin";
        MongoClient mongoClient = new MongoClient("192.168.17.129", 27017);

        // 1.数据库列表
        for (String s : mongoClient.listDatabaseNames()) {
            System.out.println("DatabaseName=" + s);
        }

        // 2.链接student数据库
        MongoDatabase db = mongoClient.getDatabase("space");

        // 4.集合列表
        ListCollectionsIterable<Document> collections = db.listCollections();
        for (Document document : collections) {
            System.out.println("CollectionName=" + document.toJson());
        }

        // 5.获取摸个集合对象
        MongoCollection<Document> collection = db.getCollection("suggestion");
        System.out.println("CollectionName=" + collection.getWriteConcern());
        System.out.println("CollectionName=" + collection.find());
    }
}
