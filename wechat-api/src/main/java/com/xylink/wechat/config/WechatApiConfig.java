package com.xylink.wechat.config;

import com.xylink.wechat.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author 林骏
 * version: v1
 * date: 2020-10-24
 */
@PropertySource(value = {
        "classpath:wechat.properties",
}, encoding = "utf-8")
@Component
@Data
public class WechatApiConfig {
    @Value("${corpSecret}")
    private String corpSecret;
    @Value("${corpId}")
    private String corpId;
    @Value("${agentId}")
    private String agentId;
    @Value("${wechatUrl}")
    private String wechatUrl;

    @Value("${pageUrl}")
    private String pageUrl;

    @Value("${enterpriseId}")
    private String enterpriseId;
    @Value("${instance}")
    private String instance;
    @Value("${token}")
    private String token;
    @Value("${xylinkUrl}")
    private String xylinkUrl;



    public final String getAccessTokenUrl(){
        return this.getWechatUrl()+"/cgi-bin/gettoken?corpid="+this.getCorpId()+"&corpsecret="+this.getCorpSecret();
    }

    public final String getUserInfoUrl(String accessToken,String code){
        return  this.getWechatUrl()+"/cgi-bin/user/getuserinfo?access_token="+accessToken+"&code="+code;
    }

    public final String getUserDetailUrl(String accessToken){
        return this.getWechatUrl()+"/cgi-bin/user/getuserdetail?access_token="+accessToken;
    }

    public final String getJsApiTicketUrl(String accessToken) {
        return this.getWechatUrl() + "/cgi-bin/get_jsapi_ticket?access_token="+accessToken+"&type=agent_config";
    }

    public final String getJsAgentTicketUrl(String accessToken) {
        return this.getWechatUrl() + "/cgi-bin/ticket/get?access_token="+accessToken+"&type=agent_config";
    }


    public final String toHomePage(String id, String name, String phone, String company) throws UnsupportedEncodingException {
        return this.getPageUrl()+ "?" + URLEncoder.encode("id="+id+"&userDisplayName="+name+"&userPhone="+phone+"&companyName="+company,"utf-8");
    }

    public final String getMeetingDetailUrl()  {
        return this.getPageUrl() +"?meetingId=${meetingId}&userDisplayName=${userDisplayName}&userPhone=${userPhone}#/meetingdetails";
    }

    public final String getMeetingDetailUrl(String meetingId,String name,String phone) throws BusinessException {
        try{
            return this.getPageUrl() +"?meetingId="+meetingId+"&userDisplayName="+URLEncoder.encode(name,"utf-8")+"&userPhone="+phone+"#/meetingdetails";
        }catch (Exception e){
            throw new BusinessException("获取会议链接url异常");
        }
    }

    public final String getMessageUrl(String token) {
        return this.getWechatUrl() + "/cgi-bin/message/send?access_token="+token;
    }


    public final String getSignatureUrl(){
        return this.getXylinkUrl()+"/api/rest/external/v1/meetingreminders?enterpriseId=" + this.getEnterpriseId();
    }

    public String getSdkMeetingCallUrl(String number){
        return this.getXylinkUrl() + "/api/rest/v4/en/callUrlInfo?version=v4&number=" + number +"&enterpriseId=" + this.getEnterpriseId();
    }
}
