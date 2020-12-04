package com.xylink.wechat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-24
 */
@Service
public class ApiService {
    private static final Logger logger = LoggerFactory.getLogger(MeetingService.class);



    @Cacheable(value = "wechat:gettoken")
    public String getToken(){
        logger.info("wechat:gettoken");
        return "wechat:gettoken";
    }


    @Cacheable(value = "wechat:user_getuserinfo",key="#code+#token")
    public String getuserinfo(String code, String token) {
        logger.info(" [ WeChat GetUserInfo]  code = {} ,token = {} ",code,token);
        return code+token;
    }
}
