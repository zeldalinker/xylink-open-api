package com.xylink.wechat.controller;

import com.xylink.wechat.bean.response.ResponseResult;
import com.xylink.wechat.dao.po.MeetingCall;
import com.xylink.wechat.dao.po.MeetingRoom;
import com.xylink.wechat.dao.po.WeChatConfig;
import com.xylink.wechat.exception.BusinessException;
import com.xylink.wechat.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-24
 */
@Controller
public class WechatController{
    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Resource
    private HomeService homeService;

    @Resource
    private MeetingService meetingService;

    @Resource
    private WeChatJsConfigService wechatJsConfigService;


    @GetMapping("toPage")
    public void toPage(@NotNull(message = "code不能为空") @RequestParam("code") String code,
                       HttpServletResponse response){
        logger.info(" index  code : {}", code);
        try {
            String page = homeService.toHomePage(code);
            response.sendRedirect(page);
        } catch (BusinessException | IOException e) {
            logger.error("[跳转政务微信首页异常]",e);
            throw new BusinessException(e);
        }
    }

    @GetMapping("/api/rest/v1/en/lanxin/callHistory")
    @ResponseBody
    public ResponseResult selectCalls(@RequestParam("phoneNumber") String phoneNumber){
        ResponseResult result;
        try{
            List<MeetingCall>  data = meetingService.selectCalls(phoneNumber);
            result = ResponseResult.createBySuccess(data);
        }catch (Exception e){
            logger.info("查询会议记录异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }

    @PostMapping("/api/rest/v1/en/lanxin/callHistory")
    @ResponseBody
    public ResponseResult saveCall(@RequestBody  MeetingCall meetingCall){
        ResponseResult result;
        try{
            meetingService.saveCall(meetingCall);
            result = ResponseResult.createBySuccess();
        }catch (Exception e){
            logger.info("保存会议记录异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }

    @PostMapping("/api/rest/v1/en/lanxin/index/callUrlInfo")
    @ResponseBody
    public ResponseResult selectCall(@RequestParam("number") String number){
        ResponseResult result;
        try{
            Map<String,Object> data =  meetingService.selectCall(number);
            result = ResponseResult.createBySuccess(data);
        }catch (Exception e){
            logger.info("查询小鱼会议记录异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }

    @GetMapping("/api/rest/v1/en/lanxin/dating")
    @ResponseBody
    public ResponseResult selectMeetingRoom(@RequestParam("meetingId") String meetingId){
        ResponseResult result;
        try{
            MeetingRoom data =  meetingService.selectMeetingRoom(meetingId);
            result = ResponseResult.createBySuccess(data);
        }catch (Exception e){
            logger.info("查询小鱼会议异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }

    @GetMapping("/api/rest/v1/en/lanxin/govwechat/config")
    @ResponseBody
    public ResponseResult getConfig(@RequestParam("url") String url){
        ResponseResult result;
        try{
            Map<String, Object> data =  wechatJsConfigService.getJsApiConfig(url);
            result = ResponseResult.createBySuccess(data);
        }catch (Exception e){
            logger.info(" 获取政务微信 JsApiConfig 异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }

    @GetMapping("api/rest/v1/en/lanxin/govwechat/agent/config")
    @ResponseBody
    public ResponseResult getAgentConfig(@RequestParam("url") String url){
        ResponseResult result;
        try{
            Map<String, Object> data =  wechatJsConfigService.getJsAgentConfig(url);
            result = ResponseResult.createBySuccess(data);
        }catch (Exception e){
            logger.info(" 获取政务微信 JsAgentConfig 异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }


    @GetMapping("/api/rest/v1/en/lanxin/govwechat/dating/getSchedule")
    @ResponseBody
    public ResponseResult selectPlans(@RequestParam("userPhone") String userPhone){
        ResponseResult result;
        try{
            List<MeetingRoom> data =  meetingService.selectPlans(userPhone);
            result = ResponseResult.createBySuccess(data);
        }catch (Exception e){
            logger.info(" 获取行程记录 异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }

    @PostMapping("/wechat/dating/wx/dating")
    @ResponseBody
    public ResponseResult saveMeetingRoom(MeetingRoom meetingRoom){
        ResponseResult result;
        try{
            MeetingRoom data =  meetingService.saveMeetingRoom(meetingRoom);
            result = ResponseResult.createBySuccess(data);
        }catch (Exception e){
            logger.info(" 生成会议 异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }


    @PostMapping("api/rest/v1/en/lanxin/govwechat/dating/details")
    @ResponseBody
    public ResponseResult selectMeetingRoomUrl(@RequestParam("meetingId") String meetingId,
                                               @RequestParam("userDisplayName") String userDisplayName,
                                               @RequestParam("userPhone") String userPhone){
        ResponseResult result;
        try{
            MeetingRoom data =  meetingService.selectMeetingRoomUrl(meetingId, userDisplayName, userPhone);
            result = ResponseResult.createBySuccess(data);
        }catch (Exception e){
            logger.info(" 会议详情异常 异常 ",e);
            throw new BusinessException(e);
        }
        return result;
    }
}
