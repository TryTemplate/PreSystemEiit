package com.eiit.presystemeiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/10 17:57
 * @description 部门操作controller
 */

@Controller
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/set")
    public String setRedisValue(Model model, String key, String value){
        redisTemplate.opsForValue().set(key, value);
        return "/redis/redis";
    }
    @RequestMapping("/get")
    public String getRedisValue(Model model, String key){
        String value = stringRedisTemplate.opsForValue().get(key);
        model.addAttribute("value", value);
        return "/redis/redis";
    }
    @RequestMapping("/remove")
    public String removeRedisValue(Model model, String key){
        String value = stringRedisTemplate.opsForValue().getAndDelete(key);
        model.addAttribute("value", value);
        return "/redis/redis";
    }
    @RequestMapping("/keys")
    public String keysRedisValue(Model model){
        Set<String> value = stringRedisTemplate.keys("*");
        model.addAttribute("value", value);
        return "/redis/rediss";
    }



}
