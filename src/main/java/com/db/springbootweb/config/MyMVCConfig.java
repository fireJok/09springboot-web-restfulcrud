package com.db.springbootweb.config;

import com.db.springbootweb.component.LoginHandlerInterceptor;
import com.db.springbootweb.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//springmvc的配置类，使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {


    //添加视图映射
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /duan 请求来到 test
        registry.addViewController("/duan").setViewName("test");
    }
    //在public包和templates包中都有登录页面，但是如果不进行设置，会默认访问public包中的
    //页面,下面进行设置使得当浏览器发起请求时会通过模板引擎访问templates包下的页面，而不是
    //public中的静态资源
    @Bean//将组件注册进容器，否则不起作用
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        WebMvcConfigurer adapter=new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //当浏览器访问"/"时，会跳转到login.html页面
                //addViewController()方法是增加视图映射
                registry.addViewController("/").setViewName("login");
                //当浏览器访问"/index.html"时，会跳转到login.html页面
                registry.addViewController("/index.html").setViewName("login");
                //为了防止表单重复提交
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            //注册拦截器
           /* @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                //静态资源； *.css , *.js
                //SpringBoot已经做好了静态资源映射
                //excludePathPatterns("/index.html", "/", "/user/login")表示当访问这些地址
                    //时不用拦截
                registry.addInterceptor(new
                        LoginHandlerInterceptor())
                        .addPathPatterns("/**").excludePathPatterns("/index.html", "/","/static/**","/webjars/**","/asserts/**","/user/login");
            }*/

        };
        return adapter;
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
