package com.xylink.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.xylink.wechat.bean.wechat.UserDetail;
import com.xylink.wechat.bean.wechat.UserInfo;
import com.xylink.wechat.config.factory.WeChatConfig;
import com.xylink.wechat.exception.BusinessException;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */

@Service
public class WeChatService {
    private static final Logger logger = LoggerFactory.getLogger(WeChatService.class);

    @Resource
    private WeChatConfig weChatConfig;

    @Resource
    private RestTemplate restTemplate;


    @Cacheable(value = "wechat:gettoken")
    public String getAccessToken() throws BusinessException {
        logger.info(" [ WeChat access_token ]  ");
        ResponseEntity<String> response = restTemplate.getForEntity(weChatConfig.getAccessTokenUrl(), String.class);
        if(response.getStatusCode() != HttpStatus.OK){
            throw new BusinessException(" wechat http failure ");
        }
        JSONObject json = JSONObject.parseObject(response.getBody());
        return json.getString("accessToken");

    }

    @Cacheable(value = "wechat:user_getuserinfo",key="#code+#token")
    public UserInfo getUserInfo(String code, String token) throws BusinessException {
        logger.info(" [ WeChat GetUserInfo]  ");
        String url = weChatConfig.getUserInfoUrl(token,code);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if(response.getStatusCode() != HttpStatus.OK){
            throw new BusinessException(" wechat http failure ");
        }
        return JSONObject.parseObject(response.getBody(), UserInfo.class);
    }

    @Cacheable(value = "wechat:get_jsapi_ticket",key="#token")
    public String getJsApiTicket(String token) throws BusinessException {
        String url = weChatConfig.getJsApiTicketUrl(token);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if(response.getStatusCode() != HttpStatus.OK){
            throw new BusinessException(" wechat http get_jsapi_ticket failure ");
        }
        JSONObject json = JSONObject.parseObject(response.getBody());
        return json.getString("ticket");
    }

    @Cacheable(value = "wechat:get_jsagent_ticket",key="#token")
    public String getJsAgentTicket(String token) throws BusinessException {
        String url = weChatConfig.getJsAgentTicketUrl(token);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if(response.getStatusCode() != HttpStatus.OK){
            throw new BusinessException(" wechat http get_jsagent_ticket failure ");
        }
        JSONObject json = JSONObject.parseObject(response.getBody());
        return json.getString("ticket");
    }




    String getUserIndexPageUrl(UserInfo userInfo, String token) throws BusinessException {
        UserDetail userDetail;
        Map<String, String> params = new HashMap<>(1);
        params.put("user_ticket", userInfo.getUser_ticket());
        ResponseEntity<String> entity;
        String url = weChatConfig.getUserDetailUrl(token);
        entity = restTemplate.postForEntity(url, params, String.class);
        logger.info(" get gov-wechat user detail  body = {}",entity.getBody());
        if (entity.getStatusCode() != HttpStatus.OK && StringUtil.isEmpty(entity.getBody())) {
            throw new BusinessException(" [get gov-wechat user detail failure] ");
        }
        userDetail = JSONObject.parseObject(entity.getBody(), UserDetail.class);
        String page;
        try{
            page = weChatConfig.toIndexPage(userDetail.getUserid(),userDetail.getName(),userDetail.getMobile(),"");
        }catch (UnsupportedEncodingException e) {
            throw new BusinessException("跳转政务微信首页异常");
        }
        return page;
    }


}
