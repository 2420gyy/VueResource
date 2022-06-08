package com.baizhi.ems.controller;

import com.baizhi.ems.utils.VerifyCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class TestController {

    @RequestMapping("hello")
    public String test1(){
        return "你好";
    }

    //生成验证码
    @RequestMapping("/ems/verifyCode")
    public void getVerifyCode(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("verifyCode", verifyCode);
        //验证码存入图片
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        VerifyCodeUtil.outputImage(105, 45, outputStream, verifyCode);
    }

}
