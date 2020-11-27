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
 * date: 2020-10-24
 */
@Configuration
@NacosPropertySource(dataId = "wechat-api", groupId = "xy-open-api",autoRefreshed = true)
public class WeChatApiConfig {
    @NacosValue("${xy.url}")
    private String xyUrl;
    @NacosValue("${xy.enterpriseId}")
    private String enterpriseId;
    @NacosValue("${xy.instance}")
    private String instance;
    @NacosValue("${xy.token}")
    private String token;


    @NacosValue("${wechat.corpid}")
    private String corpId;
    @NacosValue("${wechat.corpSecret}")
    private String corpSecret;
    @NacosValue("${wechat.agentId}")
    private String agentId;
    @NacosValue("${wechat.url}")
    private String wechatUrl;
    @NacosValue("${wechat.page}")
    private String page;


    public String getCorpId() {
        return corpId;
    }

    public String getAgentId() {
        return agentId;
    }

    public final String getAccessTokenUrl(){
        return this.wechatUrl+"/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+corpSecret;
    }

    public final String getUserInfoUrl(String token,String code){
        return  this.wechatUrl+"/cgi-bin/user/getuserinfo?access_token="+token+"&code="+code;
    }

    public final String getUserDetailUrl(String token){
        return this.wechatUrl+"/cgi-bin/user/getuserdetail?access_token="+token;
    }

    public final String getJsApiTicketUrl(String token) {
        return this.wechatUrl + "/cgi-bin/get_jsapi_ticket?access_token="+token+"&type=agent_config";
    }

    public final String getJsAgentTicketUrl(String token) {
        return this.wechatUrl + "/cgi-bin/ticket/get?access_token="+token+"&type=agent_config";
    }


    public final String toHomePage(String id, String name, String phone, String company) throws UnsupportedEncodingException {
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
        return this.wechatUrl + "/cgi-bin/message/send?access_token="+token;
    }

    public String getToken() {
        return token;
    }

    public String getXyUrl() {
        return xyUrl;
    }

    public String getLoginUrl(){
        return xyUrl+"/api/rest/v1/thirdAuth/token/login";
    }

    public String getAccountBindUrl(){
        return xyUrl+"/api/rest/v1/thirdAuth/account/binding";
    }

    public String getAddOrUpdateUserUrl(){
        return xyUrl+"/api/rest/v1/thirdAuth/account/addOrUpdate";
    }

    public String getDeleteUserUrl(){
        return  xyUrl+"/api/rest/v1/thirdAuth/account/remove";
    }

    public String getAddOrUpdateDeptUrl(){
        return  xyUrl+"/api/rest/v1/thirdAuth/department/addOrUpdate";
    }

    public String getDeleteDeptUrl(){
        return  xyUrl+"/api/rest/v1/thirdAuth/department/remove";
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public String getInstance() {
        return instance;
    }

    public final String getSignatureUrl(){
        return xyUrl+"/api/rest/external/v1/meetingreminders?enterpriseId=" + enterpriseId;
    }

    public String getSdkMeetingCallUrl(String number){
        return xyUrl + "/api/rest/v4/en/callUrlInfo?version=v4&number=" + number +"&enterpriseId=" +this.enterpriseId;

    }


}
