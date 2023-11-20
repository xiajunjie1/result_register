package com.maker.register.web.config;

import com.maker.register.config.ApplicationConfig;
import com.maker.register.config.WebAppConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class StartWebApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
  //Spring配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }
    //SpringMVC配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }
    //DispatcherServlet路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    //字符编码过滤器
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }
}
