package com.eiit.presystemeiit;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/5 22:36
 * @description default
 */

import com.eiit.presystemeiit.model.Emp;
import com.eiit.presystemeiit.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class JdisTester {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void putRedisValueName(){
        redisTemplate.opsForValue().set("name", "zhangsan");
    }

    @Test
    public void getRedisValueName(){
        Object value = redisTemplate.opsForValue().get("name");
        System.out.println("value :\t" + value);
    }


}
