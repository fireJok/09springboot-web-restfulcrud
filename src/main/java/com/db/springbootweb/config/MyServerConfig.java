package com.db.springbootweb.config;

import com.db.springbootweb.filter.MyFilter;
import com.db.springbootweb.servlet.MyServlet;
import listener.MyListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * servlet配置
 */
@Configuration
public class MyServerConfig {
    //注册三大组件
    @Bean
    //注册servlet;
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new
                MyServlet(),"/myServlet");
        return registrationBean;
    }
    //注册filter;
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }
    //注册listener
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new
                ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }
}
