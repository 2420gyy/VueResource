package com.chen.verify.controller;

import com.chen.verify.utils.VerifyCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class VerifyController {

    @RequestMapping("/verifyCode")
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
