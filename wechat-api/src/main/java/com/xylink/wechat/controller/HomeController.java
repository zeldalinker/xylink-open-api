package com.xylink.wechat.controller;

import com.xylink.wechat.exception.BusinessException;
import com.xylink.wechat.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Controller
@RequestMapping("/index")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private HomeService homeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public void toPage(@RequestParam String code, HttpServletResponse response)  {
        logger.info(" index  code : {}", code);
        try {
            String page = homeService.toPage(code);
            response.sendRedirect(page);
        } catch (BusinessException | IOException e) {
            logger.error("[跳转政务微信首页异常]",e);
        }
    }
}
