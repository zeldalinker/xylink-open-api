package com.xylink.wechat.config;

import com.xylink.wechat.config.factory.WeChatConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 林骏
 * version: v1
 * date: 2020-10-22
 */
@Configuration
public class EnvConfiguration {

//    @Bean
//    public XyConfig xyConfig(@Value("${xylink.cloud.host}") String host,
//                             @Value("${xylink.cloud.enterpriseId}") String enterpriseId,
//                             @Value("${xylink.cloud.instance}") String instance,
//                             @Value("${xylink.cloud.token}") String token){
//        return new XyConfig(host, enterpriseId, instance,token);
//    }
//
//    @Bean
//    public WeChatConfig wechatConfig(@Value("${wechat.corpId}") String corpId,
//                                     @Value("${wechat.corpSecret}") String corpSecret,
//                                     @Value("${wechat.agentId}") String agentId,
//                                     @Value("${wechat.host}") String apiHost,
//                                     @Value("${wechat.page}") String indexPage){
//        return new WeChatConfig(corpId, corpSecret, agentId, apiHost, indexPage);
//    }
}
