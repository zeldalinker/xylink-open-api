package com.xylink.wechat.controller;

import com.xylink.wechat.config.WechatApiConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-06
 */
@Controller
@RequestMapping("api/config")
public class ApiController {

    @Resource
    private WechatApiConfig wechatApiConfig;


    @RequestMapping("get")
    public String get(){
        return "redirect:index";
    }
}
