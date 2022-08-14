package com.eiit.presystemeiit.utils;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeoutException;

//@Component
public class MemcachedUtil {

    @Autowired
    private MemcachedClient memcachedClient;

    public boolean pull(String key, Object o) throws InterruptedException, TimeoutException, MemcachedException {
        return memcachedClient.add(key, 360000, o);
    }

    public Object get(String key) throws InterruptedException, TimeoutException, MemcachedException {
        return memcachedClient.get(key);
    }

    public boolean remove(String key) throws InterruptedException, TimeoutException, MemcachedException {
        return memcachedClient.delete(key);
    }

}
