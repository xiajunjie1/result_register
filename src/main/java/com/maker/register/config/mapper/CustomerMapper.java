package com.maker.register.config.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * ObjectMapper为Jackson提供的类
 * 用于进行Java对象与json对象之间的转换
 * */
public class CustomerMapper extends ObjectMapper {
    private static final String DEFAULT_DATE_FORMATTER="yyyy-MM-dd";
    public CustomerMapper(){
        super.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMATTER));
        super.configure(SerializationFeature.INDENT_OUTPUT,true);
        super.setSerializationInclusion(JsonInclude.Include.NON_NULL);//不序列化null值
        super.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));//时区

    }
}
