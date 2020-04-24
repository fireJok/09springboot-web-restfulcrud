package com.db.springbootweb.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 点击login.html钟的中文/英文切换国际化配置
 * 系统默认的LocalResolver无法做到点击login.html总的中文/英文切换国际化配置，所以我们可以
 * 自己写一个LocalResolver来完成这个需求
 * 这个类写完之后还要再MyMVCConfig配置类中进行注册
 */
public class MyLocaleResolver implements LocaleResolver {
    /**
     * 浏览器点击中文/英文切换国际化配置的时候会把请求发送到这里
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        /**
         * 如果接收到"l"这个参数，就使用自定义的LocalResolver
         * 否则使用系统默认的
         */
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);
        }

        return  locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
