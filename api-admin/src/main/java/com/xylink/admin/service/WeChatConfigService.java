package com.xylink.admin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xylink.admin.bean.WeChatConfig;
import com.xylink.admin.dao.ApiConfigMapper;
import com.xylink.admin.entity.ApiConfig;
import com.xylink.admin.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
@Service
public class WeChatConfigService {
    private static final Logger logger = LoggerFactory.getLogger(WeChatConfigService.class);

    @Value("${nacos.server-addr}")
    private String nacosAddr;

    @Resource
    private ApiConfigMapper apiConfigMapper;

    public String status(){
        ConfigService configService;
        try{
            configService = ConfigFactory.createConfigService(nacosAddr);
        } catch (NacosException e) {
            logger.error(" 获取Nacos Config异常 ",e);
            throw new CommonException(e);
        }
        String status = configService.getServerStatus();
        logger.info (" 获取Nacos Config状态 {} ",status);
        return status;
    }

    public void create(){
        ConfigService configService;
        try{
            WeChatConfig weChatConfig = new WeChatConfig();
            weChatConfig.setCorpId("ww0e77da1ad9059120");
            weChatConfig.setAgentId("1000006");
            weChatConfig.setCorpSecret("9pmg2HXoere8HuLC_psXPgTuc5EAi2APiBo_aCBiPlQ");
            weChatConfig.setEnterpriseId("default_enterprise");
            weChatConfig.setInstance("jmrcd");
            weChatConfig.setToken("123");
            weChatConfig.setXylinkUrl("https://cloud.xylink.com");
            weChatConfig.setWechatUrl("https://internal-test.gov.weixin.qq.com/");
            configService = ConfigFactory.createConfigService(nacosAddr);
            configService.publishConfig("wechat-config","wechat-api", JSON.toJSONString(weChatConfig));

//            ApiConfig wechatApiConfig = new ApiConfig();
//            wechatApiConfig.setDataId("jmrcd");
//            wechatApiConfig.setGroupId("wechat-api");
//            wechatApiConfig.setName("江门人才岛");
//
//            WeChatConfig weChatConfig = new WeChatConfig();
//            weChatConfig.setCorpId("12313123");
//
//            apiConfigMapper.insert(wechatApiConfig);

        } catch (Exception e) {
            logger.error(" 获取Nacos Config异常 ",e);
            throw new CommonException(e);
        }
        logger.info (" 获取Nacos Configs ");
    }

    public void list(){
        ConfigService configService;
        try{
            QueryWrapper<ApiConfig> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("data_id").eq("group_id", "wechat-api");
            List<ApiConfig> wechatApiConfigs = apiConfigMapper.selectList(queryWrapper);
            logger.info("{}",wechatApiConfigs);
            configService = ConfigFactory.createConfigService(nacosAddr);
//            configService.getConfig();
        } catch (NacosException e) {
            logger.error(" 获取Nacos Config异常 ",e);
            throw new CommonException(e);
        }
        logger.info (" 获取Nacos Configs ");
    }
}
