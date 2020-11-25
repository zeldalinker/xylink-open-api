package com.xylink.wechat.config.factory;

import com.alibaba.nacos.api.config.annotation.NacosValue;
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
@NacosPropertySource(dataId = "wechat", groupId = "xy-wechat-api",autoRefreshed = true)
public class WeChatConfig {

    @NacosValue("${corpId}")
    private String corpId;
    @NacosValue("${corpSecret}")
    private String corpSecret;
    @NacosValue("${agentId}")
    private String agentId;
    @NacosValue("${host}")
    private String host;
    @NacosValue("${page}")
    private String page;


    public String getCorpId() {
        return corpId;
    }

    public String getAgentId() {
        return agentId;
    }

    public final String getAccessTokenUrl(){
        return this.host+"/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+corpSecret;
    }

    public final String getUserInfoUrl(String token,String code){
        return  this.host+"/cgi-bin/user/getuserinfo?access_token="+token+"&code="+code;
    }

    public final String getUserDetailUrl(String token){
        return this.host+"/cgi-bin/user/getuserdetail?access_token="+token;
    }

    public final String getJsApiTicketUrl(String token) {
        return this.host + "/cgi-bin/get_jsapi_ticket?access_token="+token+"&type=agent_config";
    }

    public final String getJsAgentTicketUrl(String token) {
        return this.host + "/cgi-bin/ticket/get?access_token="+token+"&type=agent_config";
    }


    public final String toIndexPage(String id, String name, String phone, String company) throws UnsupportedEncodingException {
        return this.page+ "?" + URLEncoder.encode("id="+id+"&userDisplayName="+name+"&userPhone="+phone+"&companyName="+company,"utf-8");
    }

    public final String getMeetingDetailUrl()  {
        return this.page +"?meetingId=${meetingId}&userDisplayName=${userDisplayName}&userPhone=${userPhone}#/meetingdetails";
    }

    public final String getMeetingDetailUrl(String meetingId,String name,String phone) throws BusinessException {
        try{
            return this.page +"?meetingId="+meetingId+"&userDisplayName="+URLEncoder.encode(name,"utf-8")+"&userPhone="+phone+"#/meetingdetails";
        }catch (Exception e){
            throw new BusinessException("获取会议链接url异常");
        }
    }

    public final String getMessageUrl(String token) {
        return this.host + "/cgi-bin/message/send?access_token="+token;
    }
}
