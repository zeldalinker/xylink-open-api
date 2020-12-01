package com.xylink.admin.service;

import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.xylink.admin.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
@Service
public class ApiConfigService {
    private static final Logger logger = LoggerFactory.getLogger(ApiConfigService.class);

    @Value("${nacos.config.server-addr}")
    private String nacosAddr;

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
}
