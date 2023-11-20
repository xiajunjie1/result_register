package com.maker.register.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

@Configuration
public class RedisScriptConfig {
    @Bean
    public DefaultRedisScript<Boolean> limitScript(){
        DefaultRedisScript<Boolean> limitScript=new DefaultRedisScript<>();
        limitScript.setLocation(new ClassPathResource("lua/limit.lua"));
        limitScript.setResultType(Boolean.class);
        return limitScript;
    }
}
