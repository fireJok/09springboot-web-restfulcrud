package com.db.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @PostMapping("/user/login")//表示只接受post的请求
    //同@RequestMapping(value = "user/login",method = RequestMethod.POST)
    public String login(Map<Object,Object> map, String username, String password, HttpSession session){
        System.out.println(username+":"+password);
        session.setAttribute("loginuser",username);
    if (!StringUtils.isEmpty(username)&&"123456".equals(password)){
        //登录成功，为了防止表单重复提交
        //这里return "redirect:/main.html";重定向到main.html,但是因为在MyMVCConfig中
        //设置了视图映射，当访问main.html时，会跳转到dashboard.html(这个设置在MyMVCConfig配置类中)
        return "redirect:/main.html";
    }else{
        //登录失败
        map.put("msg","用户名或密码错误");
        return "login";
    }


    }
}
