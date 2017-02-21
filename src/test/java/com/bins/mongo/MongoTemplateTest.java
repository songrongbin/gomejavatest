package com.bins.mongo;

import com.bins.mongo.module.Customer;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by songrongbin on 2016/9/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class MongoTemplateTest {
    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void test(){
        DBObject object = new BasicDBObject();
        object.put("userName", 1111);
        object.put("password", 22222);
        mongoTemplate.insert(object, "user");
    }
}
