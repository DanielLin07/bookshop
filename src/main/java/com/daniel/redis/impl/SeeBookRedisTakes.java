package com.daniel.redis.impl;

import com.daniel.redis.RedisBaseTakes;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@Component("SeeBookRedisTakes")
public class SeeBookRedisTakes implements RedisBaseTakes {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    private Logger logger = Logger.getLogger(String.valueOf(SeeBookRedisTakes.class));

    @Override
    public void add(Object key, String value) {
        if (redisTemplate == null){
            logger.warning("redisTemplate 实例化失败");
            return;
        }else {
            redisTemplate.opsForValue().set(key,value);
        }
    }

    @Override
    public void addObj(Object objectKey, Object key, Object object) {
        if (redisTemplate == null){
            logger.warning("redisTemplate 实例化失败");
            return;
        }else {
            redisTemplate.opsForHash().put(objectKey,key,object);
        }
    }

    @Override
    public void delete(Object key) {

    }

    @Override
    public void delete(List listKeys) {

    }

    @Override
    public void deleteObj(Object objectKey, Object key) {

    }

    @Override
    public void update(Object key, String value) {

    }

    @Override
    public void updateObj(Object objectKey, Object key, Object object) {

    }

    @Override
    public String get(Object key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        return value;
    }

    @Override
    public Object getObj(Object objectKey, Object key) {
        return null;
    }
}
