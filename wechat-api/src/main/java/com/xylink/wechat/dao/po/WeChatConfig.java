package com.xylink.wechat.dao.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-03
 */
@Entity
@Table(name = "wechat_config")
public class WeChatConfig {
    @Column(name = "id")
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "xylink_url")
    private String xylinkUrl;
    @Column(name = "enterpriseId")
    private String enterpriseId;
    @Column(name = "instance")
    private String instance;


    @Column(name = "token")
    private String token;
    @Column(name = "corpid")
    private String corpId;
    @Column(name = "corpSecret")
    private String corpSecret;
    @Column(name = "agentId")
    private String agentId;
    @Column(name = "wechat_url")
    private String wechatUrl;
    @Column(name = "wechat_page_url")
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
