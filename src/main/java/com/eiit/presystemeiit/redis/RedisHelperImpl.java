package com.eiit.presystemeiit.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("RedisHelper")
public class RedisHelperImpl<HK, T> implements RedisHelper<HK, T> {

    // 在构造器中获取redisTemplate实例, key(not hashKey) 默认使用String类型
    private final RedisTemplate<String, T> redisTemplate;

    // 在构造器中通过redisTemplate的工厂方法实例化操作对象
    private final HashOperations<String, HK, T> hashOperations;
    private final ListOperations<String, T> listOperations;
    private final ZSetOperations<String, T> zSetOperations;
    private final SetOperations<String, T> setOperations;
    private final ValueOperations<String, T> valueOperations;

    // IDEA虽然报错,但是依然可以注入成功, 实例化操作对象后就可以直接调用方法操作Redis数据库
    @Autowired
    public RedisHelperImpl(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.zSetOperations = redisTemplate.opsForZSet();
        this.setOperations = redisTemplate.opsForSet();
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void hashPut(String key, HK hashKey, T domain) {
        hashOperations.put(key, hashKey, domain);
    }

    public boolean hSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set<String> getFolderKeys(String folderKey){
        return redisTemplate.keys(folderKey + ":*");
    }

    public Set<String> getAccountKeys(String account){
        return redisTemplate.keys("*" + account);
    }

    @Override
    public Map<HK, T> hashFindAll(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public T hashGet(String key, HK hashKey) {
        return hashOperations.get(key, hashKey);
    }

    @Override
    public void hashRemove(String key, HK hashKey) {
        hashOperations.delete(key, hashKey);
    }

    @Override
    public Long listPush(String key, T domain) {
        return listOperations.rightPush(key, domain);
    }

    @Override
    public Long listUnshift(String key, T domain) {
        return listOperations.leftPush(key, domain);
    }

    @Override
    public List<T> listFindAll(String key) {
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        return listOperations.range(key, 0, listOperations.size(key));
    }


    @Override
    public List<T> listFindByPage(String key, int start, int end) {
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        return listOperations.range(key, start, end);
    }

    @Override
    public Long listFindSizeByKey(String key) {
        return listOperations.size(key);
    }

    @Override
    public T listLPop(String key) {
        return listOperations.leftPop(key);
    }

    @Override
    public void valuePut(String key, T domain) {
        valueOperations.set(key, domain);
    }

    /**
     * 根据key获取Integer类型的值
     *
     * @auther liujingguang
     * @param key
     * @return Intget value
     */
    public Integer getInteger(String key) {
        Integer v = null;
        Object o = valueOperations.get(key);
        if (o != null) {
            try {
                v = Integer.valueOf(o.toString());
            }catch (Exception e){
                return v;
            }
        }
        return v;
    }

    /**
     * 如果没有就创建 有则更新时间
     *
     *
     * @param key       key
     * @param domain    存储的数据
     * @param timeout   过期时间 单位(秒)
     * @param isFresh   是否强刷新数据(无论有没有都以后存入的为准)
     */
    public void valuePut(String key, T domain, Long timeout, boolean isFresh) {
        T t = valueOperations.get(key);

        if (isFresh) {
            remove(key);
            valueOperations.set(key, domain, timeout, TimeUnit.SECONDS);
            return;
        }

        if (t!=null){
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }else{
            valueOperations.set(key, domain, timeout, TimeUnit.SECONDS);
        }
    }



    @Override
    public T getValue(String key) {
        return valueOperations.get(key);
    }
    /**
     * 查询并重设失效时间
     *
     * @param key
     * @param timeout
     * @return
     */
    public T getValue(String key, long timeout) {
        T t = valueOperations.get(key);
        if (t!=null){
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return t;
    }
    @Override
    public T getValueAndRemove(String key) {

        T o = valueOperations.get(key);

        if (o!=null)
            redisTemplate.delete(key);

        return o;
    }



    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }


    /**
     * 根据key删除存储对象 并返回删除行数
     *
     * @auther liujingguang
     * @param key
     * @return Intget v 受影响行数
     */
    public Integer delObject(String key) {
        Integer v = 0;
        boolean status = redisTemplate.delete(key);
        if (status) {
            try {
                v++;
            }catch (Exception e){
                return v;
            }
        }
        return v;
    }


    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public RedisTemplate<String, T> getRedisTemplate() {
        return redisTemplate;
    }

    public HashOperations<String, HK, T> getHashOperations() {
        return hashOperations;
    }

    public ListOperations<String, T> getListOperations() {
        return listOperations;
    }

    public ZSetOperations<String, T> getzSetOperations() {
        return zSetOperations;
    }

    public SetOperations<String, T> getSetOperations() {
        return setOperations;
    }

    public ValueOperations<String, T> getValueOperations() {
        return valueOperations;
    }
}
