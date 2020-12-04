package com.xylink.wechat.dao.po;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-03
 */
public class WechatConfig extends BaseModel{

    private String xylinkUrl;
    private String enterpriseId;
    private String instance;


    private String token;
    private String corpId;
    private String corpSecret;
    private String agentId;
    private String wechatUrl;
    private String wechatPageUrl;

    public String getXylinkUrl() {
        return xylinkUrl;
    }

    public void setXylinkUrl(String xylinkUrl) {
        this.xylinkUrl = xylinkUrl;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getWechatUrl() {
        return wechatUrl;
    }

    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl;
    }

    public String getWechatPageUrl() {
        return wechatPageUrl;
    }

    public void setWechatPageUrl(String wechatPageUrl) {
        this.wechatPageUrl = wechatPageUrl;
    }
}
