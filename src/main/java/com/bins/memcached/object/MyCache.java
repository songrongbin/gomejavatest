package com.bins.memcached.object;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Date;

  
public class MyCache {  
    public static void main(String[] args) {  
        MemCachedClient client=new MemCachedClient();
        String [] addr ={"192.168.17.129:11211"};
        Integer [] weights = {3};  
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(addr);  
        pool.setWeights(weights);  
        pool.setInitConn(5);  
        pool.setMinConn(5);  
        pool.setMaxConn(200);  
        pool.setMaxIdle(1000*30*30);  
        pool.setMaintSleep(30);  
        pool.setNagle(false);  
        pool.setSocketTO(30);  
        pool.setSocketConnectTO(0);  
        pool.initialize();  
          
//      String [] s  =pool.getServers();  
        //client.setCompressEnable(true);
        //client.setCompressThreshold(1000*1024);
          
//      将数据放入缓存  
        TestBean bean=new TestBean();  
        bean.setName("name1");  
        bean.setAge(25);  
        client.add("bean1", bean);  
          
//      获取缓存数据  
        TestBean beanClient=(TestBean)client.get("bean1");  
        System.out.println(beanClient.getName());  
    }  
}