package com.eiit.presystemeiit;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.eiit.presystemeiit.mapper")
public class PreSystemEiitApplication {


    /**
     * 利用监听器实现
     * 输出启动加载的类 bean **
     *
     * @Author liujingguang
     *
     * @param ctx
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Welcome user system, Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println("---------- System spring load bean :\t" + beanName);
            }

        };
    }

    //单机redisson配置
    //Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("password..").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }


    public static void main(String[] args) {
        SpringApplication.run(PreSystemEiitApplication.class, args);
    }

}
