package com.xylink.wechat.bean;

import com.xylink.wechat.dao.po.WechatConfig;
import com.xylink.wechat.exception.BusinessException;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author 林骏
 * version: v1
 * date: 2020-10-24
 */
@Configuration
public class ApiConfig extends WechatConfig {


    public final String getAccessTokenUrl(){
        return this.getWechatUrl()+"/cgi-bin/gettoken?corpid="+getCorpId()+"&corpsecret="+getCorpSecret();
    }

    public final String getUserInfoUrl(String token,String code){
        return  this.getWechatUrl()+"/cgi-bin/user/getuserinfo?access_token="+token+"&code="+code;
    }

    public final String getUserDetailUrl(String token){
        return this.getWechatUrl()+"/cgi-bin/user/getuserdetail?access_token="+token;
    }

    public final String getJsApiTicketUrl(String token) {
        return this.getWechatUrl() + "/cgi-bin/get_jsapi_ticket?access_token="+token+"&type=agent_config";
    }

    public final String getJsAgentTicketUrl(String token) {
        return this.getWechatUrl() + "/cgi-bin/ticket/get?access_token="+token+"&type=agent_config";
    }


    public final String toHomePage(String id, String name, String phone, String company) throws UnsupportedEncodingException {
        return this.getWechatPageUrl()+ "?" + URLEncoder.encode("id="+id+"&userDisplayName="+name+"&userPhone="+phone+"&companyName="+company,"utf-8");
    }

    public final String getMeetingDetailUrl()  {
        return this.getWechatPageUrl() +"?meetingId=${meetingId}&userDisplayName=${userDisplayName}&userPhone=${userPhone}#/meetingdetails";
    }

    public final String getMeetingDetailUrl(String meetingId,String name,String phone) throws BusinessException {
        try{
            return this.getWechatPageUrl() +"?meetingId="+meetingId+"&userDisplayName="+URLEncoder.encode(name,"utf-8")+"&userPhone="+phone+"#/meetingdetails";
        }catch (Exception e){
            throw new BusinessException("获取会议链接url异常");
        }
    }

    public final String getMessageUrl(String token) {
        return this.getWechatUrl() + "/cgi-bin/message/send?access_token="+token;
    }


    public final String getSignatureUrl(){
        return this.getXylinkUrl()+"/api/rest/external/v1/meetingreminders?enterpriseId=" + getEnterpriseId();
    }

    public String getSdkMeetingCallUrl(String number){
        return this.getXylinkUrl() + "/api/rest/v4/en/callUrlInfo?version=v4&number=" + number +"&enterpriseId=" +getEnterpriseId();
    }
}
