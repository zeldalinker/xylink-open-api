package com.xylink.wechat.config.factory;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

/**
 * @author 林骏
 * version: v1
 * date: 2020-10-24
 */
@Configuration
@NacosPropertySource(dataId = "xycloud", groupId = "xy-wechat-api",autoRefreshed = true)
public class XyConfig {
    @NacosValue("${host}")
    private String host;
    @NacosValue("${enterpriseId}")
    private String enterpriseId;
    @NacosValue("${instance}")
    private String instance;
    @NacosValue("${token}")
    private String token;


    public String getToken() {
        return token;
    }

    public String getHost() {
        return host;
    }

    public String getLoginUrl(){
        return host+"/api/rest/v1/thirdAuth/token/login";
    }

    public String getAccountBindUrl(){
        return host+"/api/rest/v1/thirdAuth/account/binding";
    }

    public String getAddOrUpdateUserUrl(){
        return host+"/api/rest/v1/thirdAuth/account/addOrUpdate";
    }

    public String getDeleteUserUrl(){
        return  host+"/api/rest/v1/thirdAuth/account/remove";
    }

    public String getAddOrUpdateDeptUrl(){
        return  host+"/api/rest/v1/thirdAuth/department/addOrUpdate";
    }

    public String getDeleteDeptUrl(){
        return  host+"/api/rest/v1/thirdAuth/department/remove";
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public String getInstance() {
        return instance;
    }

    public final String getSignatureUrl(){
        return host+"/api/rest/external/v1/meetingreminders?enterpriseId=" + enterpriseId;
    }


}
