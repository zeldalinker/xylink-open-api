package com.xylink.wechat.dao.po;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
public class MeetingUser {

    /**
     * 会议室id
     */
    private String meetingId;

    private MeetingRoom meetingRoom;


    /**
     * 用户名
     */
    private String userDisplayName;

    /**
     * 用户电话
     */
    private String userPhone;

    private String uid;


    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

}
