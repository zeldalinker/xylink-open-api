package com.xylink.wechat.bean.wechat;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
public class UserInfo {
    private int errcode;
    private String errmsg;
    private String userid;
    private String deviceid;
    private String user_ticket;
    private int expires_in;
    private int usertype;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getUser_ticket() {
        return user_ticket;
    }

    public void setUser_ticket(String user_ticket) {
        this.user_ticket = user_ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
