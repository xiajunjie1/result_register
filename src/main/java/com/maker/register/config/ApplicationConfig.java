package com.maker.register.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.maker.register.service","com.maker.register.config"})
@EnableAspectJAutoProxy
public class ApplicationConfig {
}
