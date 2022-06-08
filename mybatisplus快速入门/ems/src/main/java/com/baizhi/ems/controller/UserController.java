package com.baizhi.ems.controller;

import com.baizhi.ems.entity.User;
import com.baizhi.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    //用户登录
    @RequestMapping("login")
    public String login(String username,String password,HttpSession session){
        User user = userService.login(username, password);
        //判断
        if(user!=null){
            session.setAttribute("user",user);
//            关于重定向写全的问题 表单也没有重复提交
            return "redirect:/emp/findAll";//查询员工
        }else {
            return "redirect:ems/login.jsp";
        }
    }

    //用户注册
    @PostMapping("regist")
    public String regist(User user,String code,HttpSession session){
        System.out.println("user="+user.toString());
        //1.判断验证码是否通过
        if(session.getAttribute("verifyCode").toString().equalsIgnoreCase(code)){
            //2.通过之后注册
            userService.save(user);
            return "redirect:ems/login.jsp";
        }else{
            //3.不通过直接到注册界面
            return "redirect:ems/regist.jsp";
        }
    }

}
