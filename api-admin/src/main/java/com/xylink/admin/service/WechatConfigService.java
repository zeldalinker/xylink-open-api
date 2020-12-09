package com.xylink.admin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xylink.admin.dao.WechatApiConfigMapper;
import com.xylink.admin.entity.WechatApiConfig;
import com.xylink.admin.exception.CommonException;
import com.xylink.admin.wechat.ApiConfigRepository;
import com.xylink.admin.wechat.po.BaseConfig;
import jodd.io.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
@Service
public class WechatConfigService {
    private static final Logger logger = LoggerFactory.getLogger(WechatConfigService.class);

    @Value("${nacos.server-addr}")
    private String nacosAddr;

    @Resource
    private WechatApiConfigMapper wechatApiConfigMapper;

    @Resource
    private ApiConfigRepository apiConfigRepository;


    public WechatApiConfig create(){
        WechatApiConfig config = new WechatApiConfig();
        try{
            config.setName("江门人才岛");
            config.setCorpId("ww0e77da1ad9059120");
            config.setAgentId("1000006");
            config.setCorpSecret("9pmg2HXoere8HuLC_psXPgTuc5EAi2APiBo_aCBiPlQ");
            config.setEnterpriseId("default_enterprise");
            config.setInstance("jmrcd");
            config.setToken("123");
            config.setXyUrl("https://cloud.xylink.com");
            config.setWechatUrl("https://internal-test.gov.weixin.qq.com/");
            wechatApiConfigMapper.insert(config);
        } catch (Exception e) {
            logger.error(" 获取Wechat Config异常 ",e);
            throw new CommonException(e);
        }
        logger.info (" 获取 Wechat Configs ");
        return config;
    }



    public WechatApiConfig select(int id){
        WechatApiConfig config;
        try{
            logger.info(" 获取Wechat Config ID = {} ",id);
            QueryWrapper<WechatApiConfig> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            config = wechatApiConfigMapper.selectOne(queryWrapper);
        } catch (Exception e) {
            logger.error(" 获取Wechat Config异常 ",e);
            throw new CommonException(e);
        }
        logger.info (" 获取 Wechat Config = {} ",config);
        return config;
    }


    public List<WechatApiConfig> list(){
        List<WechatApiConfig> configs;
        try{
            QueryWrapper<WechatApiConfig> queryWrapper = new QueryWrapper<>();
            configs = wechatApiConfigMapper.selectList(queryWrapper);
        } catch (Exception e) {
            logger.error(" 获取Wechat Config异常 ",e);
            throw new CommonException(e);
        }
        logger.info (" 获取 Wechat Configs count = {} ",configs.size());
        return configs;
    }

    public WechatApiConfig setup(int id) {
        WechatApiConfig config = this.select(id);
        Map<String,String> properties = JSON.parseObject(JSON.toJSONString(config), new TypeReference<Map<String,String>>() {});
        createProperties(properties);
        return config;
    }



    private void createProperties(Map<String,String> attrs){
        try{
            Properties properties = new Properties();
            //遍历Properties
            Set<Map.Entry<String,String>> entries = attrs.entrySet();
            for(Map.Entry<String,String> entry : entries){
                properties.setProperty(entry.getKey(),entry.getValue());
            }
            //使用Properties生产配置文件
            properties.store(new FileWriter("api-admin/deploy/person.properties"), "文件自述");
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
