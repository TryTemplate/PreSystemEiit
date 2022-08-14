package com.eiit.presystemeiit.config;

import com.eiit.presystemeiit.netty.NettyServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NettyStartListener implements ApplicationRunner {

    @Resource
    private NettyServer socketServer;

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        this.socketServer.start();
    }
}