package com.xylink.wechat.dao.po;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author 林骏
 * @description 参会人员
 * @create 2019/4/12
 */
@Entity
@Table(name = "meeting_user")
public class MeetingUser extends BaseModel{

    /**
     * 会议室id 关联 会议室
     */
    @JsonIgnore
    @JoinColumn(name = "meeting_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private MeetingRoom meetingRoom;

    /**
     * 用户名
     */
    @Column(name = "user_display_name")
    private String userDisplayName;

    /**
     * 用户电话
     */
    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "uid")
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
}
