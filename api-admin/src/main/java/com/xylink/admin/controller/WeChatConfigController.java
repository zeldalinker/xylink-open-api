package com.xylink.admin.controller;

import com.xylink.admin.service.WeChatConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
@RestController
@RequestMapping("config/wechat")
public class WeChatConfigController {

    @Resource
    private WeChatConfigService weChatConfigService;


    @GetMapping("/status")
    public String status(){
        return weChatConfigService.status();
    }


    @GetMapping("/list")
    public void list(){
         weChatConfigService.list();
    }


    @PostMapping("/create")
    public void create(){
        weChatConfigService.create();
    }



}
