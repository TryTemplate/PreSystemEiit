package com.eiit.presystemeiit.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 配置memcached缓存
 *
 * @Date    2022年4月10日14:02:14
 * @Author  Liujingguang
 */

//@Configuration
public class MemCachedConfig {

    @Value(value = "${base.mem.location}")
    private String ip;

    @Value(value = "${base.mem.prot}")
    private Integer prot;

    @Bean
    public MemcachedClient getMemCached(){

        MemcachedClient memcachedClient = null;

        try {
            memcachedClient = new XMemcachedClient(new InetSocketAddress(ip, prot));
        }catch (IOException e){
            e.printStackTrace();
        }

        return memcachedClient;
    }
}
