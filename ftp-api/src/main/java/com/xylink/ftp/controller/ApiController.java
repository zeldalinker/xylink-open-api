package com.xylink.ftp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-27
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @GetMapping("deploy")
    public String deploy(){
        return "ftp-api deploy success";
    }
}
