package com.xylink.wechat.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhongkun
 * @description 通话记录
 * @create 2019/4/12
 */
@Entity
@Table(name = "meeting_call")
public class MeetingCall extends BaseModel {
    /**
     * 会议室号
     */
    @Column(name = "call_number")
    private String callNumber;

    /**
     * 通话时间
     */
    @Column(name = "call_time")
    private Long callTime;

    /**
     * 会议室标题
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * 通话人电话
     */
    @Column(name = "user_phone")
    private String userPhone;


    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}
