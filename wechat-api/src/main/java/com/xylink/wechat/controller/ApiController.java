package com.xylink.wechat.controller;

import com.xylink.wechat.service.ApiService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-24
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @Resource
    private ApiService apiService;


    @GetMapping("/gettoken")
    @ResponseBody
    public String gettoken(){
        return apiService.getToken();
    }

    @GetMapping("/getuserinfo")
    @ResponseBody
    public String getuserinfo(@RequestParam String code,@RequestParam String token){
        return apiService.getuserinfo(code,token);
    }
}
