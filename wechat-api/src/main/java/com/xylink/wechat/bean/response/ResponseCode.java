package com.xylink.wechat.bean.response;

public enum ResponseCode {
    SUCCESS(200, "SUCCESS"),
    ERROR(400, "ERROR"),
    NO_CONTENT(204,"NO_CONTENT"),
    ILLEGAL_ARGUEMENT(100, "ILLEGAL_ARGUMENT");
    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }
}