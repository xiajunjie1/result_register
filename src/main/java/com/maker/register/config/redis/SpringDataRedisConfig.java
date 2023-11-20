package com.maker.register.config.redis;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

@Configuration
@PropertySource("classpath:config/redis.properties")
public class SpringDataRedisConfig {
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(
            @Value("${redis.host}") String host,
            @Value("${redis.port}") int port,
            @Value("${redis.username}") String username,
            @Value("${redis.password}") String password,
            @Value("${redis.database}") int database
    ){
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setUsername(username);
        redisStandaloneConfiguration.setPassword(password);
        redisStandaloneConfiguration.setDatabase(database);
        return redisStandaloneConfiguration;
    }
    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig(
            @Value("${redis.pool.maxTotal}") int maxTotal,
            @Value("${redis.pool.maxIdle}") int maxIdle,
            @Value("${redis.pool.minIdle}") int minIdle,
            @Value("${redis.pool.testOnBorrow}") boolean testOnBorrow
    ){
        GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }
    @Bean
    public LettucePoolingClientConfiguration lettucePoolingClientConfiguration(
            @Autowired GenericObjectPoolConfig genericObjectPoolConfig){
        return LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig).build();
    }
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(@Autowired RedisStandaloneConfiguration redisStandaloneConfiguration,
                                                             @Autowired LettucePoolingClientConfiguration lettucePoolingClientConfiguration){
        LettuceConnectionFactory connectionFactory=new LettuceConnectionFactory(redisStandaloneConfiguration,lettucePoolingClientConfiguration);
        return connectionFactory;
    }


}
