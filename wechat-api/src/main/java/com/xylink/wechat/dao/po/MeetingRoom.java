package com.xylink.wechat.dao.po;

import com.xylink.model.ReminderMeeting;

import javax.persistence.*;
import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-26
 */
@Entity
@Table(name = "meeting_room")
public class MeetingRoom extends BaseModel{

    /**
     * 会议id
     */
    @Column(name = "meeting_id")
    private String meetingId;

    /**
     * 会议室号
     */
    @Column(name = "meeting_room_number")
    private String meetingRoomNumber;
    /**
     * 创建人手机号
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 创建人名称
     */
    @Column(name = "user_display_name")
    private String userDisplayName;

    /**
     * 会议标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 会议开始时间
     */
    @Column(name = "start_time")
    private Long startTime;

    /**
     * 会议结束时间
     */
    @Column(name = "end_time")
    private Long endTime;

    /**
     * 会议链接
     */
    @Column(name = "meeting_url")
    private String meetingUrl;

    @Column(name = "uid")
    private String uid;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "meetingRoom")
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

