package com.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

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
}
