package com.xylink.wechat.service;

import com.xylink.wechat.dao.po.WeChatConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-03
 */
@Service
@Transactional
public class WeChat {

    @Resource
    private WeChatConfigRepository weChatConfigRepository;

    @PostConstruct
    public void save(){
        WeChatConfig config = new WeChatConfig();
        config.setAgentId("1000006");
        config.setCorpSecret("9pmg2HXoere8HuLC_psXPgTuc5EAi2APiBo_aCBiPlQ");
        config.setWechatUrl("https://internal-test.gov.weixin.qq.com/");
        config.setEnterpriseId("default_enterprise");
        config.setInstance("jmrcd");
        config.setToken("123");
        config.setXylinkUrl("https://cloud.xylink.com");
        config.setWechatPageUrl("123123");
        config.setId(1);
        try{
            WeChatConfig latest = weChatConfigRepository.save(config);
            System.out.println(latest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
