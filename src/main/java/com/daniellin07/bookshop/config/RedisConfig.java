package com.daniellin07.bookshop.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置文件
 *
 * @author DanielLin07
 * @date 2018/11/14 14:09
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 服务器Ip
     */
    @Value("${spring.redis.host}")
    private String host;

    /**
     * 端口号
     */
    @Value("${spring.redis.port}")
    private int port;

    /**
     * 密码
     */
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 连接超时时间
     */
    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * 最多连接数
     */
    @Value("${spring.redis.jedis.pool.max-total}")
    private int maxTotal;

    /**
     * 最大连接数
     */
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    /**
     * 最小连接数
     */
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    /**
     * 最大阻塞等待时间
     */
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    /**
     * 将JedisPool注入Spring容器，交给Spring管理
     *
     * @return JedisPool的Bean
     */
    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        if (StringUtils.isNotBlank(password)) {
            return new JedisPool(jedisPoolConfig, host, port, timeout, password);
        } else {
            return new JedisPool(jedisPoolConfig, host, port, timeout);
        }
    }
}
