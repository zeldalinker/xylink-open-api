package com.xylink.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xylink.wechat.bean.MeetingRoomParam;
import com.xylink.wechat.bean.response.ResponseResult;
import com.xylink.wechat.dao.po.MeetingRoom;
import com.xylink.wechat.exception.BusinessException;
import com.xylink.wechat.service.MeetingService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Controller
@RequestMapping("/meeting")
public class MeetingController {
    private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @Resource
    private MeetingService meetingService;

    /**
     * 获取通话记录
     */
    @RequestMapping(value = "selectLatest",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult index(@Param("phoneNumber") String phoneNumber) {
        ResponseResult result;
        try{
            List<MeetingRoom> data = meetingService.selectLatest(phoneNumber);
            result = ResponseResult.createBySuccess(data);
        }catch (BusinessException e){
            logger.info("[获取会议行程] 异常 ",e);
            result = ResponseResult.createByErrorMessage("[获取会议行程] 异常");
        }
        return result;
    }

    /**
     * 创建会议
     */
    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody MeetingRoomParam param) {
        ResponseResult result;
        try{
            MeetingRoom room = JSONObject.parseObject(JSON.toJSONString(param),MeetingRoom.class);
            meetingService.create(room,param.getUserList());
            result = ResponseResult.createBySuccess();
        }catch (BusinessException e){
            logger.info("[创建会议] 异常 ",e);
            result = ResponseResult.createByErrorMessage("[创建会议] 异常");
        }
        return result;
    }


    @RequestMapping(value = "selectDetail",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@Param("meetingId") String meetingId, @Param("userDisplayName") String userDisplayName, @Param("userPhone") String userPhone) {
        ResponseResult result;
        try{
            MeetingRoom room = meetingService.getDetail(meetingId,userDisplayName,userPhone);
            result = ResponseResult.createBySuccess(room);
        }catch (BusinessException e){
            logger.info("[创建会议] 异常 ",e);
            result = ResponseResult.createByErrorMessage("[创建会议] 异常");
        }
        return result;
    }
}
