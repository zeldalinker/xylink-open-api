package com.xylink.wechat.service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.xylink.wechat.config.factory.WeChatApiConfig;
import com.xylink.wechat.exception.BusinessException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Service
public class ConfigService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigService.class);

    @Resource
    private WeChatService weChatService;

    @Resource
    private WeChatApiConfig weChatConfig;


    public Map<String, Object> getJsApiConfig(String url) throws BusinessException {
        String accessToken = weChatService.getAccessToken();
        String jsApiTicket = weChatService.getJsApiTicket(accessToken);
        long epoch = System.currentTimeMillis() / 1000;
        String nonce = UUID.randomUUID().toString();
        String sha1Hex = toSha1Hex(url, jsApiTicket, UUID.randomUUID().toString(), epoch);
        logger.info(" getJsApiConfig SHA1 : [{}]", sha1Hex);
        Map<String, Object> jsApiConfig = Maps.newHashMap();
        jsApiConfig.put("appId", weChatConfig.getCorpId());
        jsApiConfig.put("timestamp", epoch);
        jsApiConfig.put("signature", sha1Hex);
        jsApiConfig.put("nonceStr", nonce);
        return jsApiConfig;
    }


    public Map<String, Object> getJsAgentConfig(String url) throws BusinessException {
        String accessToken = weChatService.getAccessToken();
        String jsApiTicket = weChatService.getJsAgentTicket(accessToken);
        long epoch = System.currentTimeMillis() / 1000;
        String nonce = UUID.randomUUID().toString();
        String sha1Hex = toSha1Hex(url, jsApiTicket, UUID.randomUUID().toString(), epoch);
        logger.info(" getJsAgentConfig sha1 : [{}]", sha1Hex);
        Map<String, Object> jsAgentConfig = Maps.newHashMap();
        jsAgentConfig.put("corpid", weChatConfig.getCorpId());
        jsAgentConfig.put("agentid", weChatConfig.getAgentId());
        jsAgentConfig.put("timestamp", epoch);
        jsAgentConfig.put("signature", sha1Hex);
        jsAgentConfig.put("nonceStr", nonce);
        return jsAgentConfig;
    }


    private String toSha1Hex(String url, String ticket, String nonce, long epoch) {
        String config;
        if (Strings.isNullOrEmpty(url)) {
            config = "jsapi_ticket=" + ticket + "&noncestr=" + nonce + "&timestamp=" + epoch + "&url=" + url;
        } else {
            config = "jsapi_ticket=" + ticket + "&noncestr=" + nonce + "&timestamp=" + epoch + "&url=" + url;
        }
        String sha1Hex = DigestUtils.sha1Hex(config);
        logger.info(" generate sha1 : [{}]", sha1Hex);
        return sha1Hex;
    }


}
