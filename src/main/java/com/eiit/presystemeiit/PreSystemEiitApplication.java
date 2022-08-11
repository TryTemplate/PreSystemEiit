package com.eiit.presystemeiit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.eiit.presystemeiit.mapper")
public class PreSystemEiitApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreSystemEiitApplication.class, args);
    }

}
