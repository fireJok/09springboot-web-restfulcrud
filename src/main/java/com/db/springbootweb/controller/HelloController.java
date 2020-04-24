package com.db.springbootweb.controller;

import com.db.springbootweb.exception.UserNotExitsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("hello")
    public String hello(@RequestParam("user") String user){
        if (user.equals("aaa")){
            throw new UserNotExitsException();
        }
        return "hello world";
    }
    @RequestMapping("ThymeleafTest1")
    public String ThymeleafTest1(){
        return "test";
    }
    @RequestMapping("ThymeleafTest2")
    public String ThymeleafTest2(Map<Object,Object> map,HttpSession session){
        session.setAttribute("hi","hi");
        map.put("hello","你好！");
        map.put("users", Arrays.asList("zahara","lisa","wags"));
        return "test02";
    }
}
