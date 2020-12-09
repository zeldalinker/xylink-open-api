package com.xylink.wechat.dao.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-03
 */
@Data
@Component

public class WeChatConfig extends BaseModel{
    private String corpSecret;
    private String corpId;
    private String agentId;
    private String wechatUrl;
    private String pageUrl;


    private String enterpriseId;
    private String instance;
    private String token;
    private String xylinkUrl;
}
