package com.xylink.admin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-03
 */
@Data
public class WeChatConfig {

    @JSONField(name = "xylink.url")
    private String xylinkUrl;
    @JSONField(name = "xylink.enterpriseId")
    private String enterpriseId;
    @JSONField(name = "xylink.instance")
    private String instance;
    @JSONField(name = "xylink.token")
    private String token;

    @JSONField(name = "wechat.corpid")
    private String corpId;

    @JSONField(name = "wechat.corpSecret")
    private String corpSecret;
    @JSONField(name = "wechat.agentId")
    private String agentId;
    @JSONField(name = "wechat.url")
    private String wechatUrl;
    @JSONField(name = "wechat.page.url")
    private String wechatPageUrl;

}
