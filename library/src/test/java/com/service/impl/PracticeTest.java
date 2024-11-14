package com.service.impl;

import com.entity.BookInfoEntity;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class PracticeTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("name", "Tony", 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().increment("counter", 1);
        String name = (String) redisTemplate.opsForValue().get("name");
        Integer counter = (Integer) redisTemplate.opsForValue().get("counter");
        System.out.println("name: " + name);
        System.out.println("counter: " + counter);
//        List<String> strList  =  new ArrayList<String>();
//        strList.add("Tony");
//        strList.forEach(name->System.out.println(name));
    }

    @Test
    public void test2(){
        BookInfoEntity bookInfoEntity = new BookInfoEntity();
        bookInfoEntity.setBookName("TonyBook");
        bookInfoEntity.setBookId("Tony1");
        bookInfoEntity.setStatus(1);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(bookInfoEntity);
        System.out.println(jsonStr);
    }

    @Test
    public void test3(){
        String jsonString = "{\"bookname\":\"Tonybook\",\"bookId\":\"Tony1\",\"status\":1}";
        Gson gson = new Gson();
        BookInfoEntity bookInfoEntity = gson.fromJson(jsonString,BookInfoEntity.class);
        System.out.println(bookInfoEntity);
    }

    public int recursion(int n){
        if(n==1){
            return 1;
        }

        return n*recursion(n-1);
    }

    @Test
    public void testRecursion(){
        System.out.println("recursion : "+recursion(5));
    }
}
