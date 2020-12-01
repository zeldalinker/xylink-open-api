package com.xylink.admin.controller;

import com.xylink.admin.service.ApiConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
@RestController
@RequestMapping("config/nacos")
public class NacosConfigController {

    @Resource
    private ApiConfigService apiConfigService;


    @GetMapping("/status")
    public String status(){
        return apiConfigService.status();
    }
}
