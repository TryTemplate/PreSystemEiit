package com.eiit.presystemeiit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.eiit.presystemeiit.mapper")
public class PreSystemEiitApplication {


    /**
     * 输出启动加载的类 bean **
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


    public static void main(String[] args) {
        SpringApplication.run(PreSystemEiitApplication.class, args);
    }

}
