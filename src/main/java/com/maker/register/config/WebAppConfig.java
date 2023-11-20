package com.maker.register.config;

import com.maker.register.controller.interceptor.LimitInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan({"com.maker.register.controller"})
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("/image/");
        registry.addResourceHandler("/jquery/**").addResourceLocations("/jquery/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public InternalResourceViewResolver resourceViewResolver(){
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        resolver.setPrefix("/WEB-INF/pages");
        return resolver;
    }

    /**
     * 使用此方法的目的是为了在拦截器中注入Bean，如果直接添加拦截器，注入Bean可能为空
     * */
    @Bean
    public LimitInterceptor limitInterceptor(){
        return new LimitInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limitInterceptor()).addPathPatterns("/**");

    }
}
