package com.maker.register.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JacksonConfig {
   @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
        RequestMappingHandlerAdapter adapter=new RequestMappingHandlerAdapter();
        List<MediaType> mediaTypes=new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        MappingJackson2HttpMessageConverter converter =new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        adapter.setMessageConverters(List.of(converter));
        return adapter;
    }
}
