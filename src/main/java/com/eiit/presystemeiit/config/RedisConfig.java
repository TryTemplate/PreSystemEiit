package com.eiit.presystemeiit.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/13 21:31
 * @description Redis 相关配置
 */

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300)      //session过期时间，单位为秒
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * 自定义生成key的规则
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(o.getClass().getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(method.getName());
            stringBuilder.append("[");
            for (Object obj : objects) {
                stringBuilder.append(obj.toString());
            }
            stringBuilder.append("]");
            System.out.println("调用Redis缓存Key : " + stringBuilder);
            return stringBuilder.toString();
        };

    }

    /**
     * 采用RedisCacheManager作为缓存管理器
     *
     * @param connectionFactory
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheManager redisCacheManager =
                RedisCacheManager.builder(connectionFactory)
                        .cacheDefaults(defaultCacheConfig(10000))
                        .withInitialCacheConfigurations(initConfigurationMap())
                        .transactionAware().build();
        return redisCacheManager;
    }

    private RedisCacheConfiguration defaultCacheConfig(Integer second) {
        Jackson2JsonRedisSerializer<Object> redisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        redisSerializer.setObjectMapper(objectMapper);

        RedisCacheConfiguration configuration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofSeconds(second))
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                        .disableCachingNullValues();
        return configuration;
    }

    private Map<String, RedisCacheConfiguration> initConfigurationMap() {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        configurationMap.put("dept", defaultCacheConfig(1500));
        configurationMap.put("emp", defaultCacheConfig(1500));
        return configurationMap;
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

}
