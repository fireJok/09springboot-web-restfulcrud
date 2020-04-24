package com.db.springbootweb.controller;

import com.db.springbootweb.exception.UserNotExitsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 * 用来处理异常
 */
@ControllerAdvice
public class MyExceptionHandler {
    //这是第一种方法
    //这样就写死了，浏览器和客户端都会返回json数据，但是浏览器应该返回错误页面
//    @ResponseBody//将该方法的返回值以json的类型返回
//    @ExceptionHandler(UserNotExitsException.class)//表示捕获到UserNotExitsException这个异常就会进入这个方法
//    public Map<String,Object> handlerException(Exception e){
//        Map<String,Object> map=new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message",e.getMessage());
//        return map;
//
//    }
    //这是第二种方法
    //使用下面这个方法就可以做到错误自适应，客户端就返回json数据，浏览器返回错误页面
    @ExceptionHandler(UserNotExitsException.class)//表示捕获到UserNotExitsException这个异常就会进入这个方法
    public String handlerException(Exception e, HttpServletRequest request){

        Map<String,Object> map=new HashMap<>();
        //传入我们自己的错误状态码，例如4xx，5xx。如果不这样做，就不会到我们自定义的错误页面
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message","用户出错了");
        request.setAttribute("ext",map);
        //转发到error页面
        return "forward:/error";

    }
}
