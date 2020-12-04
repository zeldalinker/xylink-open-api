package com.xylink.wechat.dao.po;

import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-26
 */
public class MeetingRoom extends BaseModel{

    /**
     * 会议id
     */
    private String meetingId;

    /**
     * 会议室号
     */
    private String meetingRoomNumber;
    /**
     * 创建人手机号
     */
    private String userPhone;

    /**
     * 创建人名称
     */
    private String userDisplayName;

    /**
     * 会议标题
     */
    private String title;

    /**
     * 会议开始时间
     */
    private Long startTime;

    /**
     * 会议结束时间
     */
    private Long endTime;
    /**
     * 会议链接
     */
    private String meetingUrl;

    private String uid;


    private List<MeetingUser> userList;

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingRoomNumber() {
        return meetingRoomNumber;
    }

    public void setMeetingRoomNumber(String meetingRoomNumber) {
        this.meetingRoomNumber = meetingRoomNumber;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getMeetingUrl() {

        return meetingUrl;
    }

    public void setMeetingUrl(String meetingUrl) {

        this.meetingUrl = meetingUrl;
    }

    public List<MeetingUser> getUserList() {
        return userList;
    }

    public void setUserList(List<MeetingUser> userList) {
        this.userList = userList;
    }


    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {

        this.uid = uid;
    }


}

