package com.xylink.wechat.bean;

import com.xylink.wechat.dao.po.MeetingRoom;
import com.xylink.wechat.dao.po.MeetingUser;

import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
public class MeetingRoomParam extends MeetingRoom {
    private List<MeetingUser> userList;

    public List<MeetingUser> getUserList() {
        return userList;
    }

    public void setUserList(List<MeetingUser> userList) {
        this.userList = userList;
    }
}
