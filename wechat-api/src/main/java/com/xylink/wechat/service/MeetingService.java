package com.xylink.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xylink.model.ReminderMeeting;
import com.xylink.util.HttpUtil;
import com.xylink.util.Result;
import com.xylink.wechat.bean.ApiConfig;
import com.xylink.wechat.bean.wechat.Articles;
import com.xylink.wechat.dao.po.MeetingCall;
import com.xylink.wechat.dao.po.MeetingRoom;
import com.xylink.wechat.dao.po.MeetingUser;
import com.xylink.wechat.exception.BusinessException;
import com.xylink.wechat.util.JacksonUtil;
import com.xylink.wechat.util.SignatureSampleLocal;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Service
public class MeetingService {
    private static final Logger logger = LoggerFactory.getLogger(MeetingService.class);

    @Resource
    private Me meetingUserRepository;

    @Resource
    private MeetingRoomRepository meetingRoomRepository;

    @Resource
    private MeetingCallRepository meetingCallRepository;

    @Resource
    private ApiConfig apiConfig;

    @Resource
    private WeChatService weChatService;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 获取行程
     */
    public List<MeetingRoom> selectPlans(String phone) throws BusinessException {
        List<MeetingRoom> latestPlans = Lists.newArrayList();
        logger.info(" query meeting schedule , phone = {} ", phone);
        if (StringUtils.isEmpty(phone)) {
            throw new BusinessException(" phone is empty");
        }
        List<MeetingUser> users = meetingUserRepository.findByUserPhone(phone);
        for (MeetingUser user : users) {
            //有效会议室
            MeetingRoom latestPlan = meetingRoomRepository.findFirstByMeetingIdAndMeetingUrlIsNotNull(user.getMeetingRoom().getMeetingId());
            latestPlans.add(latestPlan);
        }
        return latestPlans;
    }


    /**
     * 预约会议
     */
    public MeetingRoom saveMeetingRoom(MeetingRoom meetingRoom) throws BusinessException {
        saveCloudRoom(meetingRoom);
        logger.info(" [  saving meeting  ] result = {} ", JSONObject.toJSONString(meetingRoom));
        meetingRoomRepository.save(meetingRoom);
        if (meetingRoom.getUserList().isEmpty()) {
            throw new BusinessException("参会人员为空");
        }
        for (MeetingUser meetingUser : meetingRoom.getUserList()) {
            meetingUser.setMeetingRoom(meetingRoom);
            String meetingUrl = apiConfig.getMeetingDetailUrl(meetingRoom.getMeetingId(),
                    meetingUser.getUserDisplayName(), meetingUser.getUserPhone());
            logger.info("[ create meeting ] send meeting info link url: " + meetingUrl);
            String params = convertWeChatMessage(meetingUser.getUid(), meetingRoom.getTitle(), meetingUrl);
            logger.info("[ create meeting ] send message params is {}", params);
            String accessToken = weChatService.getAccessToken();
            String messageUrl = apiConfig.getMessageUrl(accessToken);
            ResponseEntity response = restTemplate.postForObject(messageUrl, params, ResponseEntity.class);
            if (response.getStatusCode() != null) {
                logger.info("[ create a meeting failure]");
            }
        }
        meetingRoomRepository.save(meetingRoom);
        return meetingRoom;
    }


    public MeetingRoom selectMeetingRoomUrl(String meetingId, String name, String phone) throws BusinessException {
        if (StringUtils.isEmpty(meetingId) || StringUtils.isEmpty(name) || StringUtils.isEmpty(phone)) {
            throw new BusinessException(" [ meeting detail ] param is empty ");
        }
        MeetingRoom meetingRoom;
        try {
            meetingRoom = meetingRoomRepository.findFirstByMeetingId(meetingId);
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


    private String convertWeChatMessage(String uid, String title, String meetingUrl) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("touser", uid);
        params.put("msgtype", "news");
        params.put("agentid", apiConfig.getAgentId());
        HashMap<String, Object> links = Maps.newHashMap();
        Articles[] articles = {new Articles(title, "", meetingUrl, "")};
        links.put("articles", articles);
        params.put("news", links);
        return JacksonUtil.writJson(params);
    }

    private void saveCloudRoom(MeetingRoom meetingRoom) throws BusinessException {
        Map cloudRoom;
        if (meetingRoom == null) {
            throw new BusinessException(" meeting room is null ");
        }
        logger.info("[ create wechat message ] params:{}", meetingRoom.toString());
        SignatureSampleLocal sign = new SignatureSampleLocal();
        String sdkUrl = apiConfig.getXylinkUrl();
        String token = apiConfig.getToken();
        String surl = apiConfig.getSignatureUrl();

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
            cloudRoom = (Map) result.getData();
        } catch (IOException e) {
            throw new BusinessException("获取小鱼会议sign失败", e);
        }
        String meetingId = cloudRoom.get("meetingId").toString();
        String meetingRoomNumber = cloudRoom.get("meetingRoomNumber").toString();
        meetingRoom.setMeetingId(meetingId);
        meetingRoom.setUserPhone(meetingRoom.getUid());
        meetingRoom.setMeetingRoomNumber(meetingRoomNumber);
        meetingRoom.setMeetingUrl(apiConfig.getMeetingDetailUrl());
    }


    public List<MeetingCall> selectCalls(String phone){
        List<MeetingCall> meetingCalls = meetingCallRepository.findAllByUserPhoneOrderByCallTimeDesc(phone);
        if(meetingCalls==null){
            meetingCalls = Lists.newArrayList();
        }
        return meetingCalls;
    }

    public void saveCall(MeetingCall meetingCall){
        MeetingCall existCall = meetingCallRepository.findFirstByCallNumberAndUserPhone(meetingCall.getCallNumber(),meetingCall.getUserPhone());
        if(existCall!=null){
            meetingCall.setId(existCall.getId());
        }
        meetingCallRepository.save(meetingCall);
    }

    public Map<String,Object> selectCloudCall(String number) {
        Map<String,Object> sdkCall = Maps.newHashMap();
        if (!StringUtils.isEmpty(number) && isPhone(number)) {
            number = number.replace("-", "");
            number = number.replace(" ", "+");
        }
        try {
            if (number.contains("#")) {
                number = URLEncoder.encode(number,"UTF-8");
            }
            String meetingCallUrl  = apiConfig.getSdkMeetingCallUrl(number);
            ResponseEntity<String> response = restTemplate.getForEntity(meetingCallUrl, String.class);

            if(response.getStatusCode()!= HttpStatus.OK){
                logger.info(" 请求pivotor异常 , {} ",response);
                throw new BusinessException("请求pivotor异常");
            }
            sdkCall = JSON.parseObject(response.getBody(), new TypeReference<Map<String,Object>>() {});
        } catch (UnsupportedEncodingException | BusinessException e) {
            logger.info(" 查询sdk通话记录 异常 ",e);
        }
        return sdkCall;
    }


    public MeetingRoom selectMeetingRoom(String meetingId){
       return meetingRoomRepository.findByMeetingId(meetingId);
    }



    private static Pattern phone = Pattern.compile("^\\s\\d+-\\d+$");

    private  boolean isPhone(String number) {
        return phone.matcher(number).matches();
    }
}
