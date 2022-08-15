package com.eiit.presystemeiit.controller;

import com.eiit.presystemeiit.redis.RedisHelper;
import com.eiit.presystemeiit.utils.GetClientIpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@ApiIgnore
@Controller
public class GlobalController {

    @Autowired
    private RedisHelper redisHelper;

    private String client_ip;

    private Integer project_visits;

    @GetMapping("/")
    public String query(HttpServletRequest request) {

        client_ip = GetClientIpUtils.getIp(request);
        project_visits = (Integer) redisHelper.getValue("project_visits");

        if (project_visits == null) {
            project_visits = 0;
        } else {
            project_visits++;
        }

        System.out.println("来自: " + client_ip + " 的访问swagger文档, 当前访问总量为: " + project_visits);

        //更新访问量
        redisHelper.valuePut("project_visits", project_visits);

        //跳转swagger-ui
        return "redirect:/doc.html";
    }

    @GetMapping("/adv")
    public String toAdv(HttpServletRequest request) {
        return "redirect:index";
    }

}
