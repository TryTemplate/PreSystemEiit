package com.eiit.presystemeiit.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * K 指以hash结构操作时 键类型
 * T 为数据实体 应实现序列化接口,并定义serialVersionUID * RedisTemplate 提供了五种数据结构操作类型 hash / list / set / zset / value
 * 方法命名格式为 数据操作类型 + 操作 如 hashPut 指以hash结构(也就是map)想key添加键值对
 */
public interface RedisHelper<HK, T> {
    /**
     * Hash结构 添加元素 * @param key key * @param hashKey hashKey * @param domain 元素
     */
    void hashPut(String key, HK hashKey, T domain);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    boolean hSet(String key, String item, Object value, long time);

    /**
     * 获取所有的keys
     *
     * @param folderKey
     * @return
     */
    Set<String> getFolderKeys(String folderKey);

    /**
     * Hash结构 获取指定key所有键值对 * @param key * @return
     */
    Map<HK, T> hashFindAll(String key);

    /**
     * Hash结构 获取单个元素 * @param key * @param hashKey * @return
     */
    T hashGet(String key, HK hashKey);

    void hashRemove(String key, HK hashKey);

    /**
     * List结构 向尾部(Right)添加元素 * @param key * @param domain * @return
     */
    Long listPush(String key, T domain);

    /**
     * List结构 向头部(Left)添加元素 * @param key * @param domain * @return
     */
    Long listUnshift(String key, T domain);

    /**
     * List结构 获取所有元素 * @param key * @return
     */
    List<T> listFindAll(String key);

    /**
     * 获取集合 分页
     *
     * @param key
     * @param start 开始元素
     * @param end   结束元素
     * @return
     */
    List<T> listFindByPage(String key, int start, int end);

    //根据key获取集合大小
    Long listFindSizeByKey(String key);

    /**
     * List结构 移除并获取数组第一个元素 * @param key * @return
     */
    T listLPop(String key);




    /**
     * 对象的实体类
     *
     * @param key
     * @param domain
     * @return
     */
    void valuePut(String key, T domain);
    /**
     * 设置值并设置超时时间
     * @param key       存储key
     * @param domain    存储的值
     * @param timeout   过期前时间 单位 秒
     * @param isFresh   是否强刷新数据(已存在覆盖)
     */
    void valuePut(String key, T domain, Long timeout, boolean isFresh);

    /**
     * 获取对象实体类
     *
     * @param key
     * @return
     */
    T getValue(String key);
    //获取并设置超时时间
    T getValue(String key, long timeout);
    /**
     * 获取并删除对象
     *
     * @param key
     * @return
     */
    T getValueAndRemove(String key);

    //删除对象
    void remove(String key);

}
