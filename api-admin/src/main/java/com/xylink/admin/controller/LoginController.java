package com.xylink.admin.controller;

import com.alibaba.fastjson.JSON;
import com.xylink.admin.bean.LoginAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
@RestController
@RequestMapping("api")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @PostMapping("login")
    public void login(@RequestBody LoginAccount account){
        logger.info("{}", JSON.toJSONString(account));
    }


    @GetMapping("verifyCode")
    public String login(@RequestParam(value = "code") String time){
        return "123123123";
    }
}
