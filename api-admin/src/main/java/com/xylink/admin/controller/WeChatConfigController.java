package com.xylink.admin.controller;

import com.xylink.admin.entity.WechatApiConfig;
import com.xylink.admin.service.WechatConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
@RestController
@RequestMapping("config/wechat")
public class WeChatConfigController {

    @Resource
    private WechatConfigService weChatConfigService;

    @PostMapping("/create")
    public WechatApiConfig create(){
        return weChatConfigService.create();
    }


    @GetMapping("/list")
    public List<WechatApiConfig> list(){
        return weChatConfigService.list();
    }


    @GetMapping("/select")
    public WechatApiConfig select(int name){
        return weChatConfigService.select(name);
    }

    @GetMapping("/setup")
    public WechatApiConfig setup(){
        return weChatConfigService.setup(24);
    }
}
