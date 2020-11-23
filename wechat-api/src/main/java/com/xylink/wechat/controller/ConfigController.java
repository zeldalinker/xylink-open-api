package com.xylink.wechat.controller;

import com.xylink.wechat.bean.response.ResponseResult;
import com.xylink.wechat.exception.BusinessException;
import com.xylink.wechat.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Controller
@RequestMapping("/config")
public class ConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Resource
    private ConfigService configService;

    @RequestMapping(value = "/getJsApiConfig", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getJsApiConfig(@RequestParam String url)  {
        ResponseResult result;
        try {
            Map<String, Object> data = configService.getJsApiConfig(url);
            result = ResponseResult.createBySuccess(data);
        } catch (BusinessException e) {
            logger.info("[获取JsApiConfig异常]",e);
            result = ResponseResult.createByErrorMessage("[获取JsApiConfig异常]");
        }
        return result;
    }


    @RequestMapping(value = "/getJsAgentConfig", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getJsAgentConfig(@RequestParam String url)  {
        ResponseResult result;
        try {
            Map<String, Object> data = configService.getJsAgentConfig(url);
            result = ResponseResult.createBySuccess(data);
        } catch (BusinessException e) {
            logger.info("[获取JsAgentConfig异常]",e);
            return ResponseResult.createByErrorMessage("[获取JsAgentConfig异常]");
        }
        return result;
    }
}
