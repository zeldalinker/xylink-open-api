package com.xylink.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xylink.model.ReminderMeeting;
import com.xylink.util.HttpUtil;
import com.xylink.util.Result;
import com.xylink.wechat.bean.wechat.Articles;
import com.xylink.wechat.config.factory.WeChatConfig;
import com.xylink.wechat.config.factory.XyConfig;
import com.xylink.wechat.dao.MeetingRoomMapper;
import com.xylink.wechat.dao.MeetingUserMapper;
import com.xylink.wechat.dao.po.MeetingRoom;
import com.xylink.wechat.dao.po.MeetingUser;
import com.xylink.wechat.exception.BusinessException;
import com.xylink.wechat.util.JacksonUtil;
import com.xylink.wechat.util.SignatureSampleLocal;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Service
public class MeetingService {
    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

    @Resource
    private MeetingUserMapper userMapper;

    @Resource
    private MeetingRoomMapper roomMapper;

    @Resource
    private XyConfig xyConfig;

    @Resource
    private WeChatConfig weChatConfig;

    @Resource
    private WeChatService weChatService;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 获取行程
     */
    public List<MeetingRoom> selectLatest(String phone) throws BusinessException {
        List<MeetingRoom> latestRooms = Lists.newArrayList();
        logger.info(" query meeting schedule , phone = {} ", phone);
        if (StringUtils.isEmpty(phone)) {
            throw new BusinessException(" phone is empty");
        }
        long latestMillis = (System.currentTimeMillis() / 1000);
        List<MeetingUser> users = userMapper.selectByPhone(phone);
        for (MeetingUser user : users) {
            //有效会议室
            List<MeetingRoom> expiryRooms = roomMapper.selectExpiryRooms(user.getMeetingId(), latestMillis);
            latestRooms.addAll(expiryRooms);
        }
        return latestRooms;
    }


    /**
     * 预约会议
     */
    public void create(MeetingRoom meetingRoom, List<MeetingUser> meetingUsers) throws BusinessException {
        createSdkMeetingRoom(meetingRoom);
        logger.info(" [  booking meeting  ] result = {} ", JSONObject.toJSONString(meetingRoom));
        roomMapper.save(meetingRoom);
        if (meetingUsers.isEmpty()) {
            return;
        }
        for (MeetingUser meetingUser : meetingUsers) {
            meetingUser.setMeetingRoom(meetingRoom);
            String meetingUrl = weChatConfig.getMeetingDetailUrl(meetingRoom.getMeetingId(),
                    meetingUser.getUserDisplayName(), meetingUser.getUserPhone());
            logger.info("[ create meeting ] send meeting info link url: " + meetingUrl);
            String params = createWeChatMessage(meetingUser.getUid(), meetingRoom.getTitle(), meetingUrl);
            logger.info("[ create meeting ] send message params is {}", params);
            String accessToken = weChatService.getAccessToken();
            String messageUrl = weChatConfig.getMessageUrl(accessToken);
            ResponseEntity response = restTemplate.postForObject(messageUrl, params, ResponseEntity.class);
            if (response.getStatusCode() != null) {
                logger.info("[ create a meeting failure]");
            }
        }
        roomMapper.save(meetingRoom);
    }


    public MeetingRoom getDetail(String meetingId, String name, String phone) throws BusinessException {
        if (StringUtils.isEmpty(meetingId) || StringUtils.isEmpty(name) || StringUtils.isEmpty(phone)) {
            throw new BusinessException(" [ meeting detail ] param is empty ");
        }
        MeetingRoom meetingRoom;
        try {
            meetingRoom = roomMapper.selectInfoById(meetingId);
        } catch (Exception e) {
            logger.error("  [ meeting detail  ] query meeting schedule detail failure ", e);
            throw new BusinessException("  [ meeting detail ] query meeting schedule detail failure ");
        }
        if (meetingRoom != null) {
            String replace = meetingRoom.getMeetingUrl().replace("${meetingId}", meetingId).replace("$" +
                    "{userDisplayName}", name)
                    .replace("${userPhone}", phone);
            meetingRoom.setMeetingUrl(replace);
        }
        return meetingRoom;
    }


    private String createWeChatMessage(String uid, String title, String meetingUrl) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("touser", uid);
        params.put("msgtype", "news");
        params.put("agentid", weChatConfig.getAgentId());
        HashMap<String, Object> urlLinkMap = new HashMap<>();
        Articles[] articles = {new Articles(title, "", meetingUrl, "")};
        urlLinkMap.put("articles", articles);
        params.put("news", urlLinkMap);
        return JacksonUtil.writJson(params);
    }

    private void createSdkMeetingRoom(MeetingRoom meetingRoom) throws BusinessException {
        Map sdkRoom;
        if (meetingRoom == null) {
            throw new BusinessException(" meetingRoom is null ");
        }
        logger.info("[ create wechat message ] params:{}", meetingRoom.toString());
        SignatureSampleLocal sign = new SignatureSampleLocal();
        String sdkUrl = xyConfig.getHost();
        String token = xyConfig.getToken();
        String surl = xyConfig.getSignatureUrl();

        ReminderMeeting reminderMeeting = new ReminderMeeting();
        reminderMeeting.setStartTime(meetingRoom.getStartTime());
        reminderMeeting.setEndTime(meetingRoom.getEndTime());
        reminderMeeting.setTitle(meetingRoom.getTitle());
        reminderMeeting.setMeetingRoomType(1);
        try {
            String entity = (new ObjectMapper()).writeValueAsString(reminderMeeting);
            String signature = sign.computeSignature(entity, "POST", token, surl, sdkUrl);
            surl = surl + "&signature=" + signature;
            Result result = HttpUtil.getResponse(surl, "POST", entity, Map.class);
            if (!result.isSuccess()) {
                logger.info("[  create wechat message ] fail:{}", result.toString());
                throw new BusinessException(" xylink cloud sdk booking meeting failure ");
            }
            sdkRoom = (Map) result.getData();
        } catch (IOException e) {
            throw new BusinessException("获取小鱼会议sign失败", e);
        }
        String meetingId = sdkRoom.get("meetingId").toString();
        String meetingRoomNumber = sdkRoom.get("meetingRoomNumber").toString();
        meetingRoom.setMeetingId(meetingId);
        meetingRoom.setUserPhone(meetingRoom.getUid());
        meetingRoom.setMeetingRoomNumber(meetingRoomNumber);
        meetingRoom.setMeetingUrl(weChatConfig.getMeetingDetailUrl());
    }
}
