package com.yangjiao.controller;

import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${mail.smtp.username}")
    private String emailFrom;

    @GetMapping("/getCaptcha")
    @ResponseBody
    public Map<String,Object> doSendCaptcha(@RequestParam("email") String emailTo, HttpSession httpSession){
        //生成六位数验证码
        String captcha = String.valueOf(new Random().nextInt(899999) + 100000);
        httpSession.setAttribute("captcha",captcha);
        httpSession.setMaxInactiveInterval(5*60);
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人的邮箱地址
        message.setFrom(emailFrom);
        //收件人的邮箱地址
        message.setTo(emailTo);
        //邮件主题
        message.setSubject("注册验证码-问卷鸭");
        //邮件内容
        message.setText("这是你的注册验证码："+captcha);
        //发送邮件
        javaMailSender.send(message);
        return ResponseData.create().success("验证码发送成功").getResponse();
    }
}
