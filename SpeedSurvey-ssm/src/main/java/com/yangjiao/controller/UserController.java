package com.yangjiao.controller;

import com.alibaba.fastjson.JSON;
import com.yangjiao.domain.User;
import com.yangjiao.service.UserService;
import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> doLogin(@RequestBody User user, HttpSession httpSession) {
        User userLogin = userService.login(user.getUsername(), user.getPassword());

        if (userLogin == null) {
            return ResponseData.create().error("用户名或密码错误").getResponse();
        }
        httpSession.setAttribute("user", userLogin);
        return ResponseData.create().success("登录成功").setData(userLogin).getResponse();
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> doRegister(@RequestBody Map<String,Object> params, HttpSession httpSession) {
        Object userInfo = params.get("userInfo");
        User user = JSON.parseObject(JSON.toJSONString(userInfo), User.class);
        String captcha = (String)params.get("captcha");

        if (user.getEmail() == null || "".equals(user.getEmail())) {
            return ResponseData.create().error("注册邮箱为必填项").getResponse();
        }
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            return ResponseData.create().error("用户名为必填项").getResponse();
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            return ResponseData.create().error("密码为必填项").getResponse();
        }

        String captchaInSession = (String)httpSession.getAttribute("captcha");
        if(!captcha.equals(captchaInSession)){
            return ResponseData.create().error("验证码错误").getResponse();
        }

        User userRegister = userService.register(user);
        if (userRegister == null) {
            return ResponseData.create().error("用户名已存在").getResponse();

        }
        return ResponseData.create().success("注册成功").setData(userRegister).getResponse();


    }

    @GetMapping("/delete")
    @ResponseBody
    public Map<String, Object> doDelete(int id) {
        User publisher = userService.deleteById(id);

        if (publisher == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(publisher).getResponse();

    }

    @GetMapping("/deleteMultiple")
    @ResponseBody
    public Map<String, Object> doDeleteMultiple(int[] ids) {
        List<User> publishers = userService.deleteByIds(ids);
        if (publishers == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(publishers).getResponse();

    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> doUpdate(@RequestBody User publisher) {
        User publisherUpdate = userService.update(publisher);

        if (publisherUpdate == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(publisherUpdate).getResponse();
    }

    @GetMapping("/logincheck")
    @ResponseBody
    public Map<String, Object> doLoginCheck(HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("user");
        if(user==null){
            return ResponseData.create().error("您还未登录，请登录").getResponse();
        }
        return ResponseData.create().success("已登陆").getResponse();
    }

}
