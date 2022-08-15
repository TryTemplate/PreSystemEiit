package com.eiit.presystemeiit.controllermvc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/10 17:57
 * @description 部门操作controller
 */

@Api(tags = "【Redis】【MVC API】")
@Controller
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @ApiOperation(value = "【redis】【设置一个缓存】", notes = "输入key, value 进行缓存", produces="application/json", position = 1)
    @RequestMapping("/set")
    public String setRedisValue(Model model, String key, String value){
        redisTemplate.opsForValue().set(key, value);
        return "/redis/redis";
    }
    @ApiOperation(value = "【redis】【获取内容】", notes = "输入key, 获取内容", produces="application/json", position = 2)
    @RequestMapping("/get")
    public String getRedisValue(Model model, String key){
        String value = stringRedisTemplate.opsForValue().get(key);
        model.addAttribute("value", value);
        return "/redis/redis";
    }
    @ApiOperation(value = "【redis】【删除缓存】", notes = "输入key, 删除缓存", produces="application/json", position = 3)
    @RequestMapping("/remove")
    public String removeRedisValue(Model model, String key){
        String value = stringRedisTemplate.opsForValue().getAndDelete(key);
        model.addAttribute("value", value);
        return "/redis/redis";
    }
    @ApiOperation(value = "【redis】【获取所有key】", notes = "获取所有key", produces="application/json", position = 4)
    @RequestMapping("/keys")
    public String keysRedisValue(Model model){
        Set<String> value = stringRedisTemplate.keys("*");
        model.addAttribute("value", value);
        return "/redis/rediss";
    }



}
