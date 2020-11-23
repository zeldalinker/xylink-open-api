package com.xylink.wechat.config.factory;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.xylink.wechat.exception.BusinessException;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */

@Configuration
@NacosPropertySource(dataId = "国税", groupId = "xy-cloud",autoRefreshed = true)
public class WeChatConfig {

    private String corpId;
    private String corpSecret;
    private String agentId;
    private String apiHost;
    private String indexPage;
    private String accessTokenCacheKey = "WeChat:ACCESS_TOKEN:";
    private String jsApiTicketCacheKey = "WeChat:JS_API_TICKET:";
    private String jsAgentCacheKey = "WeChat:JS_AGENT_TICKET:";


    public WeChatConfig(String corpId, String corpSecret, String agentId, String apiHost, String indexPage) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
        this.agentId = agentId;
        this.apiHost = apiHost;
        this.indexPage = indexPage;
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

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }

    public String getAccessTokenCacheKey() {
        return accessTokenCacheKey;
    }

    public void setAccessTokenCacheKey(String accessTokenCacheKey) {
        this.accessTokenCacheKey = accessTokenCacheKey;
    }

    public String getJsApiTicketCacheKey() {
        return jsApiTicketCacheKey;
    }

    public void setJsApiTicketCacheKey(String jsApiTicketCacheKey) {
        this.jsApiTicketCacheKey = jsApiTicketCacheKey;
    }

    public String getJsAgentCacheKey() {
        return jsAgentCacheKey;
    }

    public void setJsAgentCacheKey(String jsAgentCacheKey) {
        this.jsAgentCacheKey = jsAgentCacheKey;
    }

    public final String getAccessTokenUrl(){
        return this.apiHost+"/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+corpSecret;
    }

    public final String getUserInfoUrl(String token,String code){
        return  this.apiHost+"/cgi-bin/user/getuserinfo?access_token="+token+"&code="+code;
    }

    public final String getUserDetailUrl(String token){
        return this.apiHost+"/cgi-bin/user/getuserdetail?access_token="+token;
    }

    public final String getJsApiTicketUrl(String token) {
        return this.apiHost + "/cgi-bin/get_jsapi_ticket?access_token="+token+"&type=agent_config";
    }

    public final String getJsAgentTicketUrl(String token) {
        return this.apiHost + "/cgi-bin/ticket/get?access_token="+token+"&type=agent_config";
    }


    public final String toIndexPage(String id, String name, String phone, String company) throws UnsupportedEncodingException {
        return this.indexPage+ "?" + URLEncoder.encode("id="+id+"&userDisplayName="+name+"&userPhone="+phone+"&companyName="+company,"utf-8");
    }

    public final String getMeetingDetailUrl()  {
        return this.indexPage +"?meetingId=${meetingId}&userDisplayName=${userDisplayName}&userPhone=${userPhone}#/meetingdetails";
    }

    public final String getMeetingDetailUrl(String meetingId,String name,String phone) throws BusinessException {
        try{
            return this.indexPage +"?meetingId="+meetingId+"&userDisplayName="+URLEncoder.encode(name,"utf-8")+"&userPhone="+phone+"#/meetingdetails";
        }catch (Exception e){
            throw new BusinessException("获取会议链接url异常");
        }
    }

    public final String getMessageUrl(String token) {
        return this.apiHost + "/cgi-bin/message/send?access_token="+token;
    }
}
